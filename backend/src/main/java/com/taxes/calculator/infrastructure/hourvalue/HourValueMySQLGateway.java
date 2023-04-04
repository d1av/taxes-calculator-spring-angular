package com.taxes.calculator.infrastructure.hourvalue;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.taxes.calculator.domain.exceptions.DomainException;
import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.hourvalue.HourValueID;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.infrastructure.fixedtax.persistence.FixedTaxJpaEntity;
import com.taxes.calculator.infrastructure.hourvalue.persistence.HourValueJpaEntity;
import com.taxes.calculator.infrastructure.hourvalue.persistence.HourValueRepository;
import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxPersistence;
import com.taxes.calculator.infrastructure.utils.SpecificationUtils;

@Service
public class HourValueMySQLGateway implements HourValueGateway {

    private final HourValueRepository repository;
    private final TotalTaxPersistence totalTaxPersistence;

    public HourValueMySQLGateway(
	    final TotalTaxPersistence totalTaxPersistence,
	    final HourValueRepository repository) {
	this.totalTaxPersistence = Objects
		.requireNonNull(totalTaxPersistence);
	this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public HourValue create(HourValue aHourValue) {
	return saveIfDoesntExists(aHourValue);
    }

    @Override
    public Optional<HourValue> findById(HourValueID anId) {
	return Optional.ofNullable(this.repository
		.findById(anId.getValue())
		.map(HourValueJpaEntity::toAggregate).orElse(null));
    }

    @Override
    public Pagination<HourValue> findAll(SearchQuery aQuery) {
	final var page = PageRequest.of(aQuery.page(),
		aQuery.perPage(),
		Sort.by(Sort.Direction.fromString(aQuery.direction()),
			aQuery.sort()));

	// Dynamic Search
	final Specification<?> specifications = Optional
		.ofNullable(aQuery.terms())
		.filter(str -> !str.isBlank())
		.map(SpecificationUtils::assembleSpecification)
		.orElse(null);

	final var pageResult = this.repository.findAll(Specification
		.where((Specification<HourValueJpaEntity>) specifications),
		page);

	return new Pagination<>(pageResult.getNumber(),
		pageResult.getSize(), pageResult.getTotalElements(),
		pageResult.map(HourValueJpaEntity::toAggregate)
			.toList());
    }

    @Override
    public HourValue update(HourValue aHourValue) {
	return updateEntity(aHourValue);
    }

    @Override
    public void deleteById(HourValueID anId) {
	if (this.repository.existsById(anId.getValue())) {
	    this.repository.deleteById(anId.getValue());
	    totalTaxPersistence.deleteValueWhenHourValueIsDeleted(
		    anId.getValue());
	}
    }

    @Transactional
    private HourValue saveIfDoesntExists(HourValue aHourValue) {
	if (aHourValue != null && this.repository
		.findByUserId(aHourValue.getUserId().getValue())
		.isEmpty()) {
	    String userId = aHourValue.getUserId().getValue();
	    this.repository.save(HourValueJpaEntity.from(aHourValue));
	    totalTaxPersistence.checkIfExistsToCreateOrUpdate(null,
		    null, aHourValue.getId().getValue(), userId);
	    return aHourValue;
	} else if (aHourValue != null
		&& aHourValue.getUserId().getValue() != null) {
	    String hourValueId = aHourValue.getUserId().getValue();
	    throw new DomainException("Error Creating Hour Value",
		    List.of(new Error(
			    "the user already has an hour value with id: %s "
				    .formatted(hourValueId))));
	} else {
	    throw new DomainException("Error Creating Hour Value",
		    List.of(new Error(
			    "Please fill the requisition fields.")));
	}
    }

    @Transactional
    private HourValue updateEntity(HourValue aHourValue) {
	List<HourValueJpaEntity> entity = this.repository
		.findByUserId(aHourValue.getUserId().getValue());
	if (!entity.isEmpty()) {
	    HourValueJpaEntity existingEntity = entity.get(0);

	    HourValue updatedEntity = existingEntity.toAggregate()
		    .update(aHourValue.getExpectedSalary(),
			    aHourValue.getPersonalHourValue(),
			    aHourValue.getDaysOfWork());

	    this.repository
		    .save(HourValueJpaEntity.from(updatedEntity));

	    return updatedEntity;
	} else {
	    throw new DomainException("Error Creating Fixed Tax",
		    List.of(new Error(
			    "the user already has an hour value with id: %s "
				    .formatted(aHourValue.getId()))));
	}
    }
}
