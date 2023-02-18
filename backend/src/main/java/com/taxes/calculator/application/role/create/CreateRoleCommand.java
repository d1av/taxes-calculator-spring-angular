package com.taxes.calculator.application.role.create;

public record CreateRoleCommand(String authority) {
    public static CreateRoleCommand with(final String aName) {
	return new CreateRoleCommand(aName);
    }
}
