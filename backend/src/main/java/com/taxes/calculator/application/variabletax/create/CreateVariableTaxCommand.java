package com.taxes.calculator.application.variabletax.create;

import java.math.BigDecimal;

public record CreateVariableTaxCommand(BigDecimal dentalShop,
	BigDecimal prosthetist, BigDecimal travel,
	BigDecimal creditCard, BigDecimal weekend, String userId) {

    public static CreateVariableTaxCommand with(
	    final BigDecimal dentalShop, final BigDecimal prosthetist,
	    final BigDecimal travel, final BigDecimal creditCard,
	    final BigDecimal weekend, String userId) {

	return new CreateVariableTaxCommand(dentalShop, prosthetist,
		travel, creditCard, weekend, userId);
    }
}
