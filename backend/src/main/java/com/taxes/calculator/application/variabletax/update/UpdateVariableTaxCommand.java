package com.taxes.calculator.application.variabletax.update;

import java.math.BigDecimal;

import com.taxes.calculator.application.variabletax.create.CreateVariableTaxCommand;

public record UpdateVariableTaxCommand(String id,
	BigDecimal dentalShop, BigDecimal prosthetist,
	BigDecimal travel, BigDecimal creditCard, BigDecimal weekend,
	String userId) {
    public static UpdateVariableTaxCommand with(String id,
	    final BigDecimal dentalShop, final BigDecimal prosthetist,
	    final BigDecimal travel, final BigDecimal creditCard,
	    final BigDecimal weekend, String userId) {

	return new UpdateVariableTaxCommand(id, dentalShop,
		prosthetist, travel, creditCard, weekend, userId);
    }
}