package com.taxes.calculator.application.variabletax.delete;

import java.util.Objects;

import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxID;

public class DefaultDeleteVariableTaxUseCase
	extends DeleteVariableTaxUseCase {

    private final VariableTaxGateway taxGateway;

    public DefaultDeleteVariableTaxUseCase(
	    final VariableTaxGateway taxGateway) {
	this.taxGateway = Objects.requireNonNull(taxGateway);
    }

    @Override
    public void execute(String onIn) {
	this.taxGateway.deleteById(VariableTaxID.from(onIn));
    }

}
