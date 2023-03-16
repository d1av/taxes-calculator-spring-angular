package com.taxes.calculator.application.variabletax.update;

import java.math.BigDecimal;

import com.taxes.calculator.domain.variabletax.VariableTax;

public record UpdateVariableTaxOutput(String id,
	BigDecimal creditCard, BigDecimal dentalShop,
	BigDecimal prosthetist, BigDecimal travel,
	BigDecimal weekend, String userId) {
    public static UpdateVariableTaxOutput from(
	    final VariableTax aTax) {
	return new UpdateVariableTaxOutput(
		aTax.getId().getValue(), aTax.getCreditCard(),
		aTax.getDentalShop(), aTax.getProsthetist(),
		aTax.getTravel(), aTax.getWeekend(),
		aTax.getUserId().getValue());
    }
}
