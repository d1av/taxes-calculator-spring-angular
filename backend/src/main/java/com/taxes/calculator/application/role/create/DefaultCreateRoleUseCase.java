package com.taxes.calculator.application.role.create;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleGateway;
import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.handler.Notification;

public class DefaultCreateRoleUseCase extends CreateRoleUseCase {

    private final RoleGateway roleGateway;

    public DefaultCreateRoleUseCase(final RoleGateway roleGateway) {
	this.roleGateway = Objects.requireNonNull(roleGateway);
    }

    @Override
    public CreateRoleOutput execute(CreateRoleCommand anIn) {
	final var anAuthority = anIn.authority();
	final var notification = Notification.create();

	if (roleGateway.findByAuthority(anAuthority).isPresent()) {
	    notification.append(
		    new Error("Role already exists with name: %s"
			    .formatted(anAuthority)));
	}	

	final var aRole = Role.newRole(anAuthority);
	notification.validate(() -> aRole);

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Could not create Aggregate Role", notification);
	}

	return CreateRoleOutput.from(this.roleGateway.create(aRole));
    }
}
