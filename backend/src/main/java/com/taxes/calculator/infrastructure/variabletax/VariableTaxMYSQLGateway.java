package com.taxes.calculator.infrastructure.variabletax;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxID;
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

    private VariableTax save(VariableTax aVariableTax) {
	final var anJpaEntity = VariableTaxJpaEntity
		.from(aVariableTax);
	return this.repository.save(anJpaEntity).toAggregate();
    }

    @Override
    public Optional<VariableTax> findById(VariableTaxID anId) {
	// TODO Auto-generated method stub
	return Optional.empty();
    }

    @Override
    public Pagination<VariableTax> findAll(SearchQuery aQuery) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public VariableTax update(VariableTax aVariableTax) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void deleteById(VariableTaxID anId) {
	// TODO Auto-generated method stub

    }
}
