package com.taxes.calculator.application.totaltax;

import java.util.Objects;
import java.util.Optional;

import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxJpaEntity;
import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxRepository;

public class DefaultTotalTaxUseCase extends TotalTaxUseCase {

    private final TotalTaxRepository totalTaxRepository;

    public DefaultTotalTaxUseCase(
	    TotalTaxRepository totalTaxRepository) {
	this.totalTaxRepository = Objects
		.requireNonNull(totalTaxRepository);
    }

    @Override
    public TotalTaxOutput execute(String anIn) {
	Optional<TotalTaxJpaEntity> entity = totalTaxRepository
		.findByUserId(anIn);
	if (entity.isPresent()) {
	    return TotalTaxOutput.with(entity.get());
	}
	return TotalTaxOutput
		.with(totalTaxRepository.save(TotalTaxJpaEntity
			.with(null, null, null, null, anIn)));
    }

}
