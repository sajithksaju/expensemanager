package com.ssaju.expensemanager.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssaju.expensemanager.model.Expense;
import com.ssaju.expensemanager.model.Expenses;
import com.ssaju.expensemanager.model.PaymentMethod;
import com.ssaju.expensemanager.model.PaymentMethods;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.ssaju.expensemanager.service.ServiceConstants.EXPENSE_FILE_PATH;
import static com.ssaju.expensemanager.service.ServiceConstants.PAYMENT_METHOD_FILE_PATH;

/**
 * Created by ssaju on 2/7/17.
 */
public class ExpenseUtil {

    @Autowired
    ObjectMapper objectMapper;

    public Expenses getExpensesFromJSON() {
        Expenses expenses = new Expenses(new ArrayList<Expense>());
        try {
            File file = new File(EXPENSE_FILE_PATH);
            if (!file.exists()) {

                objectMapper.writeValue(file, Expenses.class);
            } else {
                expenses = objectMapper.readValue(file, Expenses.class);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        return expenses;
    }

    public PaymentMethods getPaymentMethodsFromJSON() {
        PaymentMethods paymentMethods = new PaymentMethods(new ArrayList<PaymentMethod>());
        try {
            File file = new File(PAYMENT_METHOD_FILE_PATH);
            if (!file.exists()) {
                objectMapper.writeValue(file, paymentMethods);
            } else {
                paymentMethods = objectMapper.readValue(file, PaymentMethods.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paymentMethods;
    }
}
