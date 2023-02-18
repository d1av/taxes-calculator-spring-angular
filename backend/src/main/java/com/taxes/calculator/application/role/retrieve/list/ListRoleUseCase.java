package com.taxes.calculator.application.role.retrieve.list;

import com.taxes.calculator.application.UseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;

public abstract class ListRoleUseCase
	extends UseCase<SearchQuery, Pagination<RoleListOutput>> {

}
