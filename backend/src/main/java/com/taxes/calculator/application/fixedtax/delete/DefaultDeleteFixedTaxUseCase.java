package com.taxes.calculator.application.fixedtax.delete;

import java.util.Objects;

import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.fixedtax.FixedTaxID;

public class DefaultDeleteFixedTaxUseCase
	extends DeleteFixedTaxUseCase {

    private final FixedTaxGateway fixedTaxGateway;

    public DefaultDeleteFixedTaxUseCase(
	    final FixedTaxGateway fixedTaxGateway) {
	this.fixedTaxGateway = Objects
		.requireNonNull(fixedTaxGateway);
    }

    @Override
    public void execute(String onIn) {
	final var anId = FixedTaxID.from(onIn);
	this.fixedTaxGateway.deleteById(anId);
    }

}
