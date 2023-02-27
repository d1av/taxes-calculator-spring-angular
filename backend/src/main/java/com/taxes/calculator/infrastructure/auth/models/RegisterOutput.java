package com.taxes.calculator.infrastructure.auth.models;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;

public record RegisterOutput(String id, String name, Instant createdAt,
	Set<String> roles) {
    public static RegisterOutput from(UserJpaEntity entity) {
	final var roles = entity.getRoles() != null ? entity.getRoles()
		.stream().map(x -> x.getId().getRoleId())
		.collect(Collectors.toSet()) : null;

	return new RegisterOutput(entity.getId(), entity.getName(),
		entity.getCreatedAt(), roles);
    }
}
