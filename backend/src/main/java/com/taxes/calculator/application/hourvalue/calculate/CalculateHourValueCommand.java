package com.taxes.calculator.application.hourvalue.calculate;

public record CalculateHourValueCommand(String fixedTaxId,
	String variableTaxId, String hourValueId, String userId) {
    public static CalculateHourValueCommand with(String fixedTaxId,
	    String variableTaxId, String hourValueId, String userId) {
	return new CalculateHourValueCommand(fixedTaxId, variableTaxId,
		hourValueId, userId);
    }
}
