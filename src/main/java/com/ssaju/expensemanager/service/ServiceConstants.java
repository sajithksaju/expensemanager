package com.ssaju.expensemanager.service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ssaju on 2/5/17.
 */
public class ServiceConstants {

   public static final List<String> PAYMENT_TYPES = Arrays.asList("CREDIT CARD", "DEBIT CARD", "BANK ACCOUNT", "PAYPAL", "GIFT CARD", "OTHER");
   public static final String EXPENSE_FILE_PATH = "src/main/resources/data/expenses.json";
   public static final String PAYMENT_METHOD_FILE_PATH = "src/main/resources/data/paymentMethods.json";

}
