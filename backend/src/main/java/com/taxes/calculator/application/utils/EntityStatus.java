package com.taxes.calculator.application.utils;

import java.util.function.Supplier;

import com.taxes.calculator.domain.AggregateRoot;
import com.taxes.calculator.domain.Identifier;
import com.taxes.calculator.domain.exceptions.DomainException;
import com.taxes.calculator.domain.exceptions.NotFoundException;

public class EntityStatus {
    
    public static <T> Supplier<DomainException> notFound(final Identifier anId, T theClass) {
        return () -> NotFoundException.with((Class<? extends AggregateRoot<?>>) theClass , anId);
    }
}
