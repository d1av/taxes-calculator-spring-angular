package com.taxes.calculator.application.fixedtax.update;

import java.math.BigDecimal;

import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.user.User;

public record UpdateFixedTaxCommand(String id,
	BigDecimal regionalCouncil, BigDecimal taxOverWork,
	BigDecimal incomeTax, BigDecimal accountant,
	BigDecimal dentalShop, BigDecimal transport, BigDecimal food,
	BigDecimal education, BigDecimal otherFixedCosts, User user) {

    public static UpdateFixedTaxCommand from(final FixedTax aTax) {
	return new UpdateFixedTaxCommand(aTax.getId().getValue(),
		aTax.getRegionalCouncil(), aTax.getTaxOverWork(),
		aTax.getIncomeTax(), aTax.getAccountant(),
		aTax.getDentalShop(), aTax.getTransport(),
		aTax.getFood(), aTax.getEducation(),
		aTax.getOtherFixedCosts(), aTax.getUser());
    }

}
