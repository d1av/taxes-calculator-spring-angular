package com.taxes.calculator.domain.user;

import java.util.Objects;

import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.Validator;

public class UserValidator extends Validator {

    private static final int NAME_MAX_LENGTH = 200;
    private static final int NAME_MIN_LENGTH = 6;
    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 255;
    private final User user;

    public UserValidator(final User user,
	    final ValidationHandler aHandler) {
	super(aHandler);
	this.user = user;
    }

    @Override
    public void validate() {
	checkNameConstraints();
	checkPasswordConstraints();
    }

    private void checkNameConstraints() {
	final var name = this.user.getName();
	final int length = name != null ? name.length() : 0;

	if (Objects.isNull(name)) {
	    this.validationHandler()
		    .append(new Error("'name' should not be null"));
	}
	if (name != null && name.trim().isBlank()) {
	    this.validationHandler()
		    .append(new Error("'name' should not be empty"));

	}


	if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
	    this.validationHandler().append(new Error(
		    "'name' must be between %s and %s characters"
			    .formatted(NAME_MIN_LENGTH,
				    NAME_MAX_LENGTH)));
	}

    }

    private void checkPasswordConstraints() {
	final var password = this.user.getPassword();
	final int length = password != null ? password.length() : 0;

	if (Objects.isNull(password)) {
	    this.validationHandler().append(
		    new Error("'password' should not be null"));
	}
	if (password != null && password.trim().isBlank()) {
	    this.validationHandler().append(
		    new Error("'password' should not be empty"));
	}
	if (length > PASSWORD_MAX_LENGTH
		|| length < PASSWORD_MIN_LENGTH) {
	    this.validationHandler().append(new Error(
		    "'password' must be between %s and %s characters"
			    .formatted(PASSWORD_MIN_LENGTH,
				    PASSWORD_MAX_LENGTH)));
	}

    }
}
