package com.taxes.calculator.infrastructure.role.presenters;

import com.taxes.calculator.application.role.retrieve.get.RoleByIdOutput;
import com.taxes.calculator.infrastructure.role.models.RoleResponse;

public interface RoleApiPresenter {
    static RoleResponse present(final RoleByIdOutput output) {
	return new RoleResponse(output.id(), output.authority(),
		output.createdAt(), output.updatedAt());
    }
}
