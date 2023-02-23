package com.taxes.calculator.infrastructure.variabletax;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxID;
import com.taxes.calculator.infrastructure.utils.SpecificationUtils;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxJpaEntity;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxRepository;

@Service
public class VariableTaxMYSQLGateway implements VariableTaxGateway {

    private final VariableTaxRepository repository;

    public VariableTaxMYSQLGateway(
	    final VariableTaxRepository repository) {
	this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public VariableTax create(VariableTax aVariableTax) {
	return save(aVariableTax);
    }

    @Override
    public Optional<VariableTax> findById(VariableTaxID anId) {
	return Optional.ofNullable(this.repository
		.findById(anId.getValue())
		.map(VariableTaxJpaEntity::toAggregate).orElse(null));
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
	return save(aVariableTax);
    }

    @Override
    public void deleteById(VariableTaxID anId) {
	if (this.repository.existsById(anId.getValue())) {
	    this.repository.deleteById(anId.getValue());
	}
    }

    private VariableTax save(VariableTax aVariableTax) {
	if (aVariableTax == null)
	    return null;

	final var anJpaEntity = VariableTaxJpaEntity
		.from(aVariableTax);
	return this.repository.save(anJpaEntity).toAggregate();
    }
}
