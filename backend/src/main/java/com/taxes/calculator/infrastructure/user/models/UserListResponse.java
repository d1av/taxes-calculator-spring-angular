package com.taxes.calculator.infrastructure.user.models;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.application.user.retrieve.list.UserListOutput;
import com.taxes.calculator.domain.role.Role;

public record UserListResponse(@JsonProperty("id") String id,
	@JsonProperty("name") String name,
	@JsonProperty("active") Boolean active,
	@JsonProperty("roles") Set<String> roles) {
    public static UserListResponse present(UserListOutput aRole) {
	Set<String> rolesId = Set.of();
	if (!aRole.roles().isEmpty() || aRole.roles() != null) {
	    rolesId = aRole.roles().stream().map(Role::getAuthority)
		    .collect(Collectors.toSet());
	} else {
	    rolesId = Set.of();
	}
	return new UserListResponse(aRole.id(), aRole.name(),
		aRole.active(), rolesId);
    }
}
