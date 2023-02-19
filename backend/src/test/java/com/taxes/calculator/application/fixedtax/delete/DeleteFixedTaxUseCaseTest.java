package com.taxes.calculator.application.fixedtax.delete;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.fixedtax.FixedTaxID;

class DeleteFixedTaxUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteFixedTaxUseCase useCase;

    @Mock
    private FixedTaxGateway fixedTaxGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(fixedTaxGateway);
    }

    @Test
    void givenAValidFixedTaxId_whenCallsDeleteFixedTax_shouldReturnOk() {
	// given
	final var expectedFixedTax = Fixture.Tax.fixed();

	final var expectedId = expectedFixedTax.getId();

	doNothing().when(fixedTaxGateway).deleteById(any());
	// when
	Assertions.assertDoesNotThrow(
		() -> useCase.execute(expectedId.getValue()));

	// then
	Mockito.verify(fixedTaxGateway, times(1))
		.deleteById(expectedId);

    }

    @Test
    void givenAInvalidFixedTaxId_whenCallsDeleteFixedTax_shouldReturnOk() {
	// given
	final var expectedFixedTax = Fixture.Tax.fixed();

	final var expectedId = FixedTaxID.from("INVALID_ID");

	doNothing().when(fixedTaxGateway).deleteById(any());
	// when
	Assertions.assertDoesNotThrow(
		() -> useCase.execute(expectedId.getValue()));

	// then
	Mockito.verify(fixedTaxGateway, times(1))
		.deleteById(expectedId);
    }

    @Test
    void givenAValidFixedTaxId_whenCallsDeleteFixedTaxAndGatewatThrowsUnexpectedError_shouldReceiveException() {
	// given
	final var expectedFixedTax = Fixture.Tax.fixed();
	final var expectedId = expectedFixedTax.getId();

	doThrow(new IllegalStateException("Gateway error"))
		.when(fixedTaxGateway).deleteById(any());
	// when
	Assertions.assertThrows(IllegalStateException.class,
		() -> useCase.execute(expectedId.getValue()));

	// then


	Mockito.verify(fixedTaxGateway, times(1))
		.deleteById(expectedId);

    }

}
