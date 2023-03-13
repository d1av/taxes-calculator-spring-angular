package com.taxes.calculator.infrastructure.hourvalue.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourValueRepository
	extends JpaRepository<HourValueJpaEntity, String> {

    Page<HourValueJpaEntity> findAll(
	    Specification<HourValueJpaEntity> whereClause,
	    Pageable pageable);

    List<HourValueJpaEntity> findByUserId(String userId);

}
