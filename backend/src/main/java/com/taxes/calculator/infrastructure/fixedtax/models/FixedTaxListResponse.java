package com.taxes.calculator.infrastructure.fixedtax.models;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.application.fixedtax.retrieve.list.ListFixedTaxOutput;
import com.taxes.calculator.application.user.retrieve.list.UserListOutput;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleID;

public record FixedTaxListResponse(@JsonProperty("id") String id,
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
    public static FixedTaxListResponse present(
	    ListFixedTaxOutput aRole) {
	return new FixedTaxListResponse(aRole.id(),
		aRole.regionalCouncil(), aRole.taxOverWork(),
		aRole.incomeTax(), aRole.accountant(),
		aRole.dentalShop(), aRole.transport(), aRole.food(),
		aRole.education(), aRole.otherFixedCosts(),
		aRole.userId());
    }
}
