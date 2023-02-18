package com.taxes.calculator.application.role.retrieve.list;

import com.taxes.calculator.domain.role.Role;

public record RoleListOutput(String id, String authority) {
    public static RoleListOutput from(final Role aRole) {
	return new RoleListOutput(aRole.getId().getValue(),
		aRole.getAuthority());
    }
}
