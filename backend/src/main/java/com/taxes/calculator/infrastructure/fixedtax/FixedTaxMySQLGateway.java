package com.taxes.calculator.infrastructure.fixedtax;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.fixedtax.FixedTaxID;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.infrastructure.fixedtax.persistence.FixedTaxJpaEntity;
import com.taxes.calculator.infrastructure.fixedtax.persistence.FixedTaxRepository;

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
	// TODO Auto-generated method stub
	return Optional.empty();
    }

    @Override
    public Pagination<FixedTax> findAll(SearchQuery aQuery) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public FixedTax update(FixedTax aFixedTax) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void deleteById(FixedTaxID anId) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<FixedTaxID> existsByIds(Iterable<FixedTaxID> ids) {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional
    private FixedTax save(FixedTax aFixedTax) {
	final var anEntity = this.fixedTaxRepository
		.save(FixedTaxJpaEntity.from(aFixedTax));
	return aFixedTax;
    }

}
