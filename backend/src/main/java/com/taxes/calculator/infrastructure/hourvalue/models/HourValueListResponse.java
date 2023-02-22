package com.taxes.calculator.infrastructure.hourvalue.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.application.hourvalue.retrieve.list.ListHourValueOutput;

public record HourValueListResponse(@JsonProperty("id") String id,
	@JsonProperty("expectedSalary") BigDecimal expectedSalary,
	@JsonProperty("personalHourValue") BigDecimal personalHourValue,
	@JsonProperty("daysOfWork") Integer daysOfWork,
	@JsonProperty("user") String user) {
    public static HourValueListResponse present(
	    ListHourValueOutput aHour) {
	final var rolesId = aHour.userId();
	return new HourValueListResponse(aHour.id(),
		aHour.expectedSalary(), aHour.personalHourValue(),
		aHour.daysOfWork(), aHour.userId());
    }
}
