package com.taxes.calculator.infrastructure.hourvalue.persistence;

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

import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.user.UserID;

@Entity(name = "HourValue")
@Table(name = "hour_values")
public class HourValueJpaEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(name = "expected_salary", nullable = false)
    private BigDecimal expectedSalary;

    @Column(name = "personal_hour_value", nullable = false)
    private BigDecimal personalHourValue;

    @Column(name = "days_of_work", nullable = false)
    private Integer daysOfWork;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserHourValueJpaEntity> user;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public HourValueJpaEntity() {
	super();
    }

    private HourValueJpaEntity(final String id,
	    final BigDecimal expectedSalary,
	    final BigDecimal personalHourValue,
	    final Integer daysOfWork, final Instant createdAt,
	    final Instant updatedAt) {
	this.id = id;
	this.expectedSalary = expectedSalary;
	this.personalHourValue = personalHourValue;
	this.daysOfWork = daysOfWork;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
	this.user = new HashSet<>();
    }

    public static HourValueJpaEntity from(final HourValue aHour) {
	final var entity = new HourValueJpaEntity(
		aHour.getId().getValue(), aHour.getExpectedSalary(),
		aHour.getPersonalHourValue(), aHour.getDaysOfWork(),
		aHour.getCreatedAt(), aHour.getUpdatedAt());

	entity.addUser(aHour.getUserId());
	return entity;
    }

    private void addUser(UserID userId) {
	this.user.add(UserHourValueJpaEntity.from(userId, this));	
    }

    public HourValue toAggregate() {
	return HourValue.with(getId(), getExpectedSalary(),
		getPersonalHourValue(), getDaysOfWork(),
		getCreatedAt(), getUpdatedAt(), getUserID());
    }

    public UserID getUserID() {
	if (!this.user.isEmpty()) {
	    return this.user.iterator().next().getHourValue()
		    .getUserID();
	}
	return null;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public BigDecimal getExpectedSalary() {
	return expectedSalary;
    }

    public void setExpectedSalary(BigDecimal expectedSalary) {
	this.expectedSalary = expectedSalary;
    }

    public BigDecimal getPersonalHourValue() {
	return personalHourValue;
    }

    public void setPersonalHourValue(BigDecimal personalHourValue) {
	this.personalHourValue = personalHourValue;
    }

    public Integer getDaysOfWork() {
	return daysOfWork;
    }

    public void setDaysOfWork(Integer daysOfWork) {
	this.daysOfWork = daysOfWork;
    }

    public Set<UserHourValueJpaEntity> getUser() {
	return user;
    }

    public void setUser(Set<UserHourValueJpaEntity> user) {
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
