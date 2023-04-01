package com.taxes.calculator.application.variabletax.update;

import java.math.BigDecimal;
import java.util.Objects;

import com.taxes.calculator.application.utils.EntityStatus;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.handler.Notification;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxID;

public class DefaultUpdateVariableTaxUseCase
	extends UpdateVariableTaxUseCase {

    private final VariableTaxGateway variableTaxGateway;

    private final UserGateway userGateway;

    public DefaultUpdateVariableTaxUseCase(
	    final VariableTaxGateway variableTaxGateway,
	    final UserGateway userGateway) {
	this.variableTaxGateway = Objects
		.requireNonNull(variableTaxGateway);
	this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public UpdateVariableTaxOutput execute(
	    UpdateVariableTaxCommand anIn) {
	final var id = VariableTaxID.from(anIn.id());
	final BigDecimal dentalShop = anIn.dentalShop();
	final BigDecimal prosthetist = anIn.prosthetist();
	final BigDecimal travel = anIn.travel();
	final BigDecimal creditCard = anIn.creditCard();
	final BigDecimal weekend = anIn.weekend();
	final String userId = anIn.userId();

	final var notification = Notification.create();

	final var aTax = this.variableTaxGateway.findById(id)
		.orElseThrow(
			EntityStatus.notFound(id, VariableTax.class));

	notification.validate(() -> aTax.update(dentalShop,
		prosthetist, travel, creditCard, weekend));

	final var aUser = this.userGateway.findById(userId);

	if (aUser.isEmpty() || userId == null) {
	    notification.append(new Error(
		    "User could not be found: %s".formatted(userId)));
	}

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Could not update Aggregate VariableTax %s"
			    .formatted(anIn.id()),
		    notification);
	}

	return UpdateVariableTaxOutput
		.from(this.variableTaxGateway.update(aTax));

    }

}
