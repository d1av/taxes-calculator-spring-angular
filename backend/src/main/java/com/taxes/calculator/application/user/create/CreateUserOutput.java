package com.taxes.calculator.application.user.create;

import com.taxes.calculator.domain.exceptions.DomainException;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.validation.Error;

public record CreateUserOutput(String id) {

    public static CreateUserOutput from(final UserID anId) {
	if (anId.getValue() == null) {
	    throw DomainException.with(new Error(
		    "Problem creating Id at the application level"));
	}
	
	return new CreateUserOutput(anId.getValue());
    }

    public static CreateUserOutput from(final User aUser) {
	if (aUser.getId().getValue() == null) {
	    throw DomainException.with(new Error(
		    "Problem creating Id at the application level"));
	}

	final var anId = aUser.getId().getValue() != null
		? aUser.getId().getValue()
		: "null";
	return new CreateUserOutput(anId);
    }

}
