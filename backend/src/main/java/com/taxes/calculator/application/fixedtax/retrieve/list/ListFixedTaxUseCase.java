package com.taxes.calculator.application.fixedtax.retrieve.list;

import com.taxes.calculator.application.UseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;

public abstract class ListFixedTaxUseCase
	extends UseCase<SearchQuery, Pagination<ListFixedTaxOutput>> {

}
