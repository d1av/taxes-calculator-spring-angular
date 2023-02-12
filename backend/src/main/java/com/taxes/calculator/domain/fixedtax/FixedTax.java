package com.taxes.calculator.domain.fixedtax;

import java.math.BigDecimal;
import java.time.Instant;

import com.taxes.calculator.domain.AggregateRoot;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.utils.InstantUtils;
import com.taxes.calculator.domain.validation.ValidationHandler;

public class FixedTax extends AggregateRoot<FixedTaxID> {

    private BigDecimal regionalCouncil;
    private BigDecimal taxOverWork;
    private BigDecimal incomeTax;
    private BigDecimal accountant;
    private BigDecimal dentalShop;
    private BigDecimal transport;
    private BigDecimal food;
    private BigDecimal education;
    private BigDecimal otherFixedCosts;
    private User user;
    private Instant createdAt;
    private Instant updatedAt;

    private FixedTax(final FixedTaxID anId, final BigDecimal aRegionalCouncil,
	    final BigDecimal aTaxOverWork, final BigDecimal aIncomeTax,
	    final BigDecimal aAccountant, final BigDecimal aDentalShop,
	    final BigDecimal aTransport, final BigDecimal aFood,
	    final BigDecimal aEducation, final BigDecimal aOtherFixedCosts,
	    final User aUser, final Instant aCreatedAt,
	    final Instant aUpdatedAt) {
	super(anId);
	this.regionalCouncil = aRegionalCouncil;
	this.taxOverWork = aTaxOverWork;
	this.incomeTax = aIncomeTax;
	this.accountant = aAccountant;
	this.dentalShop = aDentalShop;
	this.transport = aTransport;
	this.food = aFood;
	this.education = aEducation;
	this.otherFixedCosts = aOtherFixedCosts;
	this.user = aUser;
	this.createdAt = aCreatedAt;
	this.updatedAt = aUpdatedAt;
    }

    public static FixedTax newFixedTax(final BigDecimal aRegionalCouncil,
	    final BigDecimal aTaxOverWork, final BigDecimal aIncomeTax,
	    final BigDecimal aAccountant, final BigDecimal aDentalShop,
	    final BigDecimal aTransport, final BigDecimal aFood,
	    final BigDecimal aEducation, final BigDecimal aOtherFixedCosts,
	    final Instant aCreatedAt, final Instant aUpdatedAt) {

	final var anId = FixedTaxID.unique();

	return new FixedTax(anId, aRegionalCouncil, aTaxOverWork, aIncomeTax,
		aAccountant, aDentalShop, aTransport, aFood, aEducation,
		aOtherFixedCosts, null, aCreatedAt, aUpdatedAt);
    }

    public static FixedTax with(final BigDecimal aRegionalCouncil,
	    final BigDecimal aTaxOverWork, final BigDecimal aIncomeTax,
	    final BigDecimal aAccountant, final BigDecimal aDentalShop,
	    final BigDecimal aTransport, final BigDecimal aFood,
	    final BigDecimal aEducation, final BigDecimal aOtherFixedCosts,
	    final User aUser, final Instant aCreatedAt,
	    final Instant aUpdatedAt) {

	final var anId = FixedTaxID.unique();

	return new FixedTax(anId, aRegionalCouncil, aTaxOverWork, aIncomeTax,
		aAccountant, aDentalShop, aTransport, aFood, aEducation,
		aOtherFixedCosts, aUser, aCreatedAt, aUpdatedAt);
    }

    public FixedTax update(final BigDecimal aRegionalCouncil,
	    final BigDecimal aTaxOverWork, final BigDecimal aIncomeTax,
	    final BigDecimal aAccountant, final BigDecimal aDentalShop,
	    final BigDecimal aTransport, final BigDecimal aFood,
	    final BigDecimal aEducation, final BigDecimal aOtherFixedCosts) {
	this.updatedAt = InstantUtils.now();
	this.regionalCouncil = aRegionalCouncil;
	this.taxOverWork = aTaxOverWork;
	this.incomeTax = aIncomeTax;
	this.accountant = aAccountant;
	this.dentalShop = aDentalShop;
	this.transport = aTransport;
	this.food = aFood;
	this.education = aEducation;
    }

    @Override
    public void validate(ValidationHandler handler) {
	// TODO Auto-generated method stub

    }

    public BigDecimal getRegionalCouncil() {
	return regionalCouncil;
    }

    public BigDecimal getTaxOverWork() {
	return taxOverWork;
    }

    public BigDecimal getIncomeTax() {
	return incomeTax;
    }

    public BigDecimal getAccountant() {
	return accountant;
    }

    public BigDecimal getDentalShop() {
	return dentalShop;
    }

    public BigDecimal getTransport() {
	return transport;
    }

    public BigDecimal getFood() {
	return food;
    }

    public BigDecimal getEducation() {
	return education;
    }

    public BigDecimal getOtherFixedCosts() {
	return otherFixedCosts;
    }

    public Instant getCreatedAt() {
	return createdAt;
    }

    public Instant getUpdatedAt() {
	return updatedAt;
    }
}
