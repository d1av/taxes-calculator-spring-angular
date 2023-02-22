package com.taxes.calculator.application.fixedtax.retrieve.list;

import java.math.BigDecimal;
import java.util.Optional;

import com.taxes.calculator.domain.fixedtax.FixedTax;

public record ListFixedTaxOutput(String id,
	BigDecimal regionalCouncil, BigDecimal taxOverWork,
	BigDecimal incomeTax, BigDecimal accountant,
	BigDecimal dentalShop, BigDecimal transport, BigDecimal food,
	BigDecimal education, BigDecimal otherFixedCosts,
	String userId) {
    public static ListFixedTaxOutput from(final FixedTax aTax) {
	final var userIdString = Optional.ofNullable(aTax.getUser())
		.orElse(null);

	return new ListFixedTaxOutput(aTax.getId().getValue(),
		aTax.getRegionalCouncil(), aTax.getTaxOverWork(),
		aTax.getIncomeTax(), aTax.getAccountant(),
		aTax.getDentalShop(), aTax.getTransport(),
		aTax.getFood(), aTax.getEducation(),
		aTax.getOtherFixedCosts(), userIdString == null ? null
			: userIdString.getValue());
    }
}
