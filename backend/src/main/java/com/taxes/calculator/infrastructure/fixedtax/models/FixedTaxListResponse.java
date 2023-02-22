package com.taxes.calculator.infrastructure.fixedtax.models;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.application.user.retrieve.list.UserListOutput;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleID;

public record FixedTaxListResponse(@JsonProperty("id") String id,
	@JsonProperty("name") String name,
	@JsonProperty("active") Boolean active,
	@JsonProperty("roles") Set<String> roles) {
    public static FixedTaxListResponse present(UserListOutput aRole) {
	final var rolesId = aRole.roles().stream()
		.map(RoleID::getValue).collect(Collectors.toSet());
	return new FixedTaxListResponse(aRole.id(), aRole.name(),
		aRole.active(), rolesId);
    }
}
