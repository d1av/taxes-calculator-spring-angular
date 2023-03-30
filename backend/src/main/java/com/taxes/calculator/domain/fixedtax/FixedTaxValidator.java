package com.taxes.calculator.domain.fixedtax;

import java.math.BigDecimal;
import java.util.Objects;

import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.Validator;

public class FixedTaxValidator extends Validator {

    private FixedTax tax;

    FixedTaxValidator(final FixedTax aTax,
	    final ValidationHandler aHandler) {
	super(aHandler);
	this.tax = aTax;
    }

    @Override
    public void validate() {
	checkRegionalCouncilConstraints();
	checkTaxOverWorkConstraints();
	checkIncomeTaxConstraints();
	checkAccountantConstraints();
	checkDentalShopConstraints();
	checkTransportConstraints();
	checkFoodConstraints();
	checkEducationConstraints();
	checkOtherFixedCostsConstraints();
    }

    private void checkRegionalCouncilConstraints() {
	final var fieldToValidate = this.tax.getRegionalCouncil();
	final String messageField = "'regionalCouncil'";

	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(new Error(
		    "%s should not be null".formatted(messageField)));
	}

	if (!(fieldToValidate instanceof BigDecimal)) {
	    this.validationHandler().append(new Error(
		    "%s should be a number".formatted(messageField)));
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

    private void checkTaxOverWorkConstraints() {
	final var fieldToValidate = this.tax.getTaxOverWork();
	final String messageField = "'taxOverWork'";

	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(new Error(
		    "%s should not be null".formatted(messageField)));
	}

	if (!(fieldToValidate instanceof BigDecimal)) {
	    this.validationHandler().append(new Error(
		    "%s should be a number".formatted(messageField)));
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

    private void checkIncomeTaxConstraints() {
	final var fieldToValidate = this.tax.getIncomeTax();
	final String messageField = "'incomeTax'";

	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(new Error(
		    "%s should not be null".formatted(messageField)));
	}

	if (!(fieldToValidate instanceof BigDecimal)) {
	    this.validationHandler().append(new Error(
		    "%s should be a number".formatted(messageField)));
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

    private void checkDentalShopConstraints() {
	final var fieldToValidate = this.tax.getDentalShop();
	final String messageField = "'dentalShop'";

	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(new Error(
		    "%s should not be null".formatted(messageField)));
	}

	if (!(fieldToValidate instanceof BigDecimal)) {
	    this.validationHandler().append(new Error(
		    "%s should be a number".formatted(messageField)));
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

    private void checkTransportConstraints() {
	final var fieldToValidate = this.tax.getTransport();
	final String messageField = "'transport'";

	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(new Error(
		    "%s should not be null".formatted(messageField)));
	}

	if (!(fieldToValidate instanceof BigDecimal)) {
	    this.validationHandler().append(new Error(
		    "%s should be a number".formatted(messageField)));
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

    private void checkAccountantConstraints() {
	final var fieldToValidate = this.tax.getAccountant();
	final String messageField = "'accountant'";

	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(new Error(
		    "%s should not be null".formatted(messageField)));
	}

	if (!(fieldToValidate instanceof BigDecimal)) {
	    this.validationHandler().append(new Error(
		    "%s should be a number".formatted(messageField)));
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

    private void checkFoodConstraints() {
	final var fieldToValidate = this.tax.getFood();
	final String messageField = "'food'";

	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(new Error(
		    "%s should not be null".formatted(messageField)));
	}

	if (!(fieldToValidate instanceof BigDecimal)) {
	    this.validationHandler().append(new Error(
		    "%s should be a number".formatted(messageField)));
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

    private void checkEducationConstraints() {
	final var fieldToValidate = this.tax.getEducation();
	final String messageField = "'education'";

	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(new Error(
		    "%s should not be null".formatted(messageField)));
	}

	if (!(fieldToValidate instanceof BigDecimal)) {
	    this.validationHandler().append(new Error(
		    "%s should be a number".formatted(messageField)));
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

    private void checkOtherFixedCostsConstraints() {
	final var fieldToValidate = this.tax.getOtherFixedCosts();
	final String messageField = "'otherFixedCosts'";

	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(new Error(
		    "%s should not be null".formatted(messageField)));
	}

	if (!(fieldToValidate instanceof BigDecimal)) {
	    this.validationHandler().append(new Error(
		    "%s should be a number".formatted(messageField)));
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
