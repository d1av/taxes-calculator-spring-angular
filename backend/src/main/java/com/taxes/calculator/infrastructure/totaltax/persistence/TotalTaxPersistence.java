package com.taxes.calculator.infrastructure.totaltax.persistence;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

@Service
public class TotalTaxPersistence {

    private TotalTaxRepository totalTaxRepository;

    private static final Logger log = (Logger) LoggerFactory
	    .getLogger(TotalTaxPersistence.class);

    public TotalTaxPersistence(
	    TotalTaxRepository totalTaxRepository) {
	this.totalTaxRepository = Objects
		.requireNonNull(totalTaxRepository);
    }

    public void deleteValueWhenFixedTaxIsDeleted(
	    final String fixedTaxId) {
	List<TotalTaxJpaEntity> fixedTax = totalTaxRepository
		.findByFixedTaxId(fixedTaxId);
	if (!fixedTax.isEmpty()) {
	    TotalTaxJpaEntity entity = fixedTax.get(0);
	    totalTaxRepository.saveAndFlush(
		    TotalTaxJpaEntity.with(entity.getTaxId(),
			    null, entity.getVariableTaxId(),
			    entity.getHourValueId(),
			    entity.getUserId()));
	}
    }

    public void deleteValueWhenVariableTaxIsDeleted(
	    final String variableTaxId) {
	List<TotalTaxJpaEntity> variableTax = totalTaxRepository
		.findByVariableTaxId(variableTaxId);
	if (!variableTax.isEmpty()) {
	    TotalTaxJpaEntity entity = variableTax.get(0);
	    totalTaxRepository.saveAndFlush(
		    TotalTaxJpaEntity.with(entity.getTaxId(),
			    entity.getFixedTaxId(), null,
			    entity.getHourValueId(),
			    entity.getUserId()));
	}
    }

    public void deleteValueWhenHourValueIsDeleted(
	    final String hourValueId) {
	List<TotalTaxJpaEntity> hourValue = totalTaxRepository
		.findByHourValueId(hourValueId);
	if (!hourValue.isEmpty()) {
	    TotalTaxJpaEntity entity = hourValue.get(0);
	    totalTaxRepository.saveAndFlush(
		    TotalTaxJpaEntity.with(entity.getTaxId(),
			    entity.getFixedTaxId(),
			    entity.getVariableTaxId(), null,
			    entity.getUserId()));
	}
    }

    @Transactional
    public TotalTaxJpaEntity checkIfExistsToCreateOrUpdate(
	    final String fixedTaxId, final String variableTaxId,
	    final String hourValueId, final String userId) {

	TotalTaxJpaEntity entity = TotalTaxJpaEntity.newEntity(
		fixedTaxId, variableTaxId, hourValueId, userId);

	Optional<TotalTaxJpaEntity> byUserId = totalTaxRepository
		.findByUserId(userId);

	if (byUserId.isPresent()
		&& Objects.equals(byUserId.get().getUserId(),
			entity.getUserId())) {
	    entity = byUserId.get();

	    log.info("{} é a entidade no persistance",
		    entity.toString());

	    return saveEntityWith(entity, fixedTaxId,
		    variableTaxId, hourValueId);
	} else {
	    return totalTaxRepository.saveAndFlush(entity);
	}

    }

    @Transactional
    private TotalTaxJpaEntity saveEntityWith(
	    TotalTaxJpaEntity entity, String fixedTaxId,
	    String variableTaxId, String hourValueId) {
	if (entity.getVariableTaxId() == null
		&& variableTaxId != null
		&& !variableTaxId.isBlank()) {
	    entity.setVariableTaxId(variableTaxId);
	}
	if (entity.getFixedTaxId() == null && fixedTaxId != null
		&& !fixedTaxId.isBlank()) {
	    entity.setFixedTaxId(fixedTaxId);
	}
	if (entity.getHourValueId() == null
		&& hourValueId != null
		&& !hourValueId.isBlank()) {
	    entity.setHourValueId(hourValueId);
	}

	return totalTaxRepository.saveAndFlush(entity);
    }

}
