package com.taxes.calculator.application.user.create;

import java.util.Set;

import com.taxes.calculator.domain.role.Role;

public record CreateUserCommand(String name, String password,
	Boolean isActive, Set<Role> roles) {

    public static CreateUserCommand with(final String aName,
	    final String aPassword, final Boolean isActive,
	    final Set<Role> roles) {

	// active Status for new user, Default is true
	final var activeStatus = isActive != null ? isActive : true;

	return new CreateUserCommand(aName, aPassword, activeStatus,
		roles);

    }
}
