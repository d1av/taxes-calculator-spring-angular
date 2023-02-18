package com.taxes.calculator.application.hourvalue.retrieve.list;

import com.taxes.calculator.application.UseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;

public abstract class ListHourValueUseCase extends
	UseCase<SearchQuery, Pagination<ListHourValueOutput>> {

}
