package com.taxes.calculator.application.hourvalue.create;

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

    private HourValueGateway hourValueGateway;
    private UserGateway userGateway;

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
	    notification.append(validateUser(aUser));

	    aHourValue.addUser(aUser.get());
	}

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Could not create Aggregate Hour Value",
		    notification);
	}

	return CreateHourValueOutput
		.from(this.hourValueGateway.create(aHourValue));
    }

    private ValidationHandler validateUser(Optional<User> aUser) {
	final var notification = Notification.create();

	if (aUser.isEmpty()) {
	    notification.append(
		    new Error("Could find the user with id: %s"
			    .formatted(aUser)));
	}

	return notification;
    }

}
