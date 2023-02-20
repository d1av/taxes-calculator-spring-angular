package com.taxes.calculator.application.hourvalue.retrieve.get;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.hourvalue.HourValueID;
import com.taxes.calculator.domain.user.User;

class GetHourValueByIdUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetHourValueByIdUseCase useCase;

    @Mock
    private HourValueGateway hourValueGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(hourValueGateway);
    }

    @Test
    void givenAValidId_whenCallsGetHourValue_shouldReturnHourValue() {
	// given
	final var aHourValue = Fixture.HourValues.valid();
	final var expectedId = aHourValue.getId();
	final BigDecimal expectedSalary = aHourValue
		.getExpectedSalary();
	final BigDecimal expectedPersonalHourValue = aHourValue
		.getPersonalHourValue();
	final Integer expectedDaysOfWork = aHourValue.getDaysOfWork();
	final User expectedUser = aHourValue.getUser();

	when(hourValueGateway.findById(any()))
		.thenReturn(Optional.of(aHourValue));
	// when
	final var actualOutput = useCase
		.execute(expectedId.getValue());

	// then
	Assertions.assertEquals(expectedId.getValue(),
		actualOutput.id());
	Assertions.assertEquals(expectedSalary,
		actualOutput.expectedSalary());
	Assertions.assertEquals(expectedPersonalHourValue,
		actualOutput.personalHourValue());
	Assertions.assertEquals(expectedDaysOfWork,
		actualOutput.daysOfWork());
	Assertions.assertEquals(expectedUser, actualOutput.userId());
    }

    @Test
    void givenAValidId_whenCallsGetHourValueAndDoesntExist_shouldReturnEmpty() {
	// given
	final var expectedId = HourValueID.from("INVALID-ID");

	final var expectedErrorMessage = "HourValue with ID %s was not found"
		.formatted(expectedId.getValue());

	when(hourValueGateway.findById(any()))
		.thenReturn(Optional.empty());
	// when
	final var actualException = Assertions.assertThrows(
		NotFoundException.class,
		() -> useCase.execute(expectedId.getValue()));

	// then
	Assertions.assertEquals(expectedErrorMessage,
		actualException.getMessage());
    }

}
