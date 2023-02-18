package com.taxes.calculator.application.hourvalue.retrieve.get;

import java.util.Objects;

import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.hourvalue.HourValueID;

public class DefaultGetHourValueByIdUseCase
	extends GetHourValueByIdUseCase {

    private final HourValueGateway hourValueGateway;

    public DefaultGetHourValueByIdUseCase(
	    final HourValueGateway hourValueGateway) {
	this.hourValueGateway = Objects
		.requireNonNull(hourValueGateway);
    }

    @Override
    public GetHourValueByIdOutput execute(String anIn) {
	final var anId = HourValueID.from(anIn);

	return this.hourValueGateway.findById(anId)
		.map(GetHourValueByIdOutput::from)
		.orElseThrow(() -> NotFoundException
			.with(HourValue.class, anId));
    }

}
