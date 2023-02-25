package com.taxes.calculator.application.hourvalue.calculate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

import org.flywaydb.core.internal.logging.slf4j.Slf4jLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.fixedtax.FixedTaxID;
import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.hourvalue.HourValueID;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.handler.Notification;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxID;

public class DefaultCalculateHourValueUseCase
	extends CalculateHourValueUseCase {

    private Integer HOURS_WORKED_PER_DAY = 8;

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

    private static Logger LOGGER = LoggerFactory
	    .getLogger(DefaultCalculateHourValueUseCase.class);

    @Override
    public CalculateHourValueOutput execute(
	    CalculateHourValueCommand anIn) {
	final var userId = anIn.userId();
	final var variableTaxId = anIn.variableTaxId();
	final var fixedTaxId = anIn.fixedTaxId();
	final var hourValueId = anIn.hourValueId();

	final var aVariableTax = variableTax(variableTaxId);
	final var aFixedTax = fixedTax(fixedTaxId);
	final var aHourValue = hourValue(hourValueId);

	checkUserOnValues(userId, aVariableTax, aFixedTax, aHourValue);

	final BigDecimal totalMonthlyCosts = aVariableTax
		.getTotalVariableTax().add(aFixedTax.getTotalFixedTax());

	final BigDecimal workedMonthHours = BigDecimal.valueOf(
		aHourValue.getDaysOfWork() * HOURS_WORKED_PER_DAY);

	final BigDecimal aSalary = aHourValue.getExpectedSalary();

	final BigDecimal expectedSalary = aSalary.intValue() < 0
		? BigDecimal.valueOf(0)
		: aSalary;

	final BigDecimal calculatedValuePerHour = (totalMonthlyCosts
		.add(expectedSalary)).divide(workedMonthHours);

	LOGGER.info(
		"Monthly Costs: {} - workedMonthHours: {} - expectedSalary: {} - calculatedValue: {}",
		totalMonthlyCosts, workedMonthHours, expectedSalary,
		calculatedValuePerHour);

	this.hourValueGateway.update(HourValue.with(
		aHourValue.getId().getValue(),
		aHourValue.getExpectedSalary(), calculatedValuePerHour,
		aHourValue.getDaysOfWork(), aHourValue.getCreatedAt(),
		aHourValue.getUpdatedAt(), aHourValue.getUserId()));

	return CalculateHourValueOutput.with(calculatedValuePerHour,
		workedMonthHours.intValue(), expectedSalary,
		totalMonthlyCosts, aHourValue, aVariableTax, aFixedTax);

    }

    private void checkUserOnValues(String userId, VariableTax aVariableTax,
	    FixedTax aFixedTax, HourValue aHourValue) {
	Notification notification = Notification.create();

	if (!Objects.equals(userId, aVariableTax.getUserId().getValue())) {
	    notification.append(
		    new Error("Wrong Variable Tax for User with id: %s"
			    .formatted(userId)));
	}

	if (!Objects.equals(userId, aFixedTax.getUser().getValue())) {
	    notification.append(
		    new Error("Wrong Fixed Tax for User with id: %s"
			    .formatted(userId)));
	}

	if (!Objects.equals(userId, aHourValue.getUserId().getValue())) {
	    notification.append(
		    new Error("Wrong Hour Value for User with id: %s"
			    .formatted(userId)));
	}

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Could not Calculate Hour Value", notification);
	}

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
