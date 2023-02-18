package com.taxes.calculator.application.hourvalue.create;

import com.taxes.calculator.domain.hourvalue.HourValue;

public record CreateHourValueOutput(String id) {
    public static CreateHourValueOutput from(
	    final HourValue aHourValue) {
	return new CreateHourValueOutput(
		aHourValue.getId().getValue());
    }
}
