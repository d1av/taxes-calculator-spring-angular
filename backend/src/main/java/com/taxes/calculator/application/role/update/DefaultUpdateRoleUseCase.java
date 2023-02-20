package com.taxes.calculator.application.role.update;

import java.util.Objects;
import java.util.function.Supplier;

import com.taxes.calculator.domain.Identifier;
import com.taxes.calculator.domain.exceptions.DomainException;
import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleGateway;
import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.handler.Notification;

public class DefaultUpdateRoleUseCase extends UpdateRoleUseCase {

    private final RoleGateway roleGateway;

    public DefaultUpdateRoleUseCase(final RoleGateway roleGateway) {
	this.roleGateway = Objects.requireNonNull(roleGateway);
    }

    @Override
    public UpdateRoleOutput execute(UpdateRoleCommand anIn) {
	final var anId = RoleID.from(anIn.id());
	final var anAuthority = anIn.authority();

	final var aRole = this.roleGateway.findById(anId)
		.orElseThrow(notFound(anId));

	final var notification = Notification.create();
	
	notification.validate(() -> aRole.update(anAuthority));

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Could not update Aggregate Role %s"
			    .formatted(anIn.id()),
		    notification);
	}

	return UpdateRoleOutput.from(this.roleGateway.update(aRole));
    }

    private static Supplier<DomainException> notFound(
	    final Identifier anId) {
	return () -> NotFoundException.with(Role.class, anId);
    }

}
