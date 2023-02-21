package com.taxes.calculator.infrastructure.user.presenters;

import static com.taxes.calculator.infrastructure.utils.EntityMapper.mapToEntity;

import java.util.Set;

import com.taxes.calculator.application.user.retrieve.get.GetUserOutput;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.infrastructure.user.models.UserResponse;

public interface UserApiPresenter {
    static UserResponse present(final GetUserOutput output) {
	final Set<String> authorityList = mapToEntity(output.roles(),
		Role::getAuthority);
	return new UserResponse(output.id(), output.name(),
		output.active(), authorityList, output.updatedAt(),
		output.createdAt());
    }
}
