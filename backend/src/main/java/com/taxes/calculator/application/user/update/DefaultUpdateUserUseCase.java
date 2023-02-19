package com.taxes.calculator.application.user.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.taxes.calculator.application.utils.MapperUtils;
import com.taxes.calculator.domain.exceptions.DomainException;
import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleGateway;
import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.handler.Notification;

public class DefaultUpdateUserUseCase extends UpdateUserUseCase {

    private final UserGateway userGateway;
    private final RoleGateway roleGateway;

    public DefaultUpdateUserUseCase(final UserGateway userGateway,
	    final RoleGateway roleGateway) {
	this.userGateway = Objects.requireNonNull(userGateway);
	this.roleGateway = Objects.requireNonNull(roleGateway);
    }

    @Override
    public UpdateUserOutput execute(UpdateUserCommand anIn) {
	final var anId = UserID.from(anIn.id());
	final var aName = anIn.name();
	final var aPassword = anIn.password();
	final var aActive = anIn.isActive();
	final var roles = anIn.roles();

	final var actualUser = userGateway.findById(anId)
		.orElseThrow(notFoundException(anId));

	final Set<RoleID> ids = MapperUtils.toID(anIn.roles(),
		Role::getId);

	final var notification = Notification.create();
	notification.append(validateRoles(ids));
	actualUser.update(aName, aPassword, aActive, roles);
	notification.validate(() -> User.with(actualUser));

	actualUser.validate(notification);

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Could not update Aggregate User", notification);
	}

	this.userGateway.update(actualUser);
	return UpdateUserOutput.from(actualUser);
    }

    private Supplier<DomainException> notFoundException(UserID anId) {
	return () -> NotFoundException.with(User.class, anId);
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
		    new Error("Some categories could not be found: %s"
			    .formatted(missingIdsMessage)));
	}

	return notification;
    }
}
