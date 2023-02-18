package com.taxes.calculator.application.role.retrieve.get;

import java.time.Instant;

import com.taxes.calculator.domain.role.Role;

public record RoleByIdOutput(String id, String authority,
	Instant updatedAt, Instant createdAt) {
    
    public static RoleByIdOutput from(final Role aRole) {
	return new RoleByIdOutput(aRole.getId().getValue(),
		aRole.getAuthority(), aRole.getUpdatedAt(),
		aRole.getCreatedAt());
    }
}
