package com.ssaju.expensemanager.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by ssaju on 2/5/17.
 */
public class Expenses {

    private List<Expense> expenses;

    @JsonCreator
    public Expenses(@JsonProperty("expenses") final List<Expense> expenses) {
        this.expenses = expenses;
    }

    public Expenses() {
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
