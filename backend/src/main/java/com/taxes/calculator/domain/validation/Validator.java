package com.taxes.calculator.domain.validation;

import java.util.Objects;

public abstract class Validator {
    private final ValidationHandler handler;

    public Validator(final ValidationHandler handler) {
        this.handler = Objects.requireNonNull(handler);
    }
}
