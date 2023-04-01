package com.taxes.calculator.application.user.retrieve.get;

import java.util.Objects;

import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.user.UserID;

public class DefaultGetUserByIdUseCase extends GetUserByIdUseCase {

    private final UserGateway userGateway;

    public DefaultGetUserByIdUseCase(final UserGateway userGateway) {
	this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public GetUserOutput execute(String anId) {
	final var aUserId = UserID.from(anId);
	return this.userGateway.findById(anId)
		.map(GetUserOutput::from)
		.orElseThrow(() -> NotFoundException.with(User.class,
			aUserId));
    }

}
