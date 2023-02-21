package com.taxes.calculator.infrastructure.user.models;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.application.user.retrieve.list.UserListOutput;
import com.taxes.calculator.domain.role.Role;

public record UserResponse(@JsonProperty("id") String id,
	@JsonProperty("name") String name,
	@JsonProperty("active") Boolean active,
	@JsonProperty("roles") Set<String> roles,
	@JsonProperty("updated_at") Instant updatedAt,
	@JsonProperty("created_at") Instant createdAt
	) {
}
