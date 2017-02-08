package com.ssaju.expensemanager.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by ssaju on 2/5/17.
 */
public class PaymentMethods {
    private List<PaymentMethod> paymentMethods;

    @JsonCreator
    public PaymentMethods(@JsonProperty("paymentMethods") final List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public PaymentMethods() {
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }
}
