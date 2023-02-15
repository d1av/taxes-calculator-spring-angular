package com.taxes.calculator.application.user.update;

import java.util.Set;

import com.taxes.calculator.domain.role.Role;

public record UpdateUserCommand(String id, String name,
	String password, Boolean isActive, Set<Role> roles) {

    public static UpdateUserCommand with(String anId,
	    final String aName, final String aPassword,
	    final Boolean isActive, final Set<Role> roles) {

	// active Status for new user, Default is true
	final var activeStatus = isActive != null ? isActive : true;

	return new UpdateUserCommand(anId, aName, aPassword,
		activeStatus, roles);
    }
}
