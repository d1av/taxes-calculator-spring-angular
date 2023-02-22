package com.taxes.calculator.infrastructure.hourvalue.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateHourValueRequest(
	@JsonProperty("expectedSalary") String expectedSalary,
	@JsonProperty("personalHourValue") String personalHourValue,
	@JsonProperty("daysOfWork") Integer daysOfWork,
	@JsonProperty("user") String user) {

}
