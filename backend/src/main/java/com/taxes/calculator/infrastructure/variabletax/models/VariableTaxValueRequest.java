package com.taxes.calculator.infrastructure.variabletax.models;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VariableTaxValueRequest(@JsonProperty("id") String id,
	@JsonProperty("dentalShop") BigDecimal dentalShop,
	@JsonProperty("prosthetist") BigDecimal prosthetist,
	@JsonProperty("travel") BigDecimal travel,
	@JsonProperty("creditCard") BigDecimal creditCard,
	@JsonProperty("weekend") BigDecimal weekend,
	@JsonProperty("userId") String userId) {

}
