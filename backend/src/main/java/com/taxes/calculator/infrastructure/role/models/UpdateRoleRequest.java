package com.taxes.calculator.infrastructure.role.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateRoleRequest(
	@JsonProperty("authority") String authority) {

}
