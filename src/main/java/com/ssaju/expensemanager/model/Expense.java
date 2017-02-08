package com.ssaju.expensemanager.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Created by ssaju on 2/5/17.
 */
public class Expense {

    private LocalDate transactionDate;
    private String description;
    private String type;
    private String paymentMethod;
    private Double amount;
    private String notes;

    @JsonCreator
    public Expense(@JsonProperty("transactionDate") final LocalDate transactionDate,
                   @JsonProperty("description") final String description,
                   @JsonProperty("type") final String type,
                   @JsonProperty("paymentMethod") final String paymentMethod,
                   @JsonProperty("amount") final Double amount,
                   @JsonProperty("notes") final String notes) {
        this.transactionDate = transactionDate;
        this.description = description;
        this.type = type;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.notes = notes;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Double getAmount() {
        return amount;
    }

    public String getNotes() {
        return notes;
    }


}
