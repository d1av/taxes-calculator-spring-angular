package com.taxes.calculator.infrastructure.variabletax.persistence;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxID;

@Entity(name = "VariableTax")
@Table(name = "variable_taxes")
public class VariableTaxJpaEntity {

    @Id
    private String id;

    @Column(name = "dental_shop", nullable = false)
    private BigDecimal dentalShop;

    @Column(name = "prosthetist", nullable = false)
    private BigDecimal prosthetist;

    @Column(name = "travel", nullable = false)
    private BigDecimal travel;

    @Column(name = "credit_card", nullable = false)
    private BigDecimal creditCard;

    @Column(name = "weekend", nullable = false)
    private BigDecimal weekend;

    @OneToMany(mappedBy = "variableTax", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserVariableTaxJpaEntity> user;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public VariableTaxJpaEntity() {
    }

    private VariableTaxJpaEntity(final String id,
	    final BigDecimal dentalShop, final BigDecimal prosthetist,
	    final BigDecimal travel, final BigDecimal creditCard,
	    final BigDecimal weekend, final String userId,
	    final Instant createdAt, final Instant updatedAt) {
	this.id = id;
	this.dentalShop = dentalShop;
	this.prosthetist = prosthetist;
	this.travel = travel;
	this.creditCard = creditCard;
	this.weekend = weekend;
	this.user = new HashSet<>();
	this.userId = userId;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
    }

    public static VariableTaxJpaEntity from(
	    final VariableTax variableTax) {
	String userId = variableTax.getUserId() != null
		? variableTax.getUserId().getValue()
		: null;
	final var anEntity = new VariableTaxJpaEntity(
		variableTax.getId().getValue(),
		variableTax.getDentalShop(),
		variableTax.getProsthetist(), variableTax.getTravel(),
		variableTax.getCreditCard(), variableTax.getWeekend(),
		userId,
		variableTax.getCreatedAt(),
		variableTax.getUpdatedAt());

	anEntity.addUser(variableTax.getUserId());

	return anEntity;
    }

    public VariableTax toAggregate() {
	return VariableTax.with(VariableTaxID.from(getId()),
		getDentalShop(), getProsthetist(), getTravel(),
		getCreditCard(), getWeekend(), getUserId(),
		getCreatedAt(), getUpdatedAt());
    }

    public void addUser(UserID userId) {
	if (userId != null && this.user.isEmpty()) {
	    this.user
		    .add(UserVariableTaxJpaEntity.from(userId, this));
	}
    }

    public UserID getUserId() {
	if (!this.user.isEmpty()) {
	    final var userId = this.user.stream()
		    .map(x -> x.getId().getUserId()).toList();
	    return UserID.from(userId.get(0));
	}
	return null;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public BigDecimal getDentalShop() {
	return dentalShop;
    }

    public void setDentalShop(BigDecimal dentalShop) {
	this.dentalShop = dentalShop;
    }

    public BigDecimal getProsthetist() {
	return prosthetist;
    }

    public void setProsthetist(BigDecimal prosthetist) {
	this.prosthetist = prosthetist;
    }

    public BigDecimal getTravel() {
	return travel;
    }

    public void setTravel(BigDecimal travel) {
	this.travel = travel;
    }

    public BigDecimal getCreditCard() {
	return creditCard;
    }

    public void setCreditCard(BigDecimal creditCard) {
	this.creditCard = creditCard;
    }

    public BigDecimal getWeekend() {
	return weekend;
    }

    public void setWeekend(BigDecimal weekend) {
	this.weekend = weekend;
    }

    public Set<UserVariableTaxJpaEntity> getUser() {
	return user;
    }

    public void setUser(Set<UserVariableTaxJpaEntity> user) {
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
