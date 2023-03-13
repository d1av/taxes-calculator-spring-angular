package com.taxes.calculator.infrastructure.totaltax.models;

import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxJpaEntity;

public record TotalTaxResponse(String taxId, String fixedTaxId,
	String variableTaxId, String hourValueId,
	String userId) {
    public static TotalTaxResponse with(TotalTaxJpaEntity aTax) {
	return new TotalTaxResponse(aTax.getTaxId(),
		aTax.getFixedTaxId(), aTax.getVariableTaxId(),
		aTax.getHourValueId(), aTax.getUserId());
    }
}
