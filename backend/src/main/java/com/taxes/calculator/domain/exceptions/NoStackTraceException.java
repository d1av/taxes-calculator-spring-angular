package com.taxes.calculator.domain.exceptions;

public class NoStackTraceException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public NoStackTraceException(String message) {
        super(message, null);
    }

    public NoStackTraceException(String message, Throwable cause) {
        super(message, cause,true,false);
    }
}
