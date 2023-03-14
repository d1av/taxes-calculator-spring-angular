package com.taxes.calculator.infrastructure.variabletax;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxID;
import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxPersistence;
import com.taxes.calculator.infrastructure.utils.SpecificationUtils;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxJpaEntity;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxRepository;

@Service
public class VariableTaxMYSQLGateway
	implements VariableTaxGateway {

    private final VariableTaxRepository repository;

    public VariableTaxMYSQLGateway(
	    final VariableTaxRepository repository) {
	this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public VariableTax create(VariableTax aVariableTax) {
	return saveIfDoesntExists(aVariableTax);
    }

    @Override
    public Optional<VariableTax> findById(VariableTaxID anId) {
	return Optional.ofNullable(
		this.repository.findById(anId.getValue())
			.map(VariableTaxJpaEntity::toAggregate)
			.orElse(null));
    }

    @Override
    public Pagination<VariableTax> findAll(SearchQuery aQuery) {
	final var page = PageRequest.of(aQuery.page(),
		aQuery.perPage(), Sort.by(
			Sort.Direction
				.fromString(aQuery.direction()),
			aQuery.sort()));

	// Dynamic Search
	final Specification<?> specifications = Optional
		.ofNullable(aQuery.terms())
		.filter(str -> !str.isBlank())
		.map(SpecificationUtils::assembleSpecification)
		.orElse(null);

	final var pageResult = this.repository.findAll(
		Specification.where(
			(Specification<VariableTaxJpaEntity>) specifications),
		page);

	return new Pagination<>(pageResult.getNumber(),
		pageResult.getSize(),
		pageResult.getTotalElements(),
		pageResult.map(VariableTaxJpaEntity::toAggregate)
			.toList());
    }

    @Override
    public VariableTax update(VariableTax aVariableTax) {
	return saveIfDoesntExists(aVariableTax);
    }

    @Override
    public void deleteById(VariableTaxID anId) {
	if (this.repository.existsById(anId.getValue())) {
	    this.repository.deleteById(anId.getValue());
	    TotalTaxPersistence
		    .deleteValueWhenVariableTaxIsDeleted(
			    anId.getValue());
	}
    }

    @Transactional
    private VariableTax saveIfDoesntExists(
	    VariableTax aVariableTax) {
	String userId = aVariableTax.getUserId().getValue();
	if (this.repository.findByUserId(userId).isEmpty()) {
	    this.repository.save(
		    VariableTaxJpaEntity.from(aVariableTax));
	    TotalTaxPersistence.checkIfExistsToCreateOrUpdate(
		    null, aVariableTax.getId().getValue(), null,
		    userId);
	    return aVariableTax;
	} else {
	    return updateEntity(aVariableTax);
	}
    }

    @Transactional
    private VariableTax updateEntity(VariableTax aVariableTax) {
	List<VariableTaxJpaEntity> foundedList = this.repository
		.findByUserId(
			aVariableTax.getUserId().getValue());
	if (!foundedList.isEmpty()) {
	    VariableTaxJpaEntity existingEntity = foundedList
		    .get(0);
	    VariableTax parentEntity = VariableTax.with(
		    VariableTaxID.from(existingEntity.getId()),
		    existingEntity.getDentalShop(),
		    existingEntity.getProsthetist(),
		    existingEntity.getTravel(),
		    existingEntity.getCreditCard(),
		    existingEntity.getWeekend(),
		    existingEntity.getUserId(),
		    existingEntity.getCreatedAt(),
		    existingEntity.getUpdatedAt());

	    this.repository.save(
		    VariableTaxJpaEntity.from(parentEntity));

	    return parentEntity;
	}
	return null;
    }
}
