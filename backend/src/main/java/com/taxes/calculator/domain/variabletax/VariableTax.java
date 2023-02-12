package com.taxes.calculator.domain.variabletax;

import java.math.BigDecimal;
import java.time.Instant;

import com.taxes.calculator.domain.AggregateRoot;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.utils.InstantUtils;
import com.taxes.calculator.domain.validation.ValidationHandler;

public class VariableTax extends AggregateRoot<VariableTaxID> {

    private BigDecimal dentalShop;
    private BigDecimal prosthetist;
    private BigDecimal travel;
    private BigDecimal creditCard;
    private BigDecimal weekend;
    private Instant createdAt;
    private Instant updatedAt;
    private User user;

    private VariableTax(final VariableTaxID anId, final BigDecimal anDentalShop,
	    final BigDecimal aProsthetist, final BigDecimal aTravel,
	    final BigDecimal aCreditCard, final BigDecimal aWeekend,
	    final User aUser, final Instant createdAt,
	    final Instant updatedAt) {
	super(anId);
	this.dentalShop = anDentalShop;
	this.prosthetist = aProsthetist;
	this.travel = aTravel;
	this.creditCard = aCreditCard;
	this.weekend = aWeekend;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
	this.user = aUser;
    }

    public static VariableTax with(final BigDecimal anDentalShop,
	    final BigDecimal aProsthetist, final BigDecimal aTravel,
	    final User aUser, final BigDecimal aCreditCard,
	    final BigDecimal aWeekend) {
	final var anId = VariableTaxID.unique();
	final var now = InstantUtils.now();
	return new VariableTax(anId, anDentalShop, aProsthetist, aTravel,
		aCreditCard, aWeekend, aUser, now, now);
    }

    public static VariableTax newVariableTax(final BigDecimal anDentalShop,
	    final BigDecimal aProsthetist, final BigDecimal aTravel,
	    final BigDecimal aCreditCard, final BigDecimal aWeekend) {
	final var anId = VariableTaxID.unique();
	final var now = InstantUtils.now();
	return new VariableTax(anId, anDentalShop, aProsthetist, aTravel,
		aCreditCard, aWeekend, null, now, now);
    }

    public static VariableTax with(VariableTax aTax) {
	return new VariableTax(aTax.id, aTax.dentalShop, aTax.prosthetist,
		aTax.travel, aTax.creditCard, aTax.weekend, aTax.user,
		aTax.createdAt, aTax.updatedAt);
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

	return this;
    }

    public VariableTax addUser(final User aUser) {
	if (this.user == null) {
	    this.user = aUser;
	    this.updatedAt = InstantUtils.now();
	}

	return this;
    }

    @Override
    public void validate(ValidationHandler handler) {
	// TODO Auto-generated method stub

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
