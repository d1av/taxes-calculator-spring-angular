package com.taxes.calculator.application.hourvalue.retrieve.list;

import java.util.Objects;

import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;

public class DefaultListHourValueUseCase
	extends ListHourValueUseCase {

    private final HourValueGateway hourValueGateway;

    public DefaultListHourValueUseCase(
	    final HourValueGateway hourValueGateway) {
	this.hourValueGateway = Objects
		.requireNonNull(hourValueGateway);
    }

    @Override
    public Pagination<ListHourValueOutput> execute(
	    final SearchQuery aQuery) {
	return this.hourValueGateway.findAll(aQuery)
		.map(ListHourValueOutput::from);
    }

}
