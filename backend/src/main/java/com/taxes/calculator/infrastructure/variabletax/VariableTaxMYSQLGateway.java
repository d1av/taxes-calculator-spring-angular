package com.taxes.calculator.infrastructure.variabletax;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.taxes.calculator.domain.exceptions.DomainException;
import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.validation.handler.Notification;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxID;
import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxPersistence;
import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxRepository;
import com.taxes.calculator.infrastructure.utils.SpecificationUtils;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxJpaEntity;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxRepository;

@Service
public class VariableTaxMYSQLGateway implements VariableTaxGateway {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(VariableTaxMYSQLGateway.class);

    private final VariableTaxRepository repository;
    private final TotalTaxPersistence totalTaxPersistence;

    public VariableTaxMYSQLGateway(VariableTaxRepository repository,
	    TotalTaxPersistence totalTaxPersistence) {
	this.repository = Objects.requireNonNull(repository);
	this.totalTaxPersistence = Objects
		.requireNonNull(totalTaxPersistence);
    }

    @Override
    public VariableTax create(VariableTax aVariableTax) {
	return saveIfDoesntExists(aVariableTax);
    }

    @Override
    public Optional<VariableTax> findById(VariableTaxID anId) {
	String idValue = anId != null ? anId.getValue() : " ";
	return Optional.of(this.repository.findById(idValue)
		.map(VariableTaxJpaEntity::toAggregate)
		.orElseThrow(() -> new DomainException(
			"Failed to update Variable Tax",
			List.of(new com.taxes.calculator.domain.validation.Error(
				"VariableTax with ID %s was not found"
					.formatted(idValue))))));
    }

    @Override
    public Pagination<VariableTax> findAll(SearchQuery aQuery) {
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
		.where((Specification<VariableTaxJpaEntity>) specifications),
		page);

	return new Pagination<>(pageResult.getNumber(),
		pageResult.getSize(), pageResult.getTotalElements(),
		pageResult.map(VariableTaxJpaEntity::toAggregate)
			.toList());
    }

    @Override
    public VariableTax update(VariableTax aVariableTax) {
	String id = aVariableTax.getId() != null
		? aVariableTax.getId().getValue()
		: null;
	Optional<VariableTaxJpaEntity> variableTax = repository
		.findById(id);
	if (variableTax.isPresent()) {
	    VariableTax entity = variableTax.get().toAggregate();
	    entity.update(aVariableTax.getDentalShop(),
		    aVariableTax.getProsthetist(),
		    aVariableTax.getTravel(),
		    aVariableTax.getCreditCard(),
		    aVariableTax.getWeekend());
	    repository.save(VariableTaxJpaEntity.from(entity));
	    return entity;
	} else {
	    throw DomainException.with(
		    new com.taxes.calculator.domain.validation.Error(
			    "Variable tax with id %s not found."
				    .formatted(aVariableTax.getId()
					    .getValue())));
	}

    }

    @Override
    public void deleteById(VariableTaxID anId) {
	if (this.repository.existsById(anId.getValue())) {
	    this.repository.deleteById(anId.getValue());
	    totalTaxPersistence.deleteValueWhenVariableTaxIsDeleted(
		    anId.getValue());
	}
    }

    @Transactional
    private VariableTax saveIfDoesntExists(VariableTax aVariableTax) {
	String userId = aVariableTax.getUserId() != null
		? aVariableTax.getUserId().getValue()
		: "";
	if (this.repository.findByUserId(userId).isEmpty()) {
	    VariableTax savedEntity = this.repository
		    .save(VariableTaxJpaEntity.from(aVariableTax))
		    .toAggregate();
	    totalTaxPersistence.checkIfExistsToCreateOrUpdate(null,
		    savedEntity.getId().getValue(), null, userId);
	    return aVariableTax;
	} else {
	    List<VariableTaxJpaEntity> entity = repository
		    .findByUserId(userId);
	    Notification notification = Notification.create();
	    notification.append(
		    new com.taxes.calculator.domain.validation.Error(
			    "User already have an Variable Tax, id: "
				    + entity.get(0).getId()));
	    throw new NotificationException(
		    "Error creating the variable tax", notification);
	}
    }

    @Transactional
    private VariableTax updateEntity(VariableTax aVariableTax) {
	String userId = aVariableTax != null
		? aVariableTax.getUserId().getValue()
		: null;
	List<VariableTaxJpaEntity> foundedList = this.repository
		.findByUserId(userId);

	if (!foundedList.isEmpty()) {
	    VariableTaxJpaEntity existingEntity = foundedList.get(0);

	    VariableTax updatedTax = existingEntity.toAggregate()
		    .update(aVariableTax.getDentalShop(),
			    aVariableTax.getProsthetist(),
			    aVariableTax.getTravel(),
			    aVariableTax.getCreditCard(),
			    aVariableTax.getWeekend());

	    this.repository
		    .save(VariableTaxJpaEntity.from(updatedTax));

	    LOGGER.info("Variable tax updated with id: {}",
		    updatedTax.getId().getValue());

	    return updatedTax;
	}
	return null;
    }
}
