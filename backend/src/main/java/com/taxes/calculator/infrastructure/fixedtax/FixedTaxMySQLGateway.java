package com.taxes.calculator.infrastructure.fixedtax;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.fixedtax.FixedTaxID;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.infrastructure.fixedtax.persistence.FixedTaxJpaEntity;
import com.taxes.calculator.infrastructure.fixedtax.persistence.FixedTaxRepository;
import com.taxes.calculator.infrastructure.utils.SpecificationUtils;

@Service
public class FixedTaxMySQLGateway implements FixedTaxGateway {

    private final FixedTaxRepository fixedTaxRepository;

    public FixedTaxMySQLGateway(
	    final FixedTaxRepository fixedTaxRepository) {
	this.fixedTaxRepository = Objects
		.requireNonNull(fixedTaxRepository);
    }

    @Override
    public FixedTax create(FixedTax aFixedTax) {
	return save(aFixedTax);
    }

    @Override
    public Optional<FixedTax> findById(FixedTaxID anId) {
	return this.fixedTaxRepository.findById(anId.getValue())
		.map(FixedTaxJpaEntity::toAggregate);
    }

    @Override
    public Pagination<FixedTax> findAll(SearchQuery aQuery) {
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

	final var pageResult = this.fixedTaxRepository.findAll(
		Specification
			.where((Specification<FixedTaxJpaEntity>) specifications),
		page);

	return new Pagination<>(pageResult.getNumber(),
		pageResult.getSize(), pageResult.getTotalElements(),
		pageResult.map(FixedTaxJpaEntity::toAggregate)
			.toList());
    }

    @Override
    public FixedTax update(FixedTax aFixedTax) {
	return save(aFixedTax);
    }

    @Override
    public void deleteById(FixedTaxID anId) {
	if (!this.fixedTaxRepository.findById(anId.getValue())
		.isEmpty()) {
	    this.fixedTaxRepository.deleteById(anId.getValue());
	}
    }

    @Transactional
    private FixedTax save(FixedTax aFixedTax) {
	final var anEntity = this.fixedTaxRepository
		.save(FixedTaxJpaEntity.from(aFixedTax));
	return aFixedTax;
    }

}
