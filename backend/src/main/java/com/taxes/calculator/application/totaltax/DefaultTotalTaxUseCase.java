package com.taxes.calculator.application.totaltax;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.taxes.calculator.domain.exceptions.DomainException;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxJpaEntity;
import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxRepository;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;

public class DefaultTotalTaxUseCase extends TotalTaxUseCase {

    private final TotalTaxRepository totalTaxRepository;
    private final UserRepository userRepository;

    public DefaultTotalTaxUseCase(
	    final TotalTaxRepository totalTaxRepository,
	    final UserRepository userRepository) {
	this.totalTaxRepository = Objects
		.requireNonNull(totalTaxRepository);
	this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public TotalTaxOutput execute(String anIn) {
	Optional<TotalTaxJpaEntity> entity = totalTaxRepository
		.findByUserId(anIn);
	if(!userRepository.existsById(anIn)) {
	    throw new DomainException("Error Creating new Total Tax",
		    List.of(new Error(
			    "User not found with id: %s "
				    .formatted(anIn)))); 
	}

	if (entity.isPresent()) {
	    return TotalTaxOutput.with(entity.get());
	}
	return TotalTaxOutput.with(totalTaxRepository.save(
		TotalTaxJpaEntity.newEntity(null, null, null, anIn)));
    }

}
