package com.taxes.calculator.domain.exceptions;

import java.util.List;

import com.taxes.calculator.domain.AggregateRoot;
import com.taxes.calculator.domain.Identifier;
import com.taxes.calculator.domain.validation.Error;

public class ServletCustomNotification extends DomainException {

    private static final long serialVersionUID = -3136630887198442977L;

    public ServletCustomNotification(String message,
	    List<Error> anErrors) {
	super(message, anErrors);
    }

    public static ServletCustomNotification with(
	    final Class<? extends AggregateRoot<?>> anAggregate,
	    final Identifier id) {
	final var anError = "%s with ID %s was not found".formatted(
		anAggregate.getSimpleName(), id.getValue());
	return new ServletCustomNotification(anError,
		List.of(new Error(anError)));
    }

}
