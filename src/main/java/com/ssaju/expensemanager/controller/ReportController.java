package com.ssaju.expensemanager.controller;

import com.ssaju.expensemanager.model.Expense;
import com.ssaju.expensemanager.model.PeriodReportRequest;
import com.ssaju.expensemanager.service.ReportService;
import com.ssaju.expensemanager.service.S3Manager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaju on 2/7/17.
 */
@RestController
@RequestMapping(("expenseManager/report/"))
public class ReportController {

    private final ReportService reportService;
    private final S3Manager s3Manager;

    public ReportController(@Qualifier("reportService") final ReportService reportService, @Qualifier("s3Manager") final S3Manager s3Manager) {
        this.reportService = reportService;
        this.s3Manager = s3Manager;
    }

    @RequestMapping(value = "period", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<List<Expense>> getExpenses(@RequestBody PeriodReportRequest request) {
        List<Expense> expenses = reportService.getExpensesForPeriod(request);
        return ResponseEntity.ok(expenses);
    }


    @RequestMapping(value = "period/perType", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<Map<String, Double>> getExpensesByType(@RequestBody PeriodReportRequest request) {
        Map<String, Double> expensePerCategoryMap = reportService.getExpenseTotalPerType(request);
        return ResponseEntity.ok(expensePerCategoryMap);
    }

    @RequestMapping(value = "period/perPaymentMethod", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<Map<String, Double>> getExpensesByPaymentMethod(@RequestBody PeriodReportRequest request) {
        Map<String, Double> expensePerCategoryMap = reportService.getExpenseTotalPerPaymentMethod(request);
        return ResponseEntity.ok(expensePerCategoryMap);
    }


}
