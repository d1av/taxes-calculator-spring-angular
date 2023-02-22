package com.taxes.calculator.infrastructure.fixedtax.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateFixedTaxRequest(
	@JsonProperty("id") String id,
	@JsonProperty("authority") String name,
	@JsonProperty("password") String password,
	@JsonProperty("active") Boolean active,
	@JsonProperty("roles") Set<String> roles) {

}
