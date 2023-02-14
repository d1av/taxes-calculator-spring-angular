package com.taxes.calculator.domain.hourvalue;

import java.math.BigDecimal;
import java.util.Objects;

import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.Validator;

public class HourValueValidator extends Validator {

    private static Double MIN_DAYS_WORK = 1d;
    private static Double MAX_DAYS_WORK = 31d;

    private HourValue hourValue;

    public HourValueValidator(final HourValue aHourValue,
	    final ValidationHandler aHandler) {
	super(aHandler);
	this.hourValue = aHourValue;
    }

    @Override
    public void validate() {
	checkDaysOfWorkConstraints();
	checkExpectedSalaryConstraints();
	checkHourValueConstraints();
    }

    private void checkDaysOfWorkConstraints() {
	final var fieldToValidate = this.hourValue.getDaysOfWork();
	final var minDays = BigDecimal.valueOf(MIN_DAYS_WORK).doubleValue();
	final var maxDays = BigDecimal.valueOf(MAX_DAYS_WORK).doubleValue();

	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler()
		    .append(new Error("'daysOfWork' should not be null"));
	} else {
	    final Double workDaysConverted = fieldToValidate.doubleValue();
	    if (workDaysConverted < minDays || workDaysConverted > maxDays) {
		this.validationHandler().append(new Error(
			"'daysOfWork' should be a number between %s and %s"
				.formatted(MIN_DAYS_WORK, MAX_DAYS_WORK)));
	    }
	}
    }

    private void checkExpectedSalaryConstraints() {
	final var fieldToValidate = this.hourValue.getExpectedSalary();
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler()
		    .append(new Error("'expectedSalary' should not be null"));
	}
    }

    private void checkHourValueConstraints() {
	final var fieldToValidate = this.hourValue.getHourValue();
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler()
		    .append(new Error("'hourValue' should not be null"));
	}
    }
}
