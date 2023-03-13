package com.taxes.calculator.infrastructure.totaltax.persistence;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class TotalTaxPersistence {

    private static TotalTaxRepository totalTaxRepository;

    public TotalTaxPersistence(
	    TotalTaxRepository totalTaxRepository) {
	TotalTaxPersistence.totalTaxRepository = Objects
		.requireNonNull(totalTaxRepository);
    }

    public static TotalTaxJpaEntity checkIfExistsToCreateOrUpdate(
	    final String fixedTaxId, final String variableTaxId,
	    final String hourValueId, final String userId) {

	TotalTaxJpaEntity entity = TotalTaxJpaEntity.with(null,
		fixedTaxId, variableTaxId, hourValueId, userId);

	Optional<TotalTaxJpaEntity> byUserId = totalTaxRepository
		.findByUserId(userId);

	if (byUserId.isPresent()
		&& Objects.equals(byUserId.get().getUserId(),
			entity.getUserId())) {
	    entity = byUserId.get();
	    return saveEntityWith(entity, fixedTaxId,
		    variableTaxId, hourValueId);
	} else {
	    entity.setFixedTaxId(fixedTaxId);
	    entity.setHourValueId(hourValueId);
	    entity.setVariableTaxId(variableTaxId);
	    return totalTaxRepository.save(entity);
	}

    }

    @Transactional
    private static TotalTaxJpaEntity saveEntityWith(
	    TotalTaxJpaEntity entity, String fixedTaxId,
	    String variableTaxId, String hourValueId) {
	if (entity.getVariableTaxId() == null
		&& variableTaxId != null
		&& !variableTaxId.isBlank()) {
	    entity.setVariableTaxId(variableTaxId);
	}
	if (entity.getFixedTaxId() == null 
		&& fixedTaxId != null
		&& !fixedTaxId.isBlank()) {
	    entity.setFixedTaxId(fixedTaxId);
	}
	if (entity.getHourValueId() == null
		&& hourValueId != null
		&& !hourValueId.isBlank()) {
	    entity.setHourValueId(hourValueId);
	}

	return totalTaxRepository.save(entity);
    }

}
