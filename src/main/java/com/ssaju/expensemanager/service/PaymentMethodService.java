package com.ssaju.expensemanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssaju.expensemanager.model.PaymentMethod;
import com.ssaju.expensemanager.model.PaymentMethods;
import com.ssaju.expensemanager.service.exception.RequestValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import static com.ssaju.expensemanager.model.ErrorResponse.FieldError;
import static com.ssaju.expensemanager.service.ServiceConstants.PAYMENT_TYPES;

@Configurable
public class PaymentMethodService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    S3Manager s3Manager;

    public void addPaymentMethod(PaymentMethod paymentMethod) {

        paymentMethod.setType(paymentMethod.getType().toUpperCase());
        PaymentMethods paymentMethods = s3Manager.getPaymentMethodsFromS3();
        validatePaymentMethod(paymentMethods, paymentMethod);
        paymentMethods.getPaymentMethods().add(paymentMethod);
        s3Manager.updatepaymentMethodsS3File(paymentMethods);

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
