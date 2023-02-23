package com.taxes.calculator.infrastructure.variabletax.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariableTaxRepository
	extends JpaRepository<VariableTaxJpaEntity, String> {
    
    Page<VariableTaxJpaEntity> findAll(
	    Specification<VariableTaxJpaEntity> whereClause,
	    Pageable pageable);

}
