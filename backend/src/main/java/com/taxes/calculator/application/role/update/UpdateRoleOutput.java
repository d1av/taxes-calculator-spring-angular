package com.taxes.calculator.application.role.update;

import com.taxes.calculator.domain.role.Role;

public record UpdateRoleOutput(String id) {
    public static UpdateRoleOutput from(final Role aRole) {
	final var role = aRole != null ? aRole.getId().getValue()
		: null;
	return new UpdateRoleOutput(role);
    }
}
