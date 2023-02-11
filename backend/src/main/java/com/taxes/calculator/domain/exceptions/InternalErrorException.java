package com.taxes.calculator.domain.exceptions;

public class InternalErrorException extends NoStackTraceException {
    public InternalErrorException(final String aMessage, final Throwable t) {
        super(aMessage, t);
    }

    public static InternalErrorException with(
            final String message,
            final Throwable t) {
        return new InternalErrorException(message, t);
    }
}
