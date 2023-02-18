package com.taxes.calculator.application.fixedtax.retrieve.get;

import java.util.Objects;

import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.fixedtax.FixedTaxID;
import com.taxes.calculator.domain.variabletax.VariableTax;

public class DefaultGetFixedTaxByIdUseCase
	extends GetFixedTaxByIdUseCase {

    private final FixedTaxGateway fixedTaxGateway;

    public DefaultGetFixedTaxByIdUseCase(
	    final FixedTaxGateway fixedTaxGateway) {
	this.fixedTaxGateway = Objects
		.requireNonNull(fixedTaxGateway);
    }

    @Override
    public GetFixedTaxByIdOutput execute(final String anIn) {
	final var anId = FixedTaxID.from(anIn);
	return this.fixedTaxGateway.findById(anId)
		.map(GetFixedTaxByIdOutput::from)
		.orElseThrow(() -> NotFoundException
			.with(VariableTax.class, anId));
    }

}
