package com.taxes.calculator.infrastructure.hourvalue.models;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateHourValueRequest(
	@JsonProperty("id") String id,
	@JsonProperty("expectedSalary") BigDecimal expectedSalary,
	@JsonProperty("personalHourValue") BigDecimal personalHourValue,
	@JsonProperty("daysOfWork") Integer daysOfWork,
	@JsonProperty("user") String user) {

}
