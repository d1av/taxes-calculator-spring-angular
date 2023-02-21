package com.taxes.calculator.infrastructure.user.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateUserRequest(
	@JsonProperty("id") String id,
	@JsonProperty("authority") String name,
	@JsonProperty("password") String password,
	@JsonProperty("active") String active,
	@JsonProperty("roles") Set<String> roles) {

}
