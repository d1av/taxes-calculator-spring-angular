package com.taxes.calculator.application.fixedtax.retrieve.list;

import java.util.Objects;

import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;

public class DefaultListFixedTaxUseCase extends ListFixedTaxUseCase {

    private final FixedTaxGateway fixedTaxGateway;

    public DefaultListFixedTaxUseCase(
	    final FixedTaxGateway fixedTaxGateway) {
	this.fixedTaxGateway = Objects
		.requireNonNull(fixedTaxGateway);
    }

    @Override
    public Pagination<ListFixedTaxOutput> execute(SearchQuery anIn) {
	return this.fixedTaxGateway.findAll(anIn)
		.map(ListFixedTaxOutput::from);
    }

}
