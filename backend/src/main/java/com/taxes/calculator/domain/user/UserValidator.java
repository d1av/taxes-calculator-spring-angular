package com.taxes.calculator.domain.user;

import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.Validator;

public class UserValidator extends Validator {

    private static final int NAME_MAX_LENGTH = 200;
    private static final int NAME_MIN_LENGTH = 1;

    public UserValidator(ValidationHandler aHandler) {
        super(aHandler);
    }
}
