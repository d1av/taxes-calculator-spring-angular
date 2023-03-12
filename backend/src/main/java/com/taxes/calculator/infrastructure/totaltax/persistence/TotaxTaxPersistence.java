package com.taxes.calculator.infrastructure.totaltax.persistence;

import java.util.Objects;

public class TotaxTaxPersistence {

    private final TotalTaxRepository totalTaxRepository;

    public TotaxTaxPersistence(
	    TotalTaxRepository totalTaxRepository) {
	this.totalTaxRepository = Objects
		.requireNonNull(totalTaxRepository);
    }

    public void checkIfExistsOrUpdate(final String taxId,
	    final String fixedTaxId, final String variableTaxId,
	    final String hourValueId, final String userId) {
	this.totalTaxRepository.findByFixedTaxId(fixedTaxId);
	this.totalTaxRepository.findByHourValueId(hourValueId);
	this.totalTaxRepository.findByUserId(userId);
	this.totalTaxRepository
		.findByVariableTaxId(variableTaxId);
    }

}
