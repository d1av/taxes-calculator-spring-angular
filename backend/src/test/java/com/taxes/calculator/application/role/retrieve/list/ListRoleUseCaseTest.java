package com.taxes.calculator.application.role.retrieve.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleGateway;

class ListRoleUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultListRoleUseCase useCase;

    @Mock
    private RoleGateway roleGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(roleGateway);
    }

    @Test
    void givenAValidQuery_whenCallsGetRole_shouldReturnRolePaginated() {
	// given
	final var Roles = List.of(Fixture.Roles.guest(),
		Fixture.Roles.member());

	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";
	final var expectedTotal = 2;

	final var expectedItems = Roles.stream()
		.map(RoleListOutput::from).toList();

	final var expectedPagination = new Pagination<>(expectedPage,
		expectedPerPage, expectedTotal, Roles);

	when(roleGateway.findAll(any()))
		.thenReturn(expectedPagination);

	final var aQuery = new SearchQuery(expectedPage,
		expectedPerPage, expectedTerms, expectedSort,
		expectedDirection);
	// when
	final var actualOutput = useCase.execute(aQuery);

	// then
	assertEquals(expectedPage, actualOutput.currentPage());
	assertEquals(expectedPerPage, actualOutput.perPage());
	assertEquals(expectedTotal, actualOutput.total());
	assertEquals(expectedItems, actualOutput.items());

	verify(roleGateway, times(1)).findAll(eq(aQuery));
    }

    @Test
    void givenAValidId_whenCallsGetRoleIsEmpty_shouldReturnRolePaginated() {
	// given
	final var Roles = List.<Role>of();

	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";
	final var expectedTotal = 0;

	final var expectedItems = Roles.stream()
		.map(RoleListOutput::from).toList();

	final var expectedPagination = new Pagination<>(expectedPage,
		expectedPerPage, expectedTotal, Roles);

	when(roleGateway.findAll(any()))
		.thenReturn(expectedPagination);

	final var aQuery = new SearchQuery(expectedPage,
		expectedPerPage, expectedTerms, expectedSort,
		expectedDirection);
	// when
	final var actualOutput = useCase.execute(aQuery);

	// then
	assertEquals(expectedPage, actualOutput.currentPage());
	assertEquals(expectedPerPage, actualOutput.perPage());
	assertEquals(expectedTotal, actualOutput.total());
	assertEquals(expectedItems, actualOutput.items());
	assertEquals(expectedItems.size(),
		actualOutput.items().size());

	verify(roleGateway, times(1)).findAll(eq(aQuery));
    }

    @Test
    void givenAValidQuery_whenCallsGetRoleAndGatewayThrowsRandomError_shouldReturnException() {
	// given
	final var Roles = List.<Role>of();

	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";

	final var expectedErrorMessage = "Gateway error";

	when(roleGateway.findAll(any())).thenThrow(
		new IllegalStateException("Gateway error"));

	final var aQuery = new SearchQuery(expectedPage,
		expectedPerPage, expectedTerms, expectedSort,
		expectedDirection);
	// when
	final var actualException = Assertions.assertThrows(
		IllegalStateException.class,
		() -> useCase.execute(aQuery));

	// then
	assertEquals(expectedErrorMessage,
		actualException.getMessage());

	verify(roleGateway, times(1)).findAll(eq(aQuery));
    }

}
