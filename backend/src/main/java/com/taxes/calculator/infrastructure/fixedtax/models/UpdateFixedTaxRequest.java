package com.taxes.calculator.infrastructure.fixedtax.models;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.application.fixedtax.retrieve.list.ListFixedTaxOutput;

public record UpdateFixedTaxRequest(@JsonProperty("id") String id,
	@JsonProperty("regionalCouncil") BigDecimal regionalCouncil,
	@JsonProperty("taxOverWork") BigDecimal taxOverWork,
	@JsonProperty("incomeTax") BigDecimal incomeTax,
	@JsonProperty("accountant") BigDecimal accountant,
	@JsonProperty("dentalShop") BigDecimal dentalShop,
	@JsonProperty("transport") BigDecimal transport,
	@JsonProperty("food") BigDecimal food,
	@JsonProperty("education") BigDecimal education,
	@JsonProperty("otherFixedCosts") BigDecimal otherFixedCosts,
	@JsonProperty("userId") String userId) {
    public static UpdateFixedTaxRequest present(
	    ListFixedTaxOutput aRole) {
	return new UpdateFixedTaxRequest(aRole.id(),
		aRole.regionalCouncil(), aRole.taxOverWork(),
		aRole.incomeTax(), aRole.accountant(),
		aRole.dentalShop(), aRole.transport(), aRole.food(),
		aRole.education(), aRole.otherFixedCosts(),
		aRole.userId());
    }

}
