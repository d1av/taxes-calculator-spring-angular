package com.taxes.calculator.domain.variabletax;

import java.util.Objects;

import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.Validator;

public class VariableTaxValidator extends Validator {

    private VariableTax tax;

    public VariableTaxValidator(final VariableTax aTax,
	    final ValidationHandler aHandler) {
	super(aHandler);
	this.tax = aTax;
    }

    @Override
    public void validate() {
	checkValueConstraints(tax.getDentalShop(), "'dentalShop'");
	checkValueConstraints(tax.getProsthetist(), "'prosthetist'");
	checkValueConstraints(tax.getTravel(), "'travel'");
	checkValueConstraints(tax.getCreditCard(), "'creditCard'");
	checkValueConstraints(tax.getWeekend(), "'weekend'");
    }

    private <T> void checkValueConstraints(final T getField,
	    final String field) {
	final var fieldToValidate = getField;
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(
		    new Error("%s should not be null".formatted(field)));
	}
    }

}
