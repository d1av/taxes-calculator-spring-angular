package com.taxes.calculator.application.role.create;

import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleID;

public record CreateRoleOutput(String id) {
    public static CreateRoleOutput from(final RoleID anId) {
	return new CreateRoleOutput(anId.getValue());
    }

    public static CreateRoleOutput from(final Role aRole) {
	return new CreateRoleOutput(aRole.getId().getValue());
    }
}
