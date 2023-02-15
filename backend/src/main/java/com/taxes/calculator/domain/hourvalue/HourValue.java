package com.taxes.calculator.domain.hourvalue;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import com.taxes.calculator.domain.AggregateRoot;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.utils.InstantUtils;
import com.taxes.calculator.domain.validation.ValidationHandler;
import com.taxes.calculator.domain.validation.handler.Notification;

public class HourValue extends AggregateRoot<HourValueID> {

    private BigDecimal expectedSalary;
    private BigDecimal hourValue;
    private Integer daysOfWork;
    private final Instant createdAt;
    private Instant updatedAt;

    private HourValue(final HourValueID id, final BigDecimal expectedSalary,
	    final BigDecimal hourValue, final Integer daysOfWork,
	    final Instant createdAt, final Instant updatedAt) {
	super(id);
	this.expectedSalary = expectedSalary;
	this.hourValue = hourValue;
	this.daysOfWork = daysOfWork;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;

	selfValidate();
    }

    @Override
    public void validate(ValidationHandler aHandler) {
	new HourValueValidator(this, aHandler).validate();
    }

    public static HourValue newHourValue(final BigDecimal aExpectedSalary,
	    final BigDecimal aHourValue, final Integer aDaysOfWork) {
	final var anId = HourValueID.unique();
	final var now = InstantUtils.now();
	return new HourValue(anId, aExpectedSalary, aHourValue, aDaysOfWork,
		now, now);
    }

    public static HourValue with(final HourValue aHourValue) {
	return new HourValue(aHourValue.id, aHourValue.expectedSalary,
		aHourValue.hourValue, aHourValue.daysOfWork,
		aHourValue.createdAt, aHourValue.getUpdatedAt());
    }

    public HourValue update(final BigDecimal aExpectedSalary,
	    final BigDecimal aHourValue, final Integer aDaysOfWork) {
	this.expectedSalary = aExpectedSalary;
	this.hourValue = aHourValue;
	this.daysOfWork = aDaysOfWork;
	this.updatedAt = InstantUtils.now();

	selfValidate();

	return this;
    }

    private void selfValidate() {
	final var notification = Notification.create();
	validate(notification);

	if (notification.hasError()) {
	    throw new NotificationException(
		    "Failed to validate Aggregate Value Hour", notification);
	}
    }

    public HourValueID getId() {
	return id;
    }

    public BigDecimal getExpectedSalary() {
	return expectedSalary;
    }

    public BigDecimal getHourValue() {
	return hourValue;
    }

    public Integer getDaysOfWork() {
	return daysOfWork;
    }

    public Instant getCreatedAt() {
	return createdAt;
    }

    public Instant getUpdatedAt() {
	return updatedAt;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(createdAt, daysOfWork,
		expectedSalary, hourValue, updatedAt);
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	HourValue other = (HourValue) obj;
	return Objects.equals(createdAt, other.createdAt)
		&& Objects.equals(daysOfWork, other.daysOfWork)
		&& Objects.equals(expectedSalary, other.expectedSalary)
		&& Objects.equals(hourValue, other.hourValue)
		&& Objects.equals(updatedAt, other.updatedAt);
    }

}