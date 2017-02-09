package com.ssaju.expensemanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssaju.expensemanager.model.Expense;
import com.ssaju.expensemanager.model.Expenses;
import com.ssaju.expensemanager.model.PeriodReportRequest;
import com.ssaju.expensemanager.service.exception.RequestValidationException;
import com.ssaju.expensemanager.util.ExpenseUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ssaju.expensemanager.model.ErrorResponse.FieldError;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * Created by ssaju on 2/7/17.
 */
public class ReportService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    S3Manager s3Manager;


    public List<Expense> getExpensesForPeriod(final PeriodReportRequest periodReportRequest) {
        Expenses expenses = s3Manager.getExpensesFromS3();
        PeriodReportRequest request = validateAndUpdateRequest(periodReportRequest);
        List<Expense> expensesForGivenPeriod = expenses.getExpenses().stream()
                .filter(expense -> {
                    return ((request.getStartDate().isEqual(expense.getTransactionDate()) || request.getStartDate().isBefore(expense.getTransactionDate()))
                            && (request.getEndDate().isEqual(expense.getTransactionDate()) || request.getEndDate().isAfter(expense.getTransactionDate())));
                })
                .sorted((e1, e2) -> e1.getTransactionDate().compareTo(e2.getTransactionDate()))
                .collect(Collectors.toList());

        return expensesForGivenPeriod;
    }

    private PeriodReportRequest validateAndUpdateRequest(final PeriodReportRequest periodReportRequest) {
        PeriodReportRequest request = periodReportRequest;
        if (request.getStartDate() == null || request.getEndDate() == null) {
            if (request.getMonth() == null) {
                FieldError fieldError = new FieldError("INVALID_INPUT", "/month");
                throw new RequestValidationException("Month is mandatory.", fieldError);
            }
            if (request.getYear() == null) {
                FieldError fieldError = new FieldError("INVALID_INPUT", "/year");
                throw new RequestValidationException("Year is mandatory.", fieldError);
            }

            LocalDate startDate = LocalDate.now()
                    .withYear(request.getYear())
                    .withMonth(request.getMonth())
                    .with(firstDayOfMonth());
            LocalDate endDate = LocalDate.now()
                    .withYear(request.getYear())
                    .withMonth(request.getMonth())
                    .with(lastDayOfMonth());
            request.setStartDate(startDate);
            request.setEndDate(endDate);
        }
        return request;
    }

    public Map<String, Double> getExpenseTotalPerType(PeriodReportRequest request){
        Map<String, Double> expensePerCategoryMap= new HashMap<String, Double>();
        List<Expense> expenses  = getExpensesForPeriod(request);
        expenses.stream()
                .collect(Collectors.groupingBy(expense-> expense.getType(), Collectors.summingDouble(expense->expense.getAmount())))
                .forEach((type, total)-> expensePerCategoryMap.put(type,total));

        return expensePerCategoryMap;
    }

    public Map<String, Double> getExpenseTotalPerPaymentMethod(PeriodReportRequest request){
        Map<String, Double> expensePerCategoryMap= new HashMap<String, Double>();
        List<Expense> expenses  = getExpensesForPeriod(request);
        expenses.stream()
                .collect(Collectors.groupingBy(expense-> expense.getPaymentMethod(), Collectors.summingDouble(expense->expense.getAmount())))
                .forEach((paymentMethod, total)-> expensePerCategoryMap.put(paymentMethod,total));

        return expensePerCategoryMap;
    }


}
