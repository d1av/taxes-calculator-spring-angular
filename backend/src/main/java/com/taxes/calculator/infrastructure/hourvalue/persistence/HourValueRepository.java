package com.taxes.calculator.infrastructure.hourvalue.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HourValueRepository
	extends JpaRepository<HourValueJpaEntity, String> {

}
