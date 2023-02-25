package com.taxes.calculator.application.hourvalue.calculate;

import com.taxes.calculator.domain.user.UserID;

public record CalculateHourValueCommand(String fixedTaxId,
	String variableTaxId, String hourValueId, UserID user) {

}
