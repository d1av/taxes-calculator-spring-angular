package com.taxes.calculator.application.fixedtax.create;

import java.math.BigDecimal;

import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserID;

public record CreateFixedTaxOutput(String id,
	BigDecimal regionalCouncil, BigDecimal taxOverWork,
	BigDecimal incomeTax, BigDecimal accountant,
	BigDecimal dentalShop, BigDecimal transport,
	BigDecimal food, BigDecimal education,
	BigDecimal otherFixedCosts, String userId) {

    public static CreateFixedTaxOutput from(
	    final FixedTax aTax) {

	String user = aTax.getUser() != null
		? aTax.getUser().getValue()
		: null;

	return new CreateFixedTaxOutput(aTax.getId().getValue(),
		aTax.getRegionalCouncil(), aTax.getTaxOverWork(),
		aTax.getIncomeTax(), aTax.getAccountant(),
		aTax.getDentalShop(), aTax.getTransport(),
		aTax.getFood(), aTax.getEducation(),
		aTax.getOtherFixedCosts(),
		user);
    }

}