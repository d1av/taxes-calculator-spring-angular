package com.taxes.calculator.application.variabletax.retrieve.get;

import java.math.BigDecimal;

import com.taxes.calculator.domain.variabletax.VariableTax;

public record VariableTaxByIdOutput(String id, BigDecimal dentalShop,
	BigDecimal prosthetist, BigDecimal travel,
	BigDecimal creditCard, BigDecimal weekend, String userId) {
    public static VariableTaxByIdOutput from(final VariableTax aTax) {
	return new VariableTaxByIdOutput(aTax.getId().getValue(),
		aTax.getDentalShop(), aTax.getProsthetist(),
		aTax.getTravel(), aTax.getCreditCard(),
		aTax.getWeekend(), aTax.getUser().getId().getValue());
    }
}
