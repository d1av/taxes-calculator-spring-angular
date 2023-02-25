package com.taxes.calculator.application.hourvalue.calculate;

import java.math.BigDecimal;

import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.variabletax.VariableTax;

public record CalculateHourValueOutput(BigDecimal hourValue,
	Integer hoursWorked, BigDecimal expectedSalary,
	BigDecimal totalMonthlyCosts, String hourValueId,
	String variableTaxId, String fixedTaxId) {
    public static CalculateHourValueOutput with(BigDecimal hourValue,
	    Integer hoursWorked, BigDecimal expectedSalary,
	    BigDecimal totalMonthlyCosts, HourValue hourValueId,
	    VariableTax variableTaxId, FixedTax fixedTaxId) {
	return new CalculateHourValueOutput(hourValue, hoursWorked,
		expectedSalary, totalMonthlyCosts,
		hourValueId.getId().getValue(),
		variableTaxId.getId().getValue(),
		fixedTaxId.getId().getValue());
    }

}
