package com.taxes.calculator.infrastructure.totaltax.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalTaxRepository
	extends JpaRepository<TotalTaxJpaEntity, String> {

    Optional<TotalTaxJpaEntity> findByFixedTaxId(
	    String fixedTaxId);

    Optional<TotalTaxJpaEntity> findByHourValueId(
	    String hourValueId);

    Optional<TotalTaxJpaEntity> findByVariableTaxId(
	    String variableTaxId);

    Optional<TotalTaxJpaEntity> findByUserId(String userId);
}
