package com.taxes.calculator.infrastructure.variabletax.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.application.hourvalue.retrieve.get.GetHourValueByIdOutput;
import com.taxes.calculator.application.user.retrieve.get.GetUserOutput;
import com.taxes.calculator.application.user.retrieve.list.UserListOutput;
import com.taxes.calculator.application.variabletax.retrieve.get.VariableTaxByIdOutput;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleID;

public record VariableTaxResponse(@JsonProperty("id") String id,
	@JsonProperty("dentalShop") BigDecimal dentalShop,
	@JsonProperty("prosthetist") BigDecimal prosthetist,
	@JsonProperty("travel") BigDecimal travel,
	@JsonProperty("creditCard") BigDecimal creditCard,
	@JsonProperty("weekend") BigDecimal weekend,
	@JsonProperty("userId") String userId,
	@JsonProperty("updatedAt") Instant updatedAt,
	@JsonProperty("createdAt") Instant createdAt) {
    public static VariableTaxResponse from(
	    final VariableTaxByIdOutput aOutput) {
	return new VariableTaxResponse(aOutput.id(),
		aOutput.dentalShop(), aOutput.prosthetist(),
		aOutput.travel(), aOutput.creditCard(),
		aOutput.weekend(), aOutput.userId(),
		aOutput.updatedAt(), aOutput.createdAt());
    }
}
