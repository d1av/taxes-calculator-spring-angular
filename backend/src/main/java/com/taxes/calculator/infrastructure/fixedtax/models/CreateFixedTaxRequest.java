package com.taxes.calculator.infrastructure.fixedtax.models;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.domain.user.UserID;

public record CreateFixedTaxRequest(
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

}
