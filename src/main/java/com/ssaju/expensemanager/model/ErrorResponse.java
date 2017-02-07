package com.ssaju.expensemanager.model;

import java.util.List;

/**
 * Created by ssaju on 2/5/17.
 */

public class ErrorResponse {
    private final String message;
    private final List<FieldError> errors;

    public ErrorResponse(final String message, final List<FieldError> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public static class FieldError {


    private final String code;
    private final String field;

    public FieldError(final String code, final String field) {
        this.code = code;
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public String getField() {
        return field;
    }
}
}
