package com.taxes.calculator.domain.exceptions;

import java.util.List;

import com.taxes.calculator.domain.AggregateRoot;
import com.taxes.calculator.domain.Identifier;
import com.taxes.calculator.domain.validation.Error;

public class NotFoundException extends DomainException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private NotFoundException(final String message, final List<Error> anErrors) {
        super(message, anErrors);
    }

    public static NotFoundException with(
            final Class<? extends AggregateRoot<?>> anAggregate,
            final Identifier id
    ) {
        final var anError = "%s with ID %s was not found".formatted(
                anAggregate.getSimpleName(),
                id.getValue()
        );
        return new NotFoundException(anError, List.of(new Error(anError)));
    }
}
