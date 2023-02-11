package com.taxes.calculator.domain.exceptions;

import com.taxes.calculator.domain.AggregateRoot;
import com.taxes.calculator.domain.Identifier;
import com.taxes.calculator.domain.validation.Error;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public class NotFoundException extends DomainException {
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
