package com.taxes.calculator.application.fixedtax.create;

import java.math.BigDecimal;

import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.user.User;

public record CreateFixedTaxCommand(BigDecimal regionalCouncil,
	BigDecimal taxOverWork, BigDecimal incomeTax,
	BigDecimal accountant, BigDecimal dentalShop,
	BigDecimal transport, BigDecimal food, BigDecimal education,
	BigDecimal otherFixedCosts, User user) {

    public static CreateFixedTaxCommand from(final FixedTax aTax) {
	return new CreateFixedTaxCommand(aTax.getRegionalCouncil(),
		aTax.getTaxOverWork(), aTax.getIncomeTax(),
		aTax.getAccountant(), aTax.getDentalShop(),
		aTax.getTransport(), aTax.getFood(),
		aTax.getEducation(), aTax.getOtherFixedCosts(),
		aTax.getUser());
    }

}
