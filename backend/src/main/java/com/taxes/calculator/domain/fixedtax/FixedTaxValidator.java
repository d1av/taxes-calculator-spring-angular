package com.taxes.calculator.domain.fixedtax;

import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.Validator;

public class FixedTaxValidator extends Validator {

    private FixedTax tax;

    private FixedTaxValidator(final ValidationHandler aHandler,
	    final FixedTax aTax) {
	super(aHandler);
	this.tax = aTax;
    }

    @Override
    public void validate() {
	// TODO Auto-generated method stub

    }

}
