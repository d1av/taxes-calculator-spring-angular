package com.taxes.calculator.application.hourvalue.update;

import java.math.BigDecimal;

import com.taxes.calculator.domain.hourvalue.HourValue;

public record UpdateHourValueCommand(String id,
	BigDecimal expectedSalary, BigDecimal personalHourValue,
	Integer daysOfWork, String userId) {
    public static UpdateHourValueCommand from(
	    final HourValue aHourValue) {
	return new UpdateHourValueCommand(
		aHourValue.getId().getValue(),
		aHourValue.getExpectedSalary(),
		aHourValue.getPersonalHourValue(),
		aHourValue.getDaysOfWork(),
		aHourValue.getUser().getId().getValue());
    }
}
