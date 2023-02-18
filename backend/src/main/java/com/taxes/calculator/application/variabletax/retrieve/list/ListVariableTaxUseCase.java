package com.taxes.calculator.application.variabletax.retrieve.list;

import com.taxes.calculator.application.UseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;

public abstract class ListVariableTaxUseCase extends
	UseCase<SearchQuery, Pagination<ListVariableTaxOutput>> {

}
