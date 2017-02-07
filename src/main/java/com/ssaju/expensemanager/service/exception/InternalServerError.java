package com.ssaju.expensemanager.service.exception;

/**
 * Created by ssaju on 2/5/17.
 */
public class InternalServerError extends RuntimeException {
    public InternalServerError() {
    }

    public InternalServerError(String message) {
        super(message);
    }

    public InternalServerError(String message, Throwable cause) {
        super(message, cause);
    }
}
