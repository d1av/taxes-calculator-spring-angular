package com.taxes.calculator.infrastructure.totaltax.models;

import com.taxes.calculator.application.totaltax.TotalTaxOutput;
import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxJpaEntity;

public record TotalTaxResponse(String taxId, String fixedTaxId,
	String variableTaxId, String hourValueId,
	String userId) {
    public static TotalTaxResponse with(TotalTaxJpaEntity aTax) {
	return new TotalTaxResponse(aTax.getTaxId(),
		aTax.getFixedTaxId(), aTax.getVariableTaxId(),
		aTax.getHourValueId(), aTax.getUserId());
    }

    public static TotalTaxResponse with(TotalTaxOutput aTax) {
	return new TotalTaxResponse(aTax.taxId(),
		aTax.fixedTaxId(), aTax.variableTaxId(),
		aTax.hourValueId(), aTax.userId());
    }
}
