package com.taxes.calculator.domain.fixedtax;

import java.util.Objects;

import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.Validator;

public class FixedTaxValidator extends Validator {

    private FixedTax tax;

    FixedTaxValidator(final FixedTax aTax, final ValidationHandler aHandler) {
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
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler()
		    .append(new Error("'regionalCouncil' should not be null"));
	}
    }

    private void checkTaxOverWorkConstraints() {
	final var fieldToValidate = this.tax.getTaxOverWork();
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler()
		    .append(new Error("'taxOverWork' should not be null"));
	}
    }

    private void checkIncomeTaxConstraints() {
	final var fieldToValidate = this.tax.getIncomeTax();
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler()
		    .append(new Error("'incomeTax' should not be null"));
	}
    }

    private void checkDentalShopConstraints() {
	final var fieldToValidate = this.tax.getDentalShop();
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler()
		    .append(new Error("'dentalShop' should not be null"));
	}
    }

    private void checkTransportConstraints() {
	final var fieldToValidate = this.tax.getTransport();
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler()
		    .append(new Error("'transport' should not be null"));
	}
    }

    private void checkAccountantConstraints() {
	final var fieldToValidate = this.tax.getAccountant();
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler()
		    .append(new Error("'accountant' should not be null"));
	}
    }

    private void checkFoodConstraints() {
	final var fieldToValidate = this.tax.getFood();
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler()
		    .append(new Error("'food' should not be null"));
	}
    }

    private void checkEducationConstraints() {
	final var fieldToValidate = this.tax.getEducation();
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler()
		    .append(new Error("'education' should not be null"));
	}
    }

    private void checkOtherFixedCostsConstraints() {
	final var fieldToValidate = this.tax.getOtherFixedCosts();
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler()
		    .append(new Error("'otherFixedCosts' should not be null"));
	}
    }

}
