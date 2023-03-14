package com.taxes.calculator.infrastructure.hourvalue.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateHourValueRequest(
	@JsonProperty("expectedSalary") BigDecimal expectedSalary,
	@JsonProperty("personalHourValue") BigDecimal personalHourValue,
	@JsonProperty("daysOfWork") Integer daysOfWork,
	@JsonProperty("userId") String userId) {

}
