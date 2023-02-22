package com.taxes.calculator.infrastructure.fixedtax.persistence;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.user.UserID;

@Entity(name = "Fixed Tax")
@Table(name = "fixed_tax")
public class FixedTaxJpaEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "regional_council", nullable = false)
    private BigDecimal regionalCouncil;

    @Column(name = "tax_over_work", nullable = false)
    private BigDecimal taxOverWork;

    @Column(name = "income_tax", nullable = false)
    private BigDecimal incomeTax;

    @Column(name = "accountant", nullable = false)
    private BigDecimal accountant;

    @Column(name = "dental_shop", nullable = false)
    private BigDecimal dentalShop;

    @Column(name = "transport", nullable = false)
    private BigDecimal transport;

    @Column(name = "food", nullable = false)
    private BigDecimal food;

    @Column(name = "education", nullable = false)
    private BigDecimal education;

    @Column(name = "other_fixed_costs", nullable = false)
    private BigDecimal otherFixedCosts;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserFixedTaxJpaEntity user;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public FixedTaxJpaEntity() {
	super();
    }

    private FixedTaxJpaEntity(final BigDecimal regionalCouncil,
	    final BigDecimal taxOverWork, final BigDecimal incomeTax,
	    final BigDecimal accountant, final BigDecimal dentalShop,
	    final BigDecimal transport, final BigDecimal food,
	    final BigDecimal education,
	    final BigDecimal otherFixedCosts,
	    final UserFixedTaxJpaEntity user, final Instant createdAt,
	    final Instant updatedAt) {
	this.regionalCouncil = regionalCouncil;
	this.taxOverWork = taxOverWork;
	this.incomeTax = incomeTax;
	this.accountant = accountant;
	this.dentalShop = dentalShop;
	this.transport = transport;
	this.food = food;
	this.education = education;
	this.otherFixedCosts = otherFixedCosts;
	this.user = user;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
    }

    public static FixedTaxJpaEntity from(final FixedTax aTax) {
	final var anEntity = new FixedTaxJpaEntity(
		aTax.getRegionalCouncil(), aTax.getTaxOverWork(),
		aTax.getIncomeTax(), aTax.getAccountant(),
		aTax.getDentalShop(), aTax.getTransport(),
		aTax.getFood(), aTax.getEducation(),
		aTax.getOtherFixedCosts(), null, aTax.getCreatedAt(),
		aTax.getUpdatedAt());

	anEntity.addUser(aTax.getUser());

	return anEntity;
    }

    private void addUser(UserID userId) {
	this.user=UserFixedTaxJpaEntity.from(userId, this);
    }

    public FixedTax toAggregate() {
	final var userId = getUser() != null
		? UserID.from(getUser().getId().getUserId())
		: null;
	return FixedTax.with(getRegionalCouncil(), getTaxOverWork(),
		getIncomeTax(), getAccountant(), getDentalShop(),
		getTransport(), getFood(), getEducation(),
		getOtherFixedCosts(), userId);
    }

    @Override
    public int hashCode() {
	return Objects.hash(createdAt, updatedAt, user);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	FixedTaxJpaEntity other = (FixedTaxJpaEntity) obj;
	return Objects.equals(createdAt, other.createdAt)
		&& Objects.equals(updatedAt, other.updatedAt)
		&& Objects.equals(user, other.user);
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public BigDecimal getRegionalCouncil() {
	return regionalCouncil;
    }

    public void setRegionalCouncil(BigDecimal regionalCouncil) {
	this.regionalCouncil = regionalCouncil;
    }

    public BigDecimal getTaxOverWork() {
	return taxOverWork;
    }

    public void setTaxOverWork(BigDecimal taxOverWork) {
	this.taxOverWork = taxOverWork;
    }

    public BigDecimal getIncomeTax() {
	return incomeTax;
    }

    public void setIncomeTax(BigDecimal incomeTax) {
	this.incomeTax = incomeTax;
    }

    public BigDecimal getAccountant() {
	return accountant;
    }

    public void setAccountant(BigDecimal accountant) {
	this.accountant = accountant;
    }

    public BigDecimal getDentalShop() {
	return dentalShop;
    }

    public void setDentalShop(BigDecimal dentalShop) {
	this.dentalShop = dentalShop;
    }

    public BigDecimal getTransport() {
	return transport;
    }

    public void setTransport(BigDecimal transport) {
	this.transport = transport;
    }

    public BigDecimal getFood() {
	return food;
    }

    public void setFood(BigDecimal food) {
	this.food = food;
    }

    public BigDecimal getEducation() {
	return education;
    }

    public void setEducation(BigDecimal education) {
	this.education = education;
    }

    public BigDecimal getOtherFixedCosts() {
	return otherFixedCosts;
    }

    public void setOtherFixedCosts(BigDecimal otherFixedCosts) {
	this.otherFixedCosts = otherFixedCosts;
    }

    public UserFixedTaxJpaEntity getUser() {
	return user;
    }

    public void setUser(UserFixedTaxJpaEntity user) {
	this.user = user;
    }

    public Instant getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
	this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
	return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
	this.updatedAt = updatedAt;
    }
}
