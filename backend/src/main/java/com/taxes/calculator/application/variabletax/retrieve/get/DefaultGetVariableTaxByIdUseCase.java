package com.taxes.calculator.application.variabletax.retrieve.get;

import java.util.Objects;

import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxID;

public class DefaultGetVariableTaxByIdUseCase
	extends GetVariableTaxByIdUseCase {

    private final VariableTaxGateway variableTaxGateway;

    public DefaultGetVariableTaxByIdUseCase(
	    final VariableTaxGateway variableTaxGateway) {
	this.variableTaxGateway = Objects
		.requireNonNull(variableTaxGateway);
    }

    @Override
    public VariableTaxByIdOutput execute(String anIn) {
	final var anId = VariableTaxID.from(anIn);

	return this.variableTaxGateway.findById(anId)
		.map(VariableTaxByIdOutput::from)
		.orElseThrow(() -> NotFoundException
			.with(VariableTax.class, anId));
    }

}
