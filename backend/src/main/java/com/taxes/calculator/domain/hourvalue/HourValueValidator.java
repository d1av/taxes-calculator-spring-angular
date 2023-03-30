package com.taxes.calculator.domain.hourvalue;

import java.math.BigDecimal;
import java.util.Objects;

import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.Validator;

public class HourValueValidator extends Validator {

    private static Integer MIN_DAYS_WORK = 1;
    private static Integer MAX_DAYS_WORK = 31;

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
	final var minDays = BigDecimal.valueOf(MIN_DAYS_WORK)
		.doubleValue();
	final var maxDays = BigDecimal.valueOf(MAX_DAYS_WORK)
		.doubleValue();
	final var fieldToValidateNotNull = fieldToValidate != null
		? fieldToValidate
		: 0;

	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(
		    new Error("'daysOfWork' should not be null"));
	}

	if (fieldToValidateNotNull < minDays
		|| fieldToValidateNotNull > maxDays) {
	    this.validationHandler().append(new Error(
		    "'daysOfWork' should be a number between %s and %s"
			    .formatted(MIN_DAYS_WORK,
				    MAX_DAYS_WORK)));
	}

    }

    private void checkExpectedSalaryConstraints() {
	final var fieldToValidate = this.hourValue
		.getExpectedSalary();
	final String messageField = "'expectedSalary'";
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(new Error(
		    "%s should not be null".formatted(messageField)));
	}
	if (Objects.nonNull(fieldToValidate)) {
	    if (fieldToValidate.intValue() < 0) {
		this.validationHandler()
			.append(new Error("%s should not be negative"
				.formatted(messageField)));
	    } else if (fieldToValidate.intValue() > 999999) {
		this.validationHandler().append(
			new Error("%s should not be above 999.999"
				.formatted(messageField)));
	    }
	}
    }

    private void checkHourValueConstraints() {
	final var fieldToValidate = this.hourValue
		.getPersonalHourValue();
	final String messageField = "'hourValue'";
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(new Error(
		    "%s should not be null".formatted(messageField)));
	}
	if (Objects.nonNull(fieldToValidate)) {
	    if (fieldToValidate.intValue() < 0) {
		this.validationHandler()
			.append(new Error("%s should not be negative"
				.formatted(messageField)));
	    } else if (fieldToValidate.intValue() > 999999) {
		this.validationHandler().append(
			new Error("%s should not be above 999.999"
				.formatted(messageField)));
	    }
	}
    }
}
