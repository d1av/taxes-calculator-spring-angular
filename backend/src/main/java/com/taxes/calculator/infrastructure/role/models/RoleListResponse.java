package com.taxes.calculator.infrastructure.role.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.application.role.retrieve.list.RoleListOutput;

public record RoleListResponse(@JsonProperty("id") String id,
	@JsonProperty("authority") String authority) {
    public static RoleListResponse present(RoleListOutput aRole) {
	return new RoleListResponse(aRole.id(), aRole.authority());
    }
}
