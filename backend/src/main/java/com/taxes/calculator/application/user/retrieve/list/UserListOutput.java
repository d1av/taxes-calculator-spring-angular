package com.taxes.calculator.application.user.retrieve.list;

import java.time.Instant;
import java.util.Set;

import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.user.User;

public record UserListOutput(String id, String name, Boolean active,
	Set<Role> roles, Instant createdAt) {
    public static UserListOutput from(final User aUser) {
	return new UserListOutput(aUser.getId().getValue(),
		aUser.getName(), aUser.getActive(), aUser.getRoles(),
		aUser.getCreatedAt());
    }
}