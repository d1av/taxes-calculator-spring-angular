package com.taxes.calculator.application.role.retrieve.get;

import java.util.Objects;

import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleGateway;
import com.taxes.calculator.domain.role.RoleID;

public class DefaultGetRoleByIdUseCase extends GetRoleByIdUseCase {

    private final RoleGateway roleGateway;

    public DefaultGetRoleByIdUseCase(final RoleGateway roleGateway) {
	this.roleGateway = Objects.requireNonNull(roleGateway);
    }

    @Override
    public RoleByIdOutput execute(String anIn) {
	final var aRoleId = RoleID.from(anIn);
	return this.roleGateway.findById(aRoleId)
		.map(RoleByIdOutput::from)
		.orElseThrow(() -> NotFoundException.with(Role.class,
			aRoleId));
    }

}
