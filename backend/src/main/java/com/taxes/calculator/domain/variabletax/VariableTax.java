package com.taxes.calculator.domain.variabletax;

import java.math.BigDecimal;
import java.time.Instant;

import com.taxes.calculator.domain.AggregateRoot;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.utils.InstantUtils;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.handler.Notification;

public class VariableTax extends AggregateRoot<VariableTaxID> {

    private BigDecimal dentalShop;
    private BigDecimal prosthetist;
    private BigDecimal travel;
    private BigDecimal creditCard;
    private BigDecimal weekend;
    private UserID user;
    private Instant createdAt;
    private Instant updatedAt;

    private VariableTax(final VariableTaxID anId,
	    final BigDecimal anDentalShop,
	    final BigDecimal aProsthetist, final BigDecimal aTravel,
	    final BigDecimal aCreditCard, final BigDecimal aWeekend,
	    final UserID aUserId, final Instant createdAt,
	    final Instant updatedAt) {
	super(anId);
	this.dentalShop = anDentalShop;
	this.prosthetist = aProsthetist;
	this.travel = aTravel;
	this.creditCard = aCreditCard;
	this.weekend = aWeekend;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
	this.user = aUserId;

	selfValidate();
    }

    public static VariableTax with(final BigDecimal anDentalShop,
	    final BigDecimal aProsthetist, final BigDecimal aTravel,
	    final BigDecimal aCreditCard, final BigDecimal aWeekend,
	    final UserID aUser) {
	final var anId = VariableTaxID.unique();
	final var now = InstantUtils.now();
	return new VariableTax(anId, anDentalShop, aProsthetist,
		aTravel, aCreditCard, aWeekend, aUser, now, now);
    }

    public static VariableTax newVariableTax(
	    final BigDecimal anDentalShop,
	    final BigDecimal aProsthetist, final BigDecimal aTravel,
	    final BigDecimal aCreditCard, final BigDecimal aWeekend) {
	final var anId = VariableTaxID.unique();
	final var now = InstantUtils.now();
	return new VariableTax(anId, anDentalShop, aProsthetist,
		aTravel, aCreditCard, aWeekend, null, now, now);
    }

    public static VariableTax create(final BigDecimal anDentalShop,
	    final BigDecimal aProsthetist, final BigDecimal aTravel,
	    final BigDecimal aCreditCard, final BigDecimal aWeekend,
	    String userId) {
	final var anId = VariableTaxID.unique();
	final var now = InstantUtils.now();
	UserID userIdTreated = userId != null ? UserID.from(userId) : null;
	return new VariableTax(anId, anDentalShop, aProsthetist,
		aTravel, aCreditCard, aWeekend, userIdTreated, now, now);
    }

    public static VariableTax with(VariableTax aTax) {
	return new VariableTax(aTax.id, aTax.dentalShop,
		aTax.prosthetist, aTax.travel, aTax.creditCard,
		aTax.weekend, aTax.user, aTax.createdAt,
		aTax.updatedAt);
    }

    public static VariableTax with(final VariableTaxID anId,
	    final BigDecimal anDentalShop,
	    final BigDecimal aProsthetist, final BigDecimal aTravel,
	    final BigDecimal aCreditCard, final BigDecimal aWeekend,
	    final UserID aUserId, final Instant createdAt,
	    final Instant updatedAt) {
	return new VariableTax(anId, anDentalShop, aProsthetist,
		aTravel, aCreditCard, aWeekend, aUserId, createdAt,
		updatedAt);
    }

    public VariableTax update(final BigDecimal anDentalShop,
	    final BigDecimal aProsthetist, final BigDecimal aTravel,
	    final BigDecimal aCreditCard, final BigDecimal aWeekend) {

	this.dentalShop = anDentalShop;
	this.prosthetist = aProsthetist;
	this.creditCard = aCreditCard;
	this.travel = aTravel;
	this.weekend = aWeekend;
	this.updatedAt = InstantUtils.now();
	selfValidate();

	return this;
    }

    public VariableTax addUser(final UserID aUser) {
	if (this.user == null) {
	    this.user = aUser;
	    this.updatedAt = InstantUtils.now();
	}
	selfValidate();
	return this;
    }

    @Override
    public void validate(ValidationHandler handler) {
	new VariableTaxValidator(this, handler).validate();
    }

    private void selfValidate() {
	final var notification = Notification.create();
	validate(notification);

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Failed to validate Aggregate VariableTax",
		    notification);
	}
    }

    public BigDecimal getTotalVariableTax() {
	return this.dentalShop.add(this.prosthetist).add(this.travel)
		.add(this.creditCard).add(this.weekend);
    }

    public UserID getUserId() {
	return user;
    }

    public VariableTaxID getId() {
	return id;
    }

    public Instant getCreatedAt() {
	return createdAt;
    }

    public Instant getUpdatedAt() {
	return updatedAt;
    }

    public BigDecimal getDentalShop() {
	return dentalShop;
    }

    public BigDecimal getProsthetist() {
	return prosthetist;
    }

    public BigDecimal getTravel() {
	return travel;
    }

    public BigDecimal getCreditCard() {
	return creditCard;
    }

    public BigDecimal getWeekend() {
	return weekend;
    }
}
