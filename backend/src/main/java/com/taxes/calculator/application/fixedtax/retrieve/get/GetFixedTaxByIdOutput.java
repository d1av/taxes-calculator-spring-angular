package com.taxes.calculator.application.fixedtax.retrieve.get;

import java.math.BigDecimal;
import java.util.Optional;

import com.taxes.calculator.domain.fixedtax.FixedTax;

public record GetFixedTaxByIdOutput(String id,
	BigDecimal regionalCouncil, BigDecimal taxOverWork,
	BigDecimal incomeTax, BigDecimal accountant,
	BigDecimal dentalShop, BigDecimal transport, BigDecimal food,
	BigDecimal education, BigDecimal otherFixedCosts,
	String userId) {
    public static GetFixedTaxByIdOutput from(final FixedTax aTax) {
	final var userIdString = Optional.ofNullable(aTax.getUser())
		.orElse(null);
	return new GetFixedTaxByIdOutput(aTax.getId().getValue(),
		aTax.getRegionalCouncil(), aTax.getTaxOverWork(),
		aTax.getIncomeTax(), aTax.getAccountant(),
		aTax.getDentalShop(), aTax.getTransport(),
		aTax.getFood(), aTax.getEducation(),
		aTax.getOtherFixedCosts(), userIdString == null ? null
			: userIdString.getValue());
    }
}
