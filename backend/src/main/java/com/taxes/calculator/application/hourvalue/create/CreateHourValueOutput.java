package com.taxes.calculator.application.hourvalue.create;

import java.math.BigDecimal;

import com.taxes.calculator.domain.hourvalue.HourValue;

public record CreateHourValueOutput(String id,
	Integer daysOfWork, BigDecimal expectedSalary,
	BigDecimal personalHourValue, String userId) {
    public static CreateHourValueOutput from(
	    final HourValue aHourValue) {
	return new CreateHourValueOutput(
		aHourValue.getId().getValue(),
		aHourValue.getDaysOfWork(),
		aHourValue.getExpectedSalary(),
		aHourValue.getPersonalHourValue(),
		aHourValue.getUserId().getValue());
    }
}
