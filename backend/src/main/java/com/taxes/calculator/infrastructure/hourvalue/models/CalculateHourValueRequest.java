package com.taxes.calculator.infrastructure.hourvalue.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CalculateHourValueRequest(
	@JsonProperty("fixedTaxId") String fixedTaxId,
	@JsonProperty("variableTaxId") String variableTaxId,
	@JsonProperty("hourValueId") String hourValueId,
	@JsonProperty("userId") String userId) {

}
