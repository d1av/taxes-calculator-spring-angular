package com.taxes.calculator.application.user.delete;

import java.util.Objects;

import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.user.UserID;

public class DefaultDeleteUserUseCase extends DeleteUserUseCase {

    private final UserGateway userGateway;

    public DefaultDeleteUserUseCase(final UserGateway userGateway) {
	this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public void execute(String onIn) {
	this.userGateway.deleteById(UserID.from(onIn));
    }

}
