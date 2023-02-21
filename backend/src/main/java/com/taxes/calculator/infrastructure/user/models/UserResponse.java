package com.taxes.calculator.infrastructure.user.models;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.application.user.retrieve.get.GetUserOutput;
import com.taxes.calculator.application.user.retrieve.list.UserListOutput;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleID;

public record UserResponse(@JsonProperty("id") String id,
	@JsonProperty("name") String name,
	@JsonProperty("active") Boolean active,
	@JsonProperty("roles") Set<String> roles,
	@JsonProperty("updated_at") Instant updatedAt,
	@JsonProperty("created_at") Instant createdAt) {
    public static UserResponse from(final GetUserOutput aOutput) {
	return new UserResponse(aOutput.id(), aOutput.name(),
		aOutput.active(),
		aOutput.roles().stream().map(RoleID::getValue)
			.collect(Collectors.toSet()),
		aOutput.updatedAt(), aOutput.createdAt());
    }
}
