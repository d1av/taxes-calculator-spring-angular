package com.taxes.calculator.application.role.update;

public record UpdateRoleCommand(String id, String authority) {
    public static UpdateRoleCommand with(final String id,
	    final String authority) {
	return new UpdateRoleCommand(id, authority);
    }
}
