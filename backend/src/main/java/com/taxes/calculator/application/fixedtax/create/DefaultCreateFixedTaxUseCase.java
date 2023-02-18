package com.taxes.calculator.application.fixedtax.create;

import java.util.Objects;

import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.handler.Notification;

public class DefaultCreateFixedTaxUseCase
	extends CreateFixedTaxUseCase {

    private final FixedTaxGateway fixedTaxGateway;
    private final UserGateway userGateway;

    public DefaultCreateFixedTaxUseCase(
	    final FixedTaxGateway fixedTaxGateway,
	    final UserGateway userGateway) {
	this.fixedTaxGateway = Objects
		.requireNonNull(fixedTaxGateway);
	this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public CreateFixedTaxOutput execute(
	    final CreateFixedTaxCommand aTax) {
	final var regionalCouncil = aTax.regionalCouncil();
	final var taxOverWork = aTax.taxOverWork();
	final var incomeTax = aTax.incomeTax();
	final var accountant = aTax.accountant();
	final var dentalShop = aTax.dentalShop();
	final var transport = aTax.transport();
	final var food = aTax.food();
	final var education = aTax.education();
	final var otherFixedCosts = aTax.otherFixedCosts();
	final var user = aTax.user();

	final var aFixedTax = FixedTax.newFixedTax(regionalCouncil,
		taxOverWork, incomeTax, accountant, dentalShop,
		transport, food, education, otherFixedCosts);

	final var notification = Notification.create();

	if (user != null) {
	    notification.append(validateUser(user));
	    aFixedTax.addUser(user);
	}

	notification.validate(() -> aFixedTax);

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Could not create Aggregate Fixed Tax",
		    notification);
	}

	return null;
    }

    private ValidationHandler validateUser(User user) {
	final var notification = Notification.create();

	final var aUser = userGateway.findById(user.getId());

	if (aUser.isEmpty()) {
	    notification.append(
		    new Error("User could not be found with id: %s"
			    .formatted(user.getId().getValue())));
	}

	return notification;
    }

}
