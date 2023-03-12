package com.taxes.calculator.infrastructure.totaltax.persistence;

import java.util.Objects;
import java.util.Optional;

public class TotalTaxPersistence {

    private static TotalTaxRepository totalTaxRepository;

    public TotalTaxPersistence(
	    TotalTaxRepository totalTaxRepository) {
	this.totalTaxRepository = Objects
		.requireNonNull(totalTaxRepository);
    }

    public static void checkIfExistsToCreateOrUpdate(
	    final String fixedTaxId, final String variableTaxId,
	    final String hourValueId, final String userId) {

	TotalTaxJpaEntity entity = TotalTaxJpaEntity.with(null,
		"", "", "", userId);

	Optional<TotalTaxJpaEntity> byFixedTaxId = totalTaxRepository
		.findByFixedTaxId(fixedTaxId);
	Optional<TotalTaxJpaEntity> byHourValueId = totalTaxRepository
		.findByHourValueId(hourValueId);
	Optional<TotalTaxJpaEntity> byVariableTaxId = totalTaxRepository
		.findByVariableTaxId(variableTaxId);
	Optional<TotalTaxJpaEntity> byUserId = totalTaxRepository
		.findByUserId(userId);

	if (byFixedTaxId.isPresent()
		&& Objects.equals(byFixedTaxId.get().getUserId(),
			entity.getUserId())) {
	    entity = byFixedTaxId.get();
	    entity.setVariableTaxId(variableTaxId);
	    entity.setHourValueId(hourValueId);
	}
	if (byHourValueId.isPresent() && Objects.equals(
		byHourValueId.get().getUserId(),
		entity.getUserId())) {
	    entity = byHourValueId.get();
	    entity.setVariableTaxId(variableTaxId);
	    entity.setFixedTaxId(fixedTaxId);
	}
	if (byVariableTaxId.isPresent() && Objects.equals(
		byVariableTaxId.get().getUserId(),
		entity.getUserId())) {
	    entity = byVariableTaxId.get();
	    entity.setHourValueId(hourValueId);
	    entity.setFixedTaxId(fixedTaxId);
	}
	if (byUserId.isPresent()
		&& Objects.equals(byUserId.get().getUserId(),
			entity.getUserId())) {
	    entity = byUserId.get();
	    entity.setVariableTaxId(variableTaxId);
	    entity.setFixedTaxId(fixedTaxId);
	    entity.setHourValueId(hourValueId);
	} else {
	    entity.setFixedTaxId(fixedTaxId);
	    entity.setHourValueId(hourValueId);
	    entity.setVariableTaxId(variableTaxId);
	    totalTaxRepository.save(entity);
	}

    }

}
