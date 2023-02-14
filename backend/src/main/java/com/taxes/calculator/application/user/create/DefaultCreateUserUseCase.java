package com.taxes.calculator.application.user.create;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.taxes.calculator.application.utils.MapperUtils;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.role.Role;
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
	super();
	this.userGateway = Objects.requireNonNull(userGateway);
	this.roleGateway = Objects.requireNonNull(roleGateway);
    }

    @Override
    public CreateUserOutput execute(CreateUserCommand anIn) {

	final var aName = anIn.name();
	final var aPassword = anIn.password();
	final var aActive = anIn.isActive();
	final var roles = anIn.roles();

	final List<RoleID> ids = MapperUtils.toID(anIn.roles(),
		Role::getId);

	final var notification = Notification.create();
	notification.append(validateRoles(ids));
	final var aUser = User.newUser(aName, aPassword, aActive);
	aUser.addRoles(roles);
	notification.validate(() -> aUser);

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Could not create Aggregate User", notification);
	}

	

	return CreateUserOutput.from(this.userGateway.create(aUser));
    }

    private ValidationHandler validateRoles(List<RoleID> roles) {
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
		    new Error("Some categories could not be found: %s"
			    .formatted(missingIdsMessage)));
	}

	return notification;
    }

}
