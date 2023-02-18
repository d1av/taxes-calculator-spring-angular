package com.taxes.calculator.application.role.update;

import com.taxes.calculator.domain.role.Role;

public record UpdateRoleOutput(String id) {
    public static UpdateRoleOutput from(final Role aRole) {
	return new UpdateRoleOutput(aRole.getId().getValue());
    }
}
