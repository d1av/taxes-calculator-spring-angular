package com.taxes.calculator.application.hourvalue.retrieve.get;

import java.math.BigDecimal;
import java.time.Instant;

import com.taxes.calculator.domain.hourvalue.HourValue;

public record GetHourValueByIdOutput(String id,
	BigDecimal expectedSalary, BigDecimal personalHourValue,
	Integer daysOfWork, String userId, Instant updatedAt,
	Instant createdAt) {
    public static GetHourValueByIdOutput from(
	    final HourValue aHourValue) {
	final var anUser = aHourValue.getUserId() != null
		? aHourValue.getUserId().getValue()
		: null;
	return new GetHourValueByIdOutput(
		aHourValue.getId().getValue(),
		aHourValue.getExpectedSalary(),
		aHourValue.getPersonalHourValue(),
		aHourValue.getDaysOfWork(), anUser,
		aHourValue.getUpdatedAt(), aHourValue.getCreatedAt());
    }
}
