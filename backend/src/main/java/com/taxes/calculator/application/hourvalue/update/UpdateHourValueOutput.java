package com.taxes.calculator.application.hourvalue.update;

import java.math.BigDecimal;

import com.taxes.calculator.domain.hourvalue.HourValue;

public record UpdateHourValueOutput(String id,
	Integer daysOfWork, BigDecimal expectedSalary,
	BigDecimal personalHourValue, String userId) {
    public static UpdateHourValueOutput from(
	    final HourValue aHour) {
	return new UpdateHourValueOutput(
		aHour.getId().getValue(), aHour.getDaysOfWork(),
		aHour.getExpectedSalary(),
		aHour.getPersonalHourValue(),
		aHour.getUserId().getValue());
    }
}
