package com.taxes.calculator.application.variabletax.retrieve.list;

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
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;

class ListVariableTaxUseCaseTest extends UseCaseTest {
    @InjectMocks
    private DefaultListVariableTaxUseCase useCase;

    @Mock
    private VariableTaxGateway variableTaxGateway;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(variableTaxGateway, userGateway);
    }

    @Test
    void givenAValidQuery_whenCallsGetVariableTax_shouldReturnVariableTaxPaginated() {
	// given
	final var variableTaxes = List.of(Fixture.Tax.variable(),
		Fixture.Tax.variable());

	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";
	final var expectedTotal = 2;

	final var expectedItems = variableTaxes.stream()
		.map(ListVariableTaxOutput::from).toList();

	final var expectedPagination = new Pagination<>(expectedPage,
		expectedPerPage, expectedTotal, variableTaxes);

	when(variableTaxGateway.findAll(any()))
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

	verify(variableTaxGateway, times(1)).findAll(eq(aQuery));
    }

    @Test
    void givenAValidQuery_whenCallsGetVariableTaxIsEmpty_shouldReturnVariableTaxPaginated() {
	// given
	final var variableTaxes = List.<VariableTax>of();

	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";
	final var expectedTotal = 0;

	final var expectedItems = variableTaxes.stream()
		.map(ListVariableTaxOutput::from).toList();

	final var expectedPagination = new Pagination<>(expectedPage,
		expectedPerPage, expectedTotal, variableTaxes);

	when(variableTaxGateway.findAll(any()))
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

	verify(variableTaxGateway, times(1)).findAll(eq(aQuery));
    }

    @Test
    void givenAValidQuery_whenCallsGetVariableTaxAndGatewayThrowsRandomError_shouldReturnException() {
	// given
	final var variableTaxes = List.<VariableTax>of();

	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";

	final var expectedErrorMessage = "Gateway error";

	when(variableTaxGateway.findAll(any())).thenThrow(
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

	verify(variableTaxGateway, times(1)).findAll(eq(aQuery));
    }

}
