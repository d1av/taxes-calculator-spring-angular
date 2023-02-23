package com.taxes.calculator.application.variabletax.retrieve.get;

import java.math.BigDecimal;
import java.time.Instant;

import com.taxes.calculator.domain.variabletax.VariableTax;

public record VariableTaxByIdOutput(String id, BigDecimal dentalShop,
	BigDecimal prosthetist, BigDecimal travel,
	BigDecimal creditCard, BigDecimal weekend, String userId,
	Instant updatedAt, Instant createdAt) {
    public static VariableTaxByIdOutput from(final VariableTax aTax) {
	final var aUserId = aTax.getUserId() != null
		? aTax.getUserId().getValue()
		: null;
	return new VariableTaxByIdOutput(aTax.getId().getValue(),
		aTax.getDentalShop(), aTax.getProsthetist(),
		aTax.getTravel(), aTax.getCreditCard(),
		aTax.getWeekend(), aUserId, aTax.getUpdatedAt(),
		aTax.getCreatedAt());
    }
}
