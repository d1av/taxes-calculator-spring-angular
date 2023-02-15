package com.taxes.calculator.application.user.retrieve.get;

import java.time.Instant;
import java.util.Set;

import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.user.User;

public record GetUserOutput(String id, String name, Boolean active,
	Set<Role> roles, Instant createdAt) {
    public static GetUserOutput from(final User aUser) {
	return new GetUserOutput(aUser.getId().getValue(),
		aUser.getName(), aUser.getActive(), aUser.getRoles(),
		aUser.getCreatedAt());
    }
}