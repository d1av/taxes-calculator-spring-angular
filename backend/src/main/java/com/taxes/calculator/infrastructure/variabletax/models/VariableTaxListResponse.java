package com.taxes.calculator.infrastructure.variabletax.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.application.hourvalue.retrieve.list.ListHourValueOutput;
import com.taxes.calculator.application.variabletax.retrieve.list.ListVariableTaxOutput;

public record VariableTaxListResponse(@JsonProperty("id") String id,
	@JsonProperty("dentalShop") BigDecimal dentalShop,
	@JsonProperty("prosthetist") BigDecimal prosthetist,
	@JsonProperty("travel") BigDecimal travel,
	@JsonProperty("creditCard") BigDecimal creditCard,
	@JsonProperty("weekend") BigDecimal weekend,
	@JsonProperty("userId") String userId) {
    public static VariableTaxListResponse present(
	    ListVariableTaxOutput aTax) {
	return new VariableTaxListResponse(aTax.id(),
		aTax.dentalShop(), aTax.prosthetist(), aTax.travel(),
		aTax.creditCard(), aTax.weekend(), aTax.userId());
    }
}
