package com.taxes.calculator.application.hourvalue.retrieve.get;

import java.math.BigDecimal;

import com.taxes.calculator.domain.hourvalue.HourValue;

public record GetHourValueByIdOutput(String id,
	BigDecimal expectedSalary, BigDecimal personalHourValue,
	Integer daysOfWork, String userId) {
    public static GetHourValueByIdOutput from(
	    final HourValue aHourValue) {
	return new GetHourValueByIdOutput(
		aHourValue.getId().getValue(),
		aHourValue.getExpectedSalary(),
		aHourValue.getPersonalHourValue(),
		aHourValue.getDaysOfWork(),
		aHourValue.getUser().getId().getValue());
    }
}
