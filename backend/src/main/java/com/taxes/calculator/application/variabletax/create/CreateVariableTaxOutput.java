package com.taxes.calculator.application.variabletax.create;

import com.taxes.calculator.domain.variabletax.VariableTaxID;

public record CreateVariableTaxOutput(String id) {
    public static CreateVariableTaxOutput from(final VariableTaxID anId) {
	return new CreateVariableTaxOutput(anId.getValue());
    }
}
