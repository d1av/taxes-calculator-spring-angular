package com.taxes.calculator.application.fixedtax.update;

import java.util.List;
import java.util.Objects;

import com.taxes.calculator.application.utils.EntityStatus;
import com.taxes.calculator.domain.exceptions.DomainException;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.fixedtax.FixedTaxID;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.handler.Notification;
import com.taxes.calculator.domain.variabletax.VariableTax;

public class DefaultUpdateFixedTaxUseCase
	extends UpdateFixedTaxUseCase {

    private final FixedTaxGateway fixedTaxGateway;
    private final UserGateway userGateway;

    public DefaultUpdateFixedTaxUseCase(
	    final FixedTaxGateway fixedTaxGateway,
	    final UserGateway userGateway) {
	this.fixedTaxGateway = Objects
		.requireNonNull(fixedTaxGateway);
	this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public UpdateFixedTaxOutput execute(
	    final UpdateFixedTaxCommand aTax) {
	final var fixedTaxId = FixedTaxID.from(aTax.id());
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

	final var aFixedTax = this.fixedTaxGateway
		.findById(fixedTaxId)
		.orElseThrow(() -> new DomainException(
			"Failed to update Fixed Tax",
			List.of(new com.taxes.calculator.domain.validation.Error(
				"Fixed with ID %s was not found"
					.formatted(aTax.id())))));

	final var notification = Notification.create();

	if (user != null) {
	    notification.append(validateUser(user));
	    aFixedTax.addUser(user);
	} else {
	    notification.append(
		    new Error("User should not be null on update."));
	    throw new NotificationException(
		    "User should not be null on update.",
		    notification);

	}

	notification.validate(() -> aFixedTax.update(regionalCouncil,
		taxOverWork, incomeTax, accountant, dentalShop,
		transport, food, education, otherFixedCosts));

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Could not create Aggregate Fixed Tax",
		    notification);
	}

	return UpdateFixedTaxOutput
		.from(this.fixedTaxGateway.update(aFixedTax));
    }

    private ValidationHandler validateUser(UserID userId) {
	final String userIdString = userId != null ? userId.getValue()
		: null;
	final var notification = Notification.create();

	final var aUser = userGateway.findById(userIdString);

	if (aUser.isEmpty()) {
	    notification.append(
		    new Error("User could not be found with id: %s"
			    .formatted(userId.getValue())));
	}

	return notification;
    }

}
