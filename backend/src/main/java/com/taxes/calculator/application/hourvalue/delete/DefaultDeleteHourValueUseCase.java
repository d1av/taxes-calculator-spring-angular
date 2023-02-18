package com.taxes.calculator.application.hourvalue.delete;

import java.util.Objects;

import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.hourvalue.HourValueID;

public class DefaultDeleteHourValueUseCase
	extends DeleteHourValueUseCase {

    private final HourValueGateway hourValueGateway;

    public DefaultDeleteHourValueUseCase(
	    final HourValueGateway hourValueGateway) {
	this.hourValueGateway = Objects
		.requireNonNull(hourValueGateway);
    }

    @Override
    public void execute(String onIn) {
	this.hourValueGateway.deleteById(HourValueID.from(onIn));

    }

}
