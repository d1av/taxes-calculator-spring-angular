package com.taxes.calculator.infrastructure.user.presenters;

import java.util.Set;
import java.util.stream.Collectors;

import com.taxes.calculator.application.user.retrieve.get.GetUserOutput;
import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.infrastructure.user.models.UserResponse;

public interface UserApiPresenter {
    static UserResponse present(final GetUserOutput output) {
	final Set<String> roleIds = output.roles().stream()
		.map(RoleID::getValue).collect(Collectors.toSet());
	return new UserResponse(output.id(), output.name(),
		output.active(), roleIds, output.updatedAt(),
		output.createdAt());
    }
}
