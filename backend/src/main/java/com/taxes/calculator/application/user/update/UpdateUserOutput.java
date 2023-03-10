package com.taxes.calculator.application.user.update;

import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserID;

public record UpdateUserOutput(String id) {

    public static UpdateUserOutput from(final User aUser) {
	return from(aUser.getId());
    }

    public static UpdateUserOutput from(final UserID anId) {
	return new UpdateUserOutput(anId.getValue());
    }
}
