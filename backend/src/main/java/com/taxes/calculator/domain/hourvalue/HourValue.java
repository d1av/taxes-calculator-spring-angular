package com.taxes.calculator.domain.hourvalue;

import java.math.BigDecimal;
import java.time.Instant;

import com.taxes.calculator.domain.AggregateRoot;
import com.taxes.calculator.domain.validation.ValidationHandler;

public class HourValue extends AggregateRoot<HourValueID> {

    private BigDecimal expectedSalary;
    private BigDecimal hourValue;
    private BigDecimal daysOfWork;
    private final Instant createdAt;
    private Instant updatedAt;

    private HourValue(final HourValueID id, final BigDecimal expectedSalary,
	    final BigDecimal hourValue, final BigDecimal daysOfWork,
	    final Instant createdAt, final Instant updatedAt) {
	super(id);
	this.expectedSalary = expectedSalary;
	this.hourValue = hourValue;
	this.daysOfWork = daysOfWork;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
    }

    @Override
    public void validate(ValidationHandler handler) {
	// TODO Auto-generated method stub

    }

    public BigDecimal getExpectedSalary() {
        return expectedSalary;
    }

    public BigDecimal getHourValue() {
        return hourValue;
    }

    public BigDecimal getDaysOfWork() {
        return daysOfWork;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
    
    

}
