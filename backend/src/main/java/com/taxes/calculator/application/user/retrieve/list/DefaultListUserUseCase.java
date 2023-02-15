package com.taxes.calculator.application.user.retrieve.list;

import java.util.Objects;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.user.UserGateway;

public class DefaultListUserUseCase extends ListUserUseCase {

    private final UserGateway userGateway;

    public DefaultListUserUseCase(final UserGateway userGateway) {
	this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public Pagination<UserListOutput> execute(
	    final SearchQuery aQuery) {
	return this.userGateway.findAll(aQuery)
		.map(UserListOutput::from);
    }

}
