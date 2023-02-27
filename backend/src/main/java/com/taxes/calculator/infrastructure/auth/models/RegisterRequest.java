package com.taxes.calculator.infrastructure.auth.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterRequest(@JsonProperty("name") String name,
	@JsonProperty("password") String password) {

}
