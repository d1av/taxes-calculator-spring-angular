package com.taxes.calculator.application.totaltax;

import java.util.Objects;

import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxRepository;

public class DefaultTotalTaxUseCase extends TotalTaxUseCase {

    private final TotalTaxRepository totalTaxRepository;

    public DefaultTotalTaxUseCase(
	    TotalTaxRepository totalTaxRepository) {
	this.totalTaxRepository = Objects
		.requireNonNull(totalTaxRepository);
    }

    @Override
    public TotalTaxOutput execute(String anIn) {
	return TotalTaxOutput.with(totalTaxRepository
		.findByUserId(anIn)
		.orElseThrow(() -> NotificationException
			.with(new Error(
				"Please, try creating a new fields"))));
    }

}
