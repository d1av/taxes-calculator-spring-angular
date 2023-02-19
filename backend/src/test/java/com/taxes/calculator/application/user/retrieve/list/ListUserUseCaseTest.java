package com.taxes.calculator.application.user.retrieve.list;

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
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;

public class ListUserUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultListUserUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(userGateway);
    }

    @Test
    void givenAValidQuery_whenCallsGetUser_shouldReturnUserPaginated() {
	// given
	final var users = List.of(Fixture.Users.abella(),
		Fixture.Users.asa());

	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";
	final var expectedTotal = 2;

	final var expectedItems = users.stream()
		.map(UserListOutput::from).toList();

	final var expectedPagination = new Pagination<>(expectedPage,
		expectedPerPage, expectedTotal, users);

	when(userGateway.findAll(any()))
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

	verify(userGateway, times(1)).findAll(eq(aQuery));
    }

    @Test
    void givenAValidId_whenCallsGetUserIsEmpty_shouldReturnUserPaginated() {
	// given
	final var users = List.<User>of();

	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";
	final var expectedTotal = 0;

	final var expectedItems = users.stream()
		.map(UserListOutput::from).toList();

	final var expectedPagination = new Pagination<>(expectedPage,
		expectedPerPage, expectedTotal, users);

	when(userGateway.findAll(any()))
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

	verify(userGateway, times(1)).findAll(eq(aQuery));
    }

    @Test
    void givenAValidQuery_whenCallsGetUserAndGatewayThrowsRandomError_shouldReturnException() {
	// given
	final var Users = List.<User>of();

	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";

	final var expectedErrorMessage = "Gateway error";

	when(userGateway.findAll(any())).thenThrow(
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

	verify(userGateway, times(1)).findAll(eq(aQuery));
    }

}