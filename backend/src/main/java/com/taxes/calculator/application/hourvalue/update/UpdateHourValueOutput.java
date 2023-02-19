package com.taxes.calculator.application.hourvalue.update;

import com.taxes.calculator.domain.hourvalue.HourValue;

public record UpdateHourValueOutput(String id) {
    public static UpdateHourValueOutput from(
	    final HourValue aHourValue) {
	final var hourValue = aHourValue != null
		? aHourValue.getId().getValue()
		: null;
	return new UpdateHourValueOutput(hourValue);
    }
}
