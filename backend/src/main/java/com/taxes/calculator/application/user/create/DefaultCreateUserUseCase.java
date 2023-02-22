package com.taxes.calculator.application.user.create;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.role.RoleGateway;
import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.handler.Notification;

public class DefaultCreateUserUseCase extends CreateUserUseCase {

    private final UserGateway userGateway;
    private final RoleGateway roleGateway;

    public DefaultCreateUserUseCase(final UserGateway userGateway,
	    final RoleGateway roleGateway) {
	this.userGateway = Objects.requireNonNull(userGateway);
	this.roleGateway = Objects.requireNonNull(roleGateway);
    }

    @Override
    public CreateUserOutput execute(CreateUserCommand anIn) {

	final var aName = anIn.name();
	final var aPassword = anIn.password();
	final var aActive = anIn.isActive();
	final var roles = anIn.rolesId();

	final Set<RoleID> ids = roles != null ? roles.stream()
		.map(RoleID::from).collect(Collectors.toSet()) : null;

	final var notification = Notification.create();
	notification.append(validateRoles(ids));
	final var aUser = User.newUser(aName, aPassword, aActive);
	if (ids != null) {
	    Set<RoleID> retrievedRoles = roleGateway.existsByIds(ids);

	    if (!retrievedRoles.containsAll(ids)) {
		throw NotificationException
			.with(new Error("Roles ids are invalid"));
	    }
	    aUser.addRoles(ids);
	}
	notification.validate(() -> aUser);

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Could not create Aggregate User", notification);
	}

	return CreateUserOutput.from(this.userGateway.create(aUser));
    }

    private ValidationHandler validateRoles(Set<RoleID> roles) {
	final var notification = Notification.create();
	if (roles == null || roles.isEmpty())
	    return notification;

	final var retrievedIds = roleGateway.existsByIds(roles);

	if (roles.size() != retrievedIds.size()) {
	    final var missingIds = new ArrayList<>(roles);
	    missingIds.removeAll(retrievedIds);

	    final var missingIdsMessage = missingIds.stream()
		    .map(RoleID::getValue)
		    .collect(Collectors.joining(", "));

	    notification.append(
		    new Error("Some roles could not be found: %s"
			    .formatted(missingIdsMessage)));
	}

	return notification;
    }

}
