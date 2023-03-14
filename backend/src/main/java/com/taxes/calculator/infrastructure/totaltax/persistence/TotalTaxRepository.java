package com.taxes.calculator.infrastructure.totaltax.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalTaxRepository
	extends JpaRepository<TotalTaxJpaEntity, String> {

    Optional<TotalTaxJpaEntity> findByUserId(String userId);

    List<TotalTaxJpaEntity> findByFixedTaxId(String fixedTaxId);

    List<TotalTaxJpaEntity> findByHourValueId(
	    String hourValueId);

    List<TotalTaxJpaEntity> findByVariableTaxId(
	    String variableTaxId);

}
