package com.taxes.calculator.application.variabletax.retrieve.list;

import java.util.Objects;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;

public class DefaultListVariableTaxUseCase
	extends ListVariableTaxUseCase {

    private final VariableTaxGateway variableTaxGateway;

    public DefaultListVariableTaxUseCase(
	    final VariableTaxGateway variableTaxGateway) {
	this.variableTaxGateway = Objects
		.requireNonNull(variableTaxGateway);
    }

    @Override
    public Pagination<ListVariableTaxOutput> execute(
	    SearchQuery aQuery) {

	return this.variableTaxGateway.findAll(aQuery)
		.map(ListVariableTaxOutput::from);
    }

}
