package com.taxes.calculator.application.fixedtax.retrieve.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.user.UserGateway;

class ListFixedTaxUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultListFixedTaxUseCase useCase;

    @Mock
    private FixedTaxGateway fixedTaxGateway;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(fixedTaxGateway, userGateway);
    }

    @Test
    void givenAValidId_whenCallsGetFixedTax_shouldReturnFixedTaxPaginated() {
	// given
	final var fixedTaxes = List.of(Fixture.Tax.fixed(),
		Fixture.Tax.fixedNullUser());

	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";
	final var expectedTotal = 2;

	final var expectedItems = fixedTaxes.stream()
		.map(ListFixedTaxOutput::from).toList();

	final var expectedPagination = new Pagination<>(expectedPage,
		expectedPerPage, expectedTotal, fixedTaxes);

	when(fixedTaxGateway.findAll(any()))
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

	verify(fixedTaxGateway, times(1)).findAll(eq(aQuery));
    }

    @Test
    void givenAValidId_whenCallsGetFixedTaxIsEmpty_shouldReturnFixedTaxPaginated() {
	// given
	final var fixedTaxes = List.<FixedTax>of();

	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";
	final var expectedTotal = 0;

	final var expectedItems = fixedTaxes.stream()
		.map(ListFixedTaxOutput::from).toList();

	final var expectedPagination = new Pagination<>(expectedPage,
		expectedPerPage, expectedTotal, fixedTaxes);

	when(fixedTaxGateway.findAll(any()))
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

	verify(fixedTaxGateway, times(1)).findAll(eq(aQuery));
    }

    @Test
    void givenAValidQuery_whenCallsGetFixedTaxAndGatewayThrowsRandomError_shouldReturnException() {
	// given
	final var fixedTaxes = List.<FixedTax>of();

	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";

	final var expectedErrorMessage = "Gateway error";

	when(fixedTaxGateway.findAll(any())).thenThrow(
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

	verify(fixedTaxGateway, times(1)).findAll(eq(aQuery));
    }

}
