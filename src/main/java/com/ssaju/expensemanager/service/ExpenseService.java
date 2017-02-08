package com.ssaju.expensemanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssaju.expensemanager.model.Expense;
import com.ssaju.expensemanager.model.Expenses;
import com.ssaju.expensemanager.service.exception.RequestValidationException;
import com.ssaju.expensemanager.util.ExpenseUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.ssaju.expensemanager.model.ErrorResponse.FieldError;
import static com.ssaju.expensemanager.service.ServiceConstants.EXPENSE_FILE_PATH;

/**
 * Created by ssaju on 2/5/17.
 */
public class ExpenseService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ExpenseUtil expenseUtil;

    public void addExpense(Expense expense) {
        try {
            validate(expense);
            Expenses expenses = expenseUtil.getExpensesFromJSON();
            expenses.getExpenses().add(expense);
            objectMapper.writeValue(new File(EXPENSE_FILE_PATH), expenses);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void validate(Expense expense) {
        FieldError fieldError;
        if (expense.getTransactionDate() == null) {
            fieldError = new FieldError("INVALID_INPUT", "/transactionDate");
            throw new RequestValidationException("Transaction Date is mandatory.", fieldError);
        }
        if (expense.getDescription() == null || expense.getDescription().isEmpty()) {
            fieldError = new FieldError("INVALID_INPUT", "/description");
            throw new RequestValidationException("Description is mandatory.", fieldError);
        }
        if (expense.getType() == null || expense.getType().isEmpty()) {
            fieldError = new FieldError("INVALID_INPUT", "/type");
            throw new RequestValidationException("Type is mandatory.", fieldError);
        }
        if (expense.getPaymentMethod() == null || expense.getPaymentMethod().isEmpty()) {
            fieldError = new FieldError("INVALID_INPUT", "/paymentMethod");
            throw new RequestValidationException("Payment Method is mandatory.", fieldError);
        }
        if (expense.getAmount() == null) {
            fieldError = new FieldError("INVALID_INPUT", "/amount");
            throw new RequestValidationException("Amount is mandatory.", fieldError);
        }


    }
}
