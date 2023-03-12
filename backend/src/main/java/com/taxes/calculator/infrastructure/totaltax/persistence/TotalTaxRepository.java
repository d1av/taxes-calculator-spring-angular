package com.taxes.calculator.infrastructure.totaltax.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalTaxRepository
	extends JpaRepository<TotalTaxJpaEntity, String> {

    List<TotalTaxJpaEntity> findByFixedTaxId(String fixedTaxId);

    List<TotalTaxJpaEntity> findByHourValueId(
	    String hourValueId);

    List<TotalTaxJpaEntity> findByVariableTaxId(
	    String variableTaxId);

    List<TotalTaxJpaEntity> findByUserId(String userId);
}
