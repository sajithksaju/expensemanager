package com.ssaju.expensemanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssaju.expensemanager.model.PaymentMethod;
import com.ssaju.expensemanager.model.PaymentMethods;
import com.ssaju.expensemanager.service.exception.RequestValidationException;
import com.ssaju.expensemanager.util.ExpenseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.ssaju.expensemanager.model.ErrorResponse.FieldError;
import static com.ssaju.expensemanager.service.ServiceConstants.PAYMENT_METHOD_FILE_PATH;
import static com.ssaju.expensemanager.service.ServiceConstants.PAYMENT_TYPES;

@Configurable
public class PaymentMethodService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ExpenseUtil expenseUtil;

    public void addPaymentMethod(PaymentMethod paymentMethod) {
        try {
            paymentMethod.setType(paymentMethod.getType().toUpperCase());
            PaymentMethods paymentMethods = expenseUtil.getPaymentMethodsFromJSON();
            validatePaymentMethod(paymentMethods, paymentMethod);
            paymentMethods.getPaymentMethods().add(paymentMethod);
            objectMapper.writeValue(new File(PAYMENT_METHOD_FILE_PATH), paymentMethods);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean isPaymentExisting(PaymentMethods paymentMethods, PaymentMethod paymentMethod) {
        return paymentMethods.getPaymentMethods().stream()
                .anyMatch(method -> method.getName().equals(paymentMethod.getName()));
    }

    private void validatePaymentMethod(PaymentMethods paymentMethods, PaymentMethod paymentMethod) {
        FieldError fieldError;
        if (paymentMethod.getName() == null ||
                paymentMethod.getName().isEmpty()) {
            fieldError = new FieldError("INVALID_INPUT", "/name");
            throw new RequestValidationException("Name is mandatory.", fieldError);
        }
        if (paymentMethod.getType() == null ||
                paymentMethod.getType().isEmpty()) {
            fieldError = new FieldError("INVALID_INPUT", "/type");
            throw new RequestValidationException("Type is mandatory!", fieldError);
        }
        if (!PAYMENT_TYPES.contains(paymentMethod.getType())) {
            fieldError = new FieldError("INVALID_INPUT", "/type");
            throw new RequestValidationException("Invalid Payment Type, valida types are " + PAYMENT_TYPES.toString(), fieldError);
        }
        if (isPaymentExisting(paymentMethods, paymentMethod)) {
            fieldError = new FieldError("INVALID_INPUT", "/name");
            throw new RequestValidationException("Payment method already exists!", fieldError);
        }
    }
}
