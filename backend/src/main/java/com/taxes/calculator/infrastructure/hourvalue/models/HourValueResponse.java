package com.taxes.calculator.infrastructure.hourvalue.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.application.hourvalue.retrieve.get.GetHourValueByIdOutput;
import com.taxes.calculator.application.user.retrieve.get.GetUserOutput;
import com.taxes.calculator.application.user.retrieve.list.UserListOutput;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleID;

public record HourValueResponse(
	@JsonProperty("id") String id,
	@JsonProperty("expectedSalary") BigDecimal expectedSalary,
	@JsonProperty("personalHourValue") BigDecimal personalHourValue,
	@JsonProperty("daysOfWork") Integer daysOfWork,
	@JsonProperty("user") String user,
	@JsonProperty("updatedAt") Instant updatedAt,
	@JsonProperty("createdAt") Instant createdAt
	) {
    public static HourValueResponse from(final GetHourValueByIdOutput aOutput) {
	return new HourValueResponse(
		aOutput.id(), 
		aOutput.expectedSalary(),
		aOutput.personalHourValue(),
		aOutput.daysOfWork(),
		aOutput.userId(),
		aOutput.updatedAt(), 
		aOutput.createdAt());
    }
}
