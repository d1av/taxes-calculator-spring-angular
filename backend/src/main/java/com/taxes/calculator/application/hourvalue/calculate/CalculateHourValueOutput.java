package com.taxes.calculator.application.hourvalue.calculate;

import java.math.BigDecimal;

public record CalculateHourValueOutput(
	BigDecimal hourValue,
	Integer daysWorked, 
	String variableTaxId, 
	String fixedTaxId) {

}
