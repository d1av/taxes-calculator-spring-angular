package com.taxes.calculator.domain.variabletax;

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
	// TODO Auto-generated method stub

    }
    
    

}
