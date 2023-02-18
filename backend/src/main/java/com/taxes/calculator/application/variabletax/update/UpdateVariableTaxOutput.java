package com.taxes.calculator.application.variabletax.update;

import com.taxes.calculator.domain.variabletax.VariableTax;

public record UpdateVariableTaxOutput(String id) {
    public static UpdateVariableTaxOutput from(
	    final VariableTax aTax) {
	return new UpdateVariableTaxOutput(aTax.getId().getValue());
    }
}
