package com.ssaju.expensemanager.service.exception;

import com.ssaju.expensemanager.model.ErrorResponse;

import java.util.Arrays;
import java.util.List;

import static com.ssaju.expensemanager.model.ErrorResponse.FieldError;

/**
 * Created by ssaju on 2/5/17.
 */
public class RequestValidationException extends RuntimeException {

    private final List<ErrorResponse.FieldError> errors;

    public RequestValidationException(final String message, final List<FieldError> errors) {
        super(message);
        this.errors = errors;
    }

    public RequestValidationException(final String message, final FieldError error) {
        super(message);
        this.errors = Arrays.asList(error);
    }

    public RequestValidationException(String message, Throwable cause, List<FieldError> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public RequestValidationException(Throwable cause, List<FieldError> errors) {
        super(cause);
        this.errors = errors;
    }

    public RequestValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<FieldError> errors) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

}
