package com.taxes.calculator.application.role.retrieve.list;

import java.util.Objects;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.role.RoleGateway;

public class DefaultListRoleUseCase extends ListRoleUseCase {

    private final RoleGateway roleGateway;

    public DefaultListRoleUseCase(final RoleGateway roleGateway) {
	this.roleGateway = Objects.requireNonNull(roleGateway);
    }

    @Override
    public Pagination<RoleListOutput> execute(
	    final SearchQuery aQuery) {
	return this.roleGateway.findAll(aQuery)
		.map(RoleListOutput::from);
    }

}
