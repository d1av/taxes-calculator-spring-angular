package com.taxes.calculator.infrastructure.hourvalue;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.hourvalue.HourValueID;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.infrastructure.hourvalue.persistence.HourValueJpaEntity;
import com.taxes.calculator.infrastructure.hourvalue.persistence.HourValueRepository;
import com.taxes.calculator.infrastructure.utils.SpecificationUtils;

@Service
public class HourValueMySQLGateway implements HourValueGateway {

    private final HourValueRepository repository;

    public HourValueMySQLGateway(
	    final HourValueRepository repository) {
	this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public HourValue create(HourValue aHourValue) {
	return save(aHourValue);
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
	return save(aHourValue);
    }

    @Override
    public void deleteById(HourValueID anId) {
	if (this.repository.existsById(anId.getValue())) {
	    this.repository.deleteById(anId.getValue());
	}
    }

    @Transactional
    private HourValue save(HourValue aHourValue) {
	return this.repository
		.save(HourValueJpaEntity.from(aHourValue))
		.toAggregate();
    }
}
