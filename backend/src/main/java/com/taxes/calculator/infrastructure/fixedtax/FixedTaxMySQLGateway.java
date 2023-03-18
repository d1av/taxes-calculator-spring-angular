package com.taxes.calculator.infrastructure.fixedtax;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.fixedtax.FixedTaxID;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.infrastructure.fixedtax.persistence.FixedTaxJpaEntity;
import com.taxes.calculator.infrastructure.fixedtax.persistence.FixedTaxRepository;
import com.taxes.calculator.infrastructure.fixedtax.persistence.UserFixedTaxID;
import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxJpaEntity;
import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxPersistence;
import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxRepository;
import com.taxes.calculator.infrastructure.utils.SpecificationUtils;

@Service
public class FixedTaxMySQLGateway implements FixedTaxGateway {

    private final FixedTaxRepository fixedTaxRepository;
    private final TotalTaxPersistence totalTaxPersistence;

    public FixedTaxMySQLGateway(
	    final TotalTaxPersistence totalTaxPersistence, 
	    final FixedTaxRepository fixedTaxRepository) {
	this.fixedTaxRepository = fixedTaxRepository;
	this.totalTaxPersistence = Objects
		.requireNonNull(totalTaxPersistence);
    }

    @Override
    public FixedTax create(FixedTax aFixedTax) {
	return saveIfDoesntExists(aFixedTax);
    }

    @Override
    public Optional<FixedTax> findById(FixedTaxID anId) {
	return this.fixedTaxRepository.findById(anId.getValue())
		.map(FixedTaxJpaEntity::toAggregate);
    }

    @Override
    public Pagination<FixedTax> findAll(SearchQuery aQuery) {
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

	final var pageResult = this.fixedTaxRepository.findAll(
		Specification.where(
			(Specification<FixedTaxJpaEntity>) specifications),
		page);

	return new Pagination<>(pageResult.getNumber(),
		pageResult.getSize(),
		pageResult.getTotalElements(),
		pageResult.map(FixedTaxJpaEntity::toAggregate)
			.toList());
    }

    @Override
    public FixedTax update(FixedTax aFixedTax) {
	return saveIfDoesntExists(aFixedTax);
    }

    @Override
    public void deleteById(FixedTaxID anId) {
	if (!this.fixedTaxRepository.findById(anId.getValue())
		.isEmpty()) {
	    this.fixedTaxRepository.deleteById(anId.getValue());
	    totalTaxPersistence.deleteValueWhenFixedTaxIsDeleted(
		    anId.getValue());
	}
    }

    @Transactional
    private FixedTax saveIfDoesntExists(FixedTax aFixedTax) {
	String userId = aFixedTax.getUser().getValue();
	if (this.fixedTaxRepository.findByUserId(userId)
		.isEmpty()) {
	    this.fixedTaxRepository
		    .save(FixedTaxJpaEntity.from(aFixedTax));
	    totalTaxPersistence.checkIfExistsToCreateOrUpdate(
		    aFixedTax.getId().getValue(), null, null,
		    userId);
	    return aFixedTax;
	} else {
	    return updateEntity(aFixedTax);
	}
    }

    @Transactional
    private FixedTax updateEntity(FixedTax aFixedTax) {
	FixedTax existingEntity = this.fixedTaxRepository
		.findAll().get(0).toAggregate();
	existingEntity.update(aFixedTax.getRegionalCouncil(),
		aFixedTax.getTaxOverWork(),
		aFixedTax.getIncomeTax(),
		aFixedTax.getAccountant(),
		aFixedTax.getDentalShop(),
		aFixedTax.getTransport(), aFixedTax.getFood(),
		aFixedTax.getEducation(),
		aFixedTax.getOtherFixedCosts());
	this.fixedTaxRepository
		.save(FixedTaxJpaEntity.from(existingEntity));
	return existingEntity;
    }

}
