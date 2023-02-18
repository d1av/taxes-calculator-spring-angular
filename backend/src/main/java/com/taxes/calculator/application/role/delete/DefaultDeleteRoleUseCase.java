package com.taxes.calculator.application.role.delete;

import java.util.Objects;

import com.taxes.calculator.domain.role.RoleGateway;
import com.taxes.calculator.domain.role.RoleID;

public class DefaultDeleteRoleUseCase extends DeleteRoleUseCase {

    private final RoleGateway roleGateway;

    public DefaultDeleteRoleUseCase(final RoleGateway roleGateway) {
	this.roleGateway = Objects.requireNonNull(roleGateway);
    }

    @Override
    public void execute(String onIn) {
	this.roleGateway.deleteById(RoleID.from(onIn));
    }

}
