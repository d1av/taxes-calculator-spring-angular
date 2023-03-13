package com.taxes.calculator.infrastructure.fixedtax.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedTaxRepository
	extends JpaRepository<FixedTaxJpaEntity, String> {

    Page<FixedTaxJpaEntity> findAll(
	    Specification<FixedTaxJpaEntity> whereClause,
	    Pageable pageable);

    List<FixedTaxJpaEntity> findByUserId(String userId);

}
