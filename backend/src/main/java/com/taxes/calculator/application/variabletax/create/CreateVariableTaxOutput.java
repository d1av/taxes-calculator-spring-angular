package com.taxes.calculator.application.variabletax.create;

import java.math.BigDecimal;

import com.taxes.calculator.domain.variabletax.VariableTax;

public record CreateVariableTaxOutput(String id,
	BigDecimal creditCard, BigDecimal dentalShop,
	BigDecimal prosthetist, BigDecimal travel,
	BigDecimal weekend, String userId) {
    public static CreateVariableTaxOutput from(
	    final VariableTax vTax) {
	String id = vTax.getId() != null
		? vTax.getId().getValue()
		: null;
	String userId = vTax.getUserId() != null
		? vTax.getUserId().getValue()
		: null;

	return new CreateVariableTaxOutput(id,
		vTax.getCreditCard(), vTax.getDentalShop(),
		vTax.getProsthetist(), vTax.getTravel(),
		vTax.getWeekend(), userId);
    }
}
