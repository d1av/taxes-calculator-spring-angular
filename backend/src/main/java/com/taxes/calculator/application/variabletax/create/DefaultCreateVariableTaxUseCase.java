package com.taxes.calculator.application.variabletax.create;

import java.util.Objects;
import java.util.Optional;

import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.validation.handler.Notification;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;

public class DefaultCreateVariableTaxUseCase
	extends CreateVariableTaxUseCase {

    private final VariableTaxGateway variableTaxGateway;

    private final UserGateway userGateway;

    public DefaultCreateVariableTaxUseCase(
	    final VariableTaxGateway variableTaxGateway,
	    final UserGateway userGateway) {
	this.variableTaxGateway = Objects
		.requireNonNull(variableTaxGateway);
	this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public CreateVariableTaxOutput execute(
	    CreateVariableTaxCommand anIn) {
	final var dentalShop = anIn.dentalShop();
	final var prosthetist = anIn.prosthetist();
	final var travel = anIn.travel();
	final var creditCard = anIn.creditCard();
	final var weekend = anIn.weekend();
	final UserID userID = UserID.from(anIn.userId());

	final var notification = Notification.create();

	final var aVariableTax = VariableTax.newVariableTax(
		dentalShop, prosthetist, travel, creditCard,
		weekend);

	notification.validate(() -> aVariableTax);

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Could not create Aggregate Variable Tax",
		    notification);
	}

	final Optional<User> aUser = userGateway
		.findById(userID);

	if (!aUser.isEmpty()) {
	    aVariableTax.addUser(aUser.get().getId());
	}

	return CreateVariableTaxOutput
		.from(variableTaxGateway.create(aVariableTax));
    }
}
