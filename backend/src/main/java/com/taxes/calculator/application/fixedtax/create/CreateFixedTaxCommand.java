package com.taxes.calculator.application.fixedtax.create;

import java.math.BigDecimal;

import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserID;

public record CreateFixedTaxCommand(BigDecimal regionalCouncil,
	BigDecimal taxOverWork, BigDecimal incomeTax,
	BigDecimal accountant, BigDecimal dentalShop,
	BigDecimal transport, BigDecimal food, BigDecimal education,
	BigDecimal otherFixedCosts, UserID user) {

    public static CreateFixedTaxCommand from(final FixedTax aTax) {
	return new CreateFixedTaxCommand(aTax.getRegionalCouncil(),
		aTax.getTaxOverWork(), aTax.getIncomeTax(),
		aTax.getAccountant(), aTax.getDentalShop(),
		aTax.getTransport(), aTax.getFood(),
		aTax.getEducation(), aTax.getOtherFixedCosts(),
		aTax.getUser());
    }

    public static CreateFixedTaxCommand with(
	    final BigDecimal regionalCouncil,
	    final BigDecimal taxOverWork, final BigDecimal incomeTax,
	    final BigDecimal accountant, final BigDecimal dentalShop,
	    final BigDecimal transport, final BigDecimal food,
	    final BigDecimal education,
	    final BigDecimal otherFixedCosts, final UserID user) {
	return new CreateFixedTaxCommand(regionalCouncil, taxOverWork,
		incomeTax, accountant, dentalShop, transport, food,
		education, otherFixedCosts, user);
    }

}
