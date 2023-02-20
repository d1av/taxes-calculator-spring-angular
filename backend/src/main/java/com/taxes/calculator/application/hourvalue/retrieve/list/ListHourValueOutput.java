package com.taxes.calculator.application.hourvalue.retrieve.list;

import java.math.BigDecimal;

import com.taxes.calculator.domain.hourvalue.HourValue;

public record ListHourValueOutput(String id,
	BigDecimal expectedSalary, BigDecimal personalHourValue,
	Integer daysOfWork, String userId) {
    public static ListHourValueOutput from(final HourValue aHour) {
	final var userId = aHour.getUser() == null ? null
		: aHour.getUser().getId().getValue();
	return new ListHourValueOutput(aHour.getId().getValue(),
		aHour.getExpectedSalary(),
		aHour.getPersonalHourValue(), aHour.getDaysOfWork(),
		userId);
    }
}
