package com.taxes.calculator.application.hourvalue.create;

import java.util.Objects;
import java.util.Optional;

import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.handler.Notification;

public class DefaultCreateHourValueUseCase
	extends CreateHourValueUseCase {

    private final HourValueGateway hourValueGateway;
    private final UserGateway userGateway;

    public DefaultCreateHourValueUseCase(
	    final HourValueGateway hourValueGateway,
	    final UserGateway userGateway) {
	this.hourValueGateway = Objects
		.requireNonNull(hourValueGateway);
	this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public CreateHourValueOutput execute(
	    CreateHourValueCommand aCommand) {
	final var expectedSalary = aCommand.expectedSalary();
	final var personalHourValue = aCommand.personalHourValue();
	final var daysOfWork = aCommand.daysOfWork();
	final var userId = aCommand.userId();

	final var notification = Notification.create();

	final var aHourValue = HourValue.newHourValue(expectedSalary,
		personalHourValue, daysOfWork);

	notification.validate(() -> aHourValue);

	if (userId != null) {
	    final var anId = UserID.from(userId);
	    final var aUser = userGateway.findById(anId);

	    notification.append(validateUser(aUser, anId));
	    aHourValue.addUser(foundedUser(aUser));
	}

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Could not create Aggregate Hour Value",
		    notification);
	}

	return CreateHourValueOutput
		.from(this.hourValueGateway.create(aHourValue));
    }

    private ValidationHandler validateUser(Optional<User> aUser,
	    UserID aUserId) {
	final var notification = Notification.create();

	if (aUser.isEmpty()) {
	    notification.append(
		    new Error("Could find the user with id: %s"
			    .formatted(aUserId.getValue())));
	}

	return notification;
    }

    private User foundedUser(Optional<User> aUser) {
	if (aUser.isPresent()) {
	    return aUser.get();
	}
	return null;
    }

}
