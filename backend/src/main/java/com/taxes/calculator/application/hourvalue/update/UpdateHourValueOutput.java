package com.taxes.calculator.application.hourvalue.update;

import com.taxes.calculator.domain.hourvalue.HourValue;

public record UpdateHourValueOutput(String id) {
    public static UpdateHourValueOutput from(
	    final HourValue aHourValue) {
	return new UpdateHourValueOutput(
		aHourValue.getId().getValue());
    }
}
