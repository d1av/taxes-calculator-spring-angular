package com.taxes.calculator.application.fixedtax.update;

import java.math.BigDecimal;

import com.taxes.calculator.application.fixedtax.create.CreateFixedTaxCommand;
import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserID;

public record UpdateFixedTaxCommand(String id,
	BigDecimal regionalCouncil, BigDecimal taxOverWork,
	BigDecimal incomeTax, BigDecimal accountant,
	BigDecimal dentalShop, BigDecimal transport, BigDecimal food,
	BigDecimal education, BigDecimal otherFixedCosts, UserID user) {

    public static UpdateFixedTaxCommand from(final FixedTax aTax) {
	return new UpdateFixedTaxCommand(aTax.getId().getValue(),
		aTax.getRegionalCouncil(), aTax.getTaxOverWork(),
		aTax.getIncomeTax(), aTax.getAccountant(),
		aTax.getDentalShop(), aTax.getTransport(),
		aTax.getFood(), aTax.getEducation(),
		aTax.getOtherFixedCosts(), aTax.getUser());
    }

    public static UpdateFixedTaxCommand with(final String id,
	    final BigDecimal regionalCouncil,
	    final BigDecimal taxOverWork, final BigDecimal incomeTax,
	    final BigDecimal accountant, final BigDecimal dentalShop,
	    final BigDecimal transport, final BigDecimal food,
	    final BigDecimal education,
	    final BigDecimal otherFixedCosts, final UserID user) {
	return new UpdateFixedTaxCommand(id, regionalCouncil,
		taxOverWork, incomeTax, accountant, dentalShop,
		transport, food, education, otherFixedCosts, user);
    }

}
