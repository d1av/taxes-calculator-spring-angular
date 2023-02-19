package com.taxes.calculator.application.hourvalue.delete;

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
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.hourvalue.HourValueID;

class DeleteHourValueUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteHourValueUseCase useCase;

    @Mock
    private HourValueGateway hourValueGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(hourValueGateway);
    }

    @Test
    void givenAValidHourValueId_whenCallsDeleteHourValue_shouldReturnOk() {
	// given
	final var expectedHourValue = Fixture.HourValues.valid();

	final var expectedId = expectedHourValue.getId();

	doNothing().when(hourValueGateway).deleteById(any());
	// when
	Assertions.assertDoesNotThrow(
		() -> useCase.execute(expectedId.getValue()));

	// then
	Mockito.verify(hourValueGateway, times(1))
		.deleteById(expectedId);

    }

    @Test
    void givenAInvalidHourValueId_whenCallsDeleteHourValue_shouldReturnOk() {
	// given
	final var expectedHourValue = Fixture.HourValues.valid();

	final var expectedId = HourValueID.from("INVALID_ID");

	doNothing().when(hourValueGateway).deleteById(any());
	// when
	Assertions.assertDoesNotThrow(
		() -> useCase.execute(expectedId.getValue()));

	// then
	Mockito.verify(hourValueGateway, times(1))
		.deleteById(expectedId);
    }

    @Test
    void givenAValidHourValueId_whenCallsDeleteHourValueAndGatewatThrowsUnexpectedError_shouldReceiveException() {
	// given
	final var expectedHourValue = Fixture.HourValues.valid();
	final var expectedId = expectedHourValue.getId();

	doThrow(new IllegalStateException("Gateway error"))
		.when(hourValueGateway).deleteById(any());
	// when
	Assertions.assertThrows(IllegalStateException.class,
		() -> useCase.execute(expectedId.getValue()));

	// then

	Mockito.verify(hourValueGateway, times(1))
		.deleteById(expectedId);

    }

}
