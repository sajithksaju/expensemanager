package com.ssaju.expensemanager.controller;

import com.ssaju.expensemanager.model.Expense;
import com.ssaju.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ssaju on 2/5/17.
 */
@RestController
@RequestMapping(("expenseManager/expense/"))
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(@Qualifier("expenseService") final ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<Expense> add(@RequestBody final Expense expense) {
        expenseService.addExpense(expense);
        return ResponseEntity.ok(expense);
    }
}
