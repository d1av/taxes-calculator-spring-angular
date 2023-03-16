package com.taxes.calculator.infrastructure.fixedtax.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.application.fixedtax.update.UpdateFixedTaxOutput;
import com.taxes.calculator.application.user.retrieve.get.GetUserOutput;
import com.taxes.calculator.application.user.retrieve.list.UserListOutput;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.domain.user.UserID;

public record FixedTaxResponse(@JsonProperty("id") String id,
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
    public static FixedTaxResponse from(
	    final UpdateFixedTaxOutput aTax) {
	return new FixedTaxResponse(aTax.id(),
		aTax.regionalCouncil(), aTax.taxOverWork(),
		aTax.incomeTax(), aTax.accountant(),
		aTax.dentalShop(), aTax.transport(), aTax.food(),
		aTax.education(), aTax.otherFixedCosts(),
		aTax.user().getValue());
    }
}
