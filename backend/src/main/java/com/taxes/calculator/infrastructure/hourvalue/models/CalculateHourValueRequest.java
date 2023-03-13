package com.taxes.calculator.infrastructure.hourvalue.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CalculateHourValueRequest(
	@JsonProperty("userId") String userId) {

}
