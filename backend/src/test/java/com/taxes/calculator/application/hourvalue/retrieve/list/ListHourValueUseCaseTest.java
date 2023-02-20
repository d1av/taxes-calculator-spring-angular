package com.taxes.calculator.application.hourvalue.retrieve.list;

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
import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;

class ListHourValueUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultListHourValueUseCase useCase;

    @Mock
    private HourValueGateway hourValueGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(hourValueGateway);
    }

    @Test
    void givenAValidQuery_whenCallsGetHourValue_shouldReturnPaginated() {
	// given
	final var hourValues = List.of(Fixture.HourValues.valid(),
		Fixture.HourValues.one());

	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";
	final var expectedTotal = 2;

	final var expectedItems = hourValues.stream()
		.map(ListHourValueOutput::from).toList();

	final var expectedPagination = new Pagination<>(expectedPage,
		expectedPerPage, expectedTotal, hourValues);

	when(hourValueGateway.findAll(any()))
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

	verify(hourValueGateway, times(1)).findAll(eq(aQuery));
    }
    
    @Test
    void givenAValidQuery_whenCallsGetHourValueIsEmpty_shouldReturnPaginated() {
	// given
	final var hourValues = List.<HourValue>of();
	
	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";
	final var expectedTotal = 2;
	
	final var expectedItems = hourValues.stream()
		.map(ListHourValueOutput::from).toList();
	
	final var expectedPagination = new Pagination<>(expectedPage,
		expectedPerPage, expectedTotal, hourValues);
	
	when(hourValueGateway.findAll(any()))
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
	
	verify(hourValueGateway, times(1)).findAll(eq(aQuery));
    }
    
    @Test
    void givenAValidQuery_whenCallsGetHourValueThrowsError_shouldReturnError() {
	// given
	final var expectedPage = 0;
	final var expectedPerPage = 10;
	final var expectedTerms = "A";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";
	
	final var expectedErrorMessage = "Gateway error";

	when(hourValueGateway.findAll(any())).thenThrow(
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

	verify(hourValueGateway, times(1)).findAll(eq(aQuery));
    }

}
