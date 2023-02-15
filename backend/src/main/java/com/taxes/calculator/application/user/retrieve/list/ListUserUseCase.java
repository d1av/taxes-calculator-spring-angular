package com.taxes.calculator.application.user.retrieve.list;

import com.taxes.calculator.application.UseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;

public abstract class ListUserUseCase
	extends UseCase<SearchQuery, Pagination<UserListOutput>> {

}
