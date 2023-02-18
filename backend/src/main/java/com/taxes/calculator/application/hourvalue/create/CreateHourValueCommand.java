package com.taxes.calculator.application.hourvalue.create;

import java.math.BigDecimal;

import com.taxes.calculator.domain.hourvalue.HourValue;

public record CreateHourValueCommand(BigDecimal expectedSalary,
	BigDecimal personalHourValue, Integer daysOfWork,
	String userId) {
    public static CreateHourValueCommand from(
	    final HourValue aHourValue
	    ) {
	return new CreateHourValueCommand(
		aHourValue.getExpectedSalary(),
		aHourValue.getPersonalHourValue(), 
		aHourValue.getDaysOfWork(), 
		aHourValue.getUser().getId().getValue());
    }
}
