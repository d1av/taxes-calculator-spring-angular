package com.taxes.calculator.infrastructure.variabletax.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.domain.user.UserID;

public record CreateVariableTaxRequest(
	@JsonProperty("dentalShop") BigDecimal dentalShop,
	@JsonProperty("prosthetist") BigDecimal prosthetist,
	@JsonProperty("travel") BigDecimal travel,
	@JsonProperty("creditCard") BigDecimal creditCard,
	@JsonProperty("weekend") BigDecimal weekend,
	@JsonProperty("userId") String userId) {

}
