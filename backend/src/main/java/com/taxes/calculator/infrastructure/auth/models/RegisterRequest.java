package com.taxes.calculator.infrastructure.auth.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.domain.user.User;

public record RegisterRequest(@JsonProperty("name") String name,
	@JsonProperty("password") String password) {
    public static RegisterRequest with(String name, String password) {
	User entity = User.newUser(name, password, true);
	return new RegisterRequest(entity.getName(),
		entity.getPassword());
    }
}
