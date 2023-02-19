package com.taxes.calculator.application.hourvalue.create;

import java.math.BigDecimal;

import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.user.User;

public record CreateHourValueCommand(BigDecimal expectedSalary,
	BigDecimal personalHourValue, Integer daysOfWork,
	String userId) {
    public static CreateHourValueCommand from(
	    final HourValue aHourValue) {
	return new CreateHourValueCommand(
		aHourValue.getExpectedSalary(),
		aHourValue.getPersonalHourValue(),
		aHourValue.getDaysOfWork(),
		aHourValue.getUser().getId().getValue());
    }

    public static CreateHourValueCommand from(BigDecimal aSalary,
	    BigDecimal aPersonalValue, Integer aDaysOfWork,
	    User user) {
	final var userId = user != null ? user.getId().getValue()
		: null;

	return new CreateHourValueCommand(aSalary, aPersonalValue,
		aDaysOfWork, userId);
    }
}
