package com.taxes.calculator.domain.validation;

public record Error(String message) {
    @Override
    public String toString() {
        return message;
    }
}
