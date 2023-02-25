package com.taxes.calculator.application.hourvalue.calculate;

import java.math.BigDecimal;

import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.fixedtax.FixedTaxID;
import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.hourvalue.HourValueID;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxID;

public class DefaultCalculateHourValueUseCase
	extends CalculateHourValueUseCase {

    private final HourValueGateway hourValueGateway;
    private final FixedTaxGateway fixedTaxGateway;
    private final VariableTaxGateway variableTaxGateway;

    public DefaultCalculateHourValueUseCase(
	    final HourValueGateway hourValueGateway,
	    final FixedTaxGateway fixedTaxGateway,
	    final VariableTaxGateway variableTaxGateway) {
	this.hourValueGateway = hourValueGateway;
	this.fixedTaxGateway = fixedTaxGateway;
	this.variableTaxGateway = variableTaxGateway;
    }

    @Override
    public CalculateHourValueOutput execute(
	    CalculateHourValueCommand anIn) {
	final var variableTaxId = anIn.variableTaxId();
	final var fixedTaxId = anIn.fixedTaxId();
	final var hourValueId = anIn.hourValueId();

	final var aVariableTax = variableTax(variableTaxId);
	final var aFixedTax = fixedTax(fixedTaxId);
	final var aHourValue = hourValue(hourValueId);

	final BigDecimal totalMonthlyCosts = aVariableTax
		.getTotalVariableTax().add(aFixedTax.getTotalFixedTax());

	final BigDecimal workedMonthHours = BigDecimal
		.valueOf(aHourValue.getDaysOfWork() * 8);

	final BigDecimal calculatedValuePerHour = totalMonthlyCosts
		.divide(workedMonthHours);

	this.hourValueGateway.update(HourValue.with(
		aHourValue.getId().getValue(),
		aHourValue.getExpectedSalary(), calculatedValuePerHour,
		aHourValue.getDaysOfWork(), aHourValue.getCreatedAt(),
		aHourValue.getUpdatedAt(), aHourValue.getUserId()));

	return new CalculateHourValueOutput(calculatedValuePerHour,
		aHourValue.getDaysOfWork(), aFixedTax.getId().getValue(),
		aVariableTax.getId().getValue());

    }

    private VariableTax variableTax(final String variableTaxId) {
	return variableTaxGateway
		.findById(VariableTaxID.from(variableTaxId))
		.orElseThrow(() -> NotFoundException.with(
			new Error("Variable Tax not found with id: %s"
				.formatted(variableTaxId))));
    }

    private FixedTax fixedTax(final String fixedTaxId) {
	return fixedTaxGateway.findById(FixedTaxID.from(fixedTaxId))
		.orElseThrow(() -> NotFoundException
			.with(new Error("Fixed Tax not found with id: %s"
				.formatted(fixedTaxId))));
    }

    private HourValue hourValue(final String hourValueId) {
	return hourValueGateway.findById(HourValueID.from(hourValueId))
		.orElseThrow(() -> NotFoundException
			.with(new Error("Hour Value not found with id: %s"
				.formatted(hourValueId))));
    }

}
