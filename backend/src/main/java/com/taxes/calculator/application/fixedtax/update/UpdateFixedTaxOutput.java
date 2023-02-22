package com.taxes.calculator.application.fixedtax.update;

import java.math.BigDecimal;

import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserID;

public record UpdateFixedTaxOutput(String id,
	BigDecimal regionalCouncil, BigDecimal taxOverWork,
	BigDecimal incomeTax, BigDecimal accountant,
	BigDecimal dentalShop, BigDecimal transport, BigDecimal food,
	BigDecimal education, BigDecimal otherFixedCosts, UserID user) {

    public static UpdateFixedTaxOutput from(final FixedTax aTax) {
	return new UpdateFixedTaxOutput(aTax.getId().getValue(),
		aTax.getRegionalCouncil(), aTax.getTaxOverWork(),
		aTax.getIncomeTax(), aTax.getAccountant(),
		aTax.getDentalShop(), aTax.getTransport(),
		aTax.getFood(), aTax.getEducation(),
		aTax.getOtherFixedCosts(), aTax.getUser());
    }

}