package com.taxes.calculator.application.variabletax.retrieve.list;

import java.math.BigDecimal;

import com.taxes.calculator.domain.variabletax.VariableTax;

public record ListVariableTaxOutput(String id, BigDecimal dentalShop,
	BigDecimal prosthetist, BigDecimal travel,
	BigDecimal creditCard, BigDecimal weekend, String userId) {

    public static ListVariableTaxOutput from(final VariableTax aTax) {
	return new ListVariableTaxOutput(aTax.getId().getValue(),
		aTax.getDentalShop(), aTax.getProsthetist(),
		aTax.getTravel(), aTax.getCreditCard(),
		aTax.getWeekend(), aTax.getUser().getId().getValue());
    }

}
