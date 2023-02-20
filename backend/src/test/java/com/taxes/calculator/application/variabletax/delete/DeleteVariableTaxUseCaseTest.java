package com.taxes.calculator.application.variabletax.delete;

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
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxID;

class DeleteVariableTaxUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteVariableTaxUseCase useCase;

    @Mock
    private VariableTaxGateway variableTaxGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(variableTaxGateway);
    }

    @Test
    void givenAValidVariableTaxId_whenCallsDeleteVariableTax_shouldReturnOk() {
	// given
	final var expectedVariableTax = Fixture.Tax.variable();

	final var expectedId = expectedVariableTax.getId();

	doNothing().when(variableTaxGateway).deleteById(any());
	// when
	Assertions.assertDoesNotThrow(
		() -> useCase.execute(expectedId.getValue()));

	// then
	Mockito.verify(variableTaxGateway, times(1))
		.deleteById(expectedId);

    }

    @Test
    void givenAInvalidVariableTaxId_whenCallsDeleteVariableTax_shouldReturnOk() {
	// given
	final var expectedVariableTax = Fixture.Tax.variable();

	final var expectedId = VariableTaxID.from("INVALID_ID");

	doNothing().when(variableTaxGateway).deleteById(any());
	// when
	Assertions.assertDoesNotThrow(
		() -> useCase.execute(expectedId.getValue()));

	// then
	Mockito.verify(variableTaxGateway, times(1))
		.deleteById(expectedId);
    }

    @Test
    void givenAValidVariableTaxId_whenCallsDeleteVariableTaxAndGatewatThrowsUnexpectedError_shouldReceiveException() {
	// given
	final var expectedVariableTax = Fixture.Tax.variable();
	final var expectedId = expectedVariableTax.getId();

	doThrow(new IllegalStateException("Gateway error"))
		.when(variableTaxGateway).deleteById(any());
	// when
	Assertions.assertThrows(IllegalStateException.class,
		() -> useCase.execute(expectedId.getValue()));

	// then

	Mockito.verify(variableTaxGateway, times(1))
		.deleteById(expectedId);

    }

}
