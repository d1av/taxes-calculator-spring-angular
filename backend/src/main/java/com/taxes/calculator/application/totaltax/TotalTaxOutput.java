package com.taxes.calculator.application.totaltax;

import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxJpaEntity;

public record TotalTaxOutput(String taxId, String fixedTaxId,
	String variableTaxId, String hourValueId,
	String userId) {
    public static TotalTaxOutput with(TotalTaxJpaEntity entity) {
	return new TotalTaxOutput(entity.getTaxId(),
		entity.getFixedTaxId(),
		entity.getVariableTaxId(),
		entity.getHourValueId(), entity.getUserId());

    }
}
