package com.taxes.calculator.application.hourvalue.update;

import java.util.Objects;
import java.util.Optional;

import com.taxes.calculator.application.utils.EntityStatus;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.hourvalue.HourValueID;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.handler.Notification;

public class DefaultUpdateHourValueUseCase
	extends UpdateHourValueUseCase {

    private final HourValueGateway hourValueGateway;
    private final UserGateway userGateway;

    public DefaultUpdateHourValueUseCase(
	    final HourValueGateway hourValueGateway,
	    final UserGateway userGateway) {
	this.hourValueGateway = Objects
		.requireNonNull(hourValueGateway);
	this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public UpdateHourValueOutput execute(
	    UpdateHourValueCommand aCommand) {
	final var hourValueId = HourValueID.from(aCommand.id());
	final var expectedSalary = aCommand.expectedSalary();
	final var personalHourValue = aCommand.personalHourValue();
	final var daysOfWork = aCommand.daysOfWork();
	final var userId = aCommand.userId();

	final var notification = Notification.create();

	final var aHourValue = hourValueGateway.findById(hourValueId)
		.orElseThrow(EntityStatus.notFound(hourValueId,
			HourValue.class));

	notification.validate(() -> aHourValue.update(expectedSalary,
		personalHourValue, daysOfWork));

	if (userId != null) {
	    final var anId = UserID.from(userId);
	    final var aUser = userGateway.findById(anId);
	    notification.append(validateUser(aUser, anId));
	    aHourValue.addUser(getValidUser(aUser));
	} else {
	    notification.append(
		    new Error("User cannot be null on update"));
	}

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Could not update Aggregate Hour Value",
		    notification);
	}

	this.hourValueGateway.update(aHourValue);
	return UpdateHourValueOutput.from(aHourValue);
    }

    private UserID getValidUser(Optional<User> aUser) {
	if (aUser.isPresent()) {
	    return aUser.get().getId();
	}
	return null;
    }

    private ValidationHandler validateUser(Optional<User> aUser,
	    UserID userId) {
	final var notification = Notification.create();

	if (aUser.isEmpty()) {
	    notification.append(
		    new Error("Could find the user with id: %s"
			    .formatted(userId.getValue())));
	}

	return notification;
    }

}
