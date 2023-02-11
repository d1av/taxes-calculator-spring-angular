package com.taxes.calculator.domain.exceptions;

import com.taxes.calculator.domain.validation.handler.Notification;

public class NotificationException extends DomainException {
    public NotificationException(final String message, final Notification aNotification) {
        super(message, aNotification.getErrors());
    }
}
