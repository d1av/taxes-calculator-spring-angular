package com.taxes.calculator.infrastructure.auth.models;

import java.time.Instant;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterResponse(@JsonProperty("message") String message,
	@JsonProperty("id") String id,
	@JsonProperty("name") String name,
	@JsonProperty("createdAt") Instant createdAt,
	@JsonProperty("roles") Set<String> roles) {
    public static RegisterResponse from(RegisterOutput output) {
	return new RegisterResponse("User created successfully!",
		output.id(),
		output.name(), output.createdAt(), output.roles());
    }
}
