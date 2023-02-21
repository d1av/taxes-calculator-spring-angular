package com.taxes.calculator.infrastructure.user.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateUserRequest(
	@JsonProperty("name") String name,
	@JsonProperty("password") String password,
	@JsonProperty("active") Boolean active,
	@JsonProperty("roles") Set<String> roles) {

}
