package com.taxes.calculator.infrastructure.fixedtax.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedTaxRepository
	extends JpaRepository<FixedTaxJpaEntity, String> {

}
