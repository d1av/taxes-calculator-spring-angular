package com.taxes.calculator.infrastructure.role.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateRoleRequest(
	@JsonProperty("authority") String authority) {

}
