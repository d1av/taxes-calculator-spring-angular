package com.taxes.calculator.domain.variabletax;

import java.util.Objects;

import com.taxes.calculator.domain.user.UserID;
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
	checkUserValueConstraints(tax.getUserId());
    }

 
    private void checkUserValueConstraints(UserID userId) {
	if (Objects.isNull(userId)) {
	    this.validationHandler().append(
		    new Error("%s should not be null".formatted("userId")));
	}
    }

    private <T extends Number> void checkValueConstraints(final T getField,
	    final String field) {
	final var fieldToValidate = getField;
	if (Objects.isNull(fieldToValidate)) {
	    this.validationHandler().append(
		    new Error("%s should not be null".formatted(field)));
	}
	if (Objects.nonNull(fieldToValidate)) {
	    if (fieldToValidate.intValue() < 0) {
		this.validationHandler()
			.append(new Error("%s should not be negative"
				.formatted(field)));
	    } else if (fieldToValidate.intValue() > 999999) {
		this.validationHandler().append(
			new Error("%s should not be above 999.999"
				.formatted(field)));
	    }
	}
    }

}
