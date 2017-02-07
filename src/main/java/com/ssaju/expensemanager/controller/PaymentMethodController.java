package com.ssaju.expensemanager.controller;

import com.ssaju.expensemanager.model.ErrorResponse;
import com.ssaju.expensemanager.model.PaymentMethod;
import com.ssaju.expensemanager.service.PaymentMethodService;
import com.ssaju.expensemanager.service.exception.RequestValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(("expenseManager/paymentMethod/"))
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(@Qualifier("paymentMethodService") final PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public PaymentMethod add(@RequestBody final PaymentMethod paymentMethod) {
        paymentMethodService.addPaymentMethod(paymentMethod);
        return paymentMethod;
    }

    @ExceptionHandler(RequestValidationException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleValdationException(RequestValidationException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }
}
