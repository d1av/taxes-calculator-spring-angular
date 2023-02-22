package com.taxes.calculator.application.hourvalue.create;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;

class CreateHourValueUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateHourValueUseCase useCase;

    @Mock
    private HourValueGateway hourValueGateway;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(hourValueGateway, userGateway);
    }

    @Test
    void givenAValidCommand_whenCallsCreateHourValue_shouldReturnHourValueId() {
	// given
	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(2);
	final Integer expectedDaysOfWork = Fixture.daysOfWork();
	final User expectedUser = Fixture.Users.abella();

	final var aCommand = CreateHourValueCommand.from(
		expectedSalary, expectedPersonalHourValue,
		expectedDaysOfWork, expectedUser);

	when(hourValueGateway.create(any()))
		.thenAnswer(returnsFirstArg());

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));
	// when
	final var actualOutput = useCase.execute(aCommand);
	// then

	assertNotNull(actualOutput.id());

	Mockito.verify(hourValueGateway, times(1)).create(argThat(
		aHourValue -> Objects.nonNull(aHourValue.getId())
			&& Objects.equals(expectedSalary,
				aHourValue.getExpectedSalary())
			&& Objects.equals(expectedDaysOfWork,
				aHourValue.getDaysOfWork())
			&& Objects.equals(expectedUser.getId(),
				aHourValue.getUserId())));
    }

    @Test
    void givenAValidCommandWithNullUser_whenCallsCreateHourValue_shouldReturnHourValueId() {
	// given
	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(2);
	final Integer expectedDaysOfWork = Fixture.daysOfWork();
	final User expectedUser = null;

	final var aCommand = CreateHourValueCommand.from(
		expectedSalary, expectedPersonalHourValue,
		expectedDaysOfWork, expectedUser);

	when(hourValueGateway.create(any()))
		.thenAnswer(returnsFirstArg());

	// when
	final var actualOutput = useCase.execute(aCommand);
	// then

	assertNotNull(actualOutput.id());

	Mockito.verify(hourValueGateway, times(1)).create(argThat(
		aHourValue -> Objects.nonNull(aHourValue.getId())
			&& Objects.equals(expectedSalary,
				aHourValue.getExpectedSalary())
			&& Objects.equals(expectedDaysOfWork,
				aHourValue.getDaysOfWork())
			&& Objects.equals(expectedUser,
				aHourValue.getUserId())));
    }

    @Test
    void givenAInvalidCommandWithNullSalary_whenCallsCreateHourValue_shouldReturnNotification() {
	// given
	final BigDecimal expectedSalary = null;
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(2);
	final Integer expectedDaysOfWork = Fixture.daysOfWork();
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorMessage = "'expectedSalary' should not be null";
	final var expectedErrorsCount = 1;

	final var aCommand = CreateHourValueCommand.from(
		expectedSalary, expectedPersonalHourValue,
		expectedDaysOfWork, expectedUser);

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	// then

	assertNotNull(actualException);
	assertEquals(expectedErrorsCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(hourValueGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidCommandWithNullPersonalHourValue_whenCallsCreateHourValue_shouldReturnNotification() {
	// given
	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = null;
	final Integer expectedDaysOfWork = Fixture.daysOfWork();
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorMessage = "'hourValue' should not be null";
	final var expectedErrorsCount = 1;

	final var aCommand = CreateHourValueCommand.from(
		expectedSalary, expectedPersonalHourValue,
		expectedDaysOfWork, expectedUser);

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	// then

	assertNotNull(actualException);
	assertEquals(expectedErrorsCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(hourValueGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidCommandWithNullDaysOfWork_whenCallsCreateHourValue_shouldReturnNotification() {
	// given
	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(2);
	final Integer expectedDaysOfWork = null;
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorMessage = "'daysOfWork' should not be null";
	final var expectedErrorsCount = 1;

	final var aCommand = CreateHourValueCommand.from(
		expectedSalary, expectedPersonalHourValue,
		expectedDaysOfWork, expectedUser);

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	// then

	assertNotNull(actualException);
	assertEquals(expectedErrorsCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(hourValueGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidCommandWithInvalidMinDaysOfWork_whenCallsCreateHourValue_shouldReturnNotification() {
	// given
	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(2);
	final Integer expectedDaysOfWork = 0;
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorMessage = "'daysOfWork' should be a number between 1 and 31";
	final var expectedErrorsCount = 1;

	final var aCommand = CreateHourValueCommand.from(
		expectedSalary, expectedPersonalHourValue,
		expectedDaysOfWork, expectedUser);

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	// then

	assertNotNull(actualException);
	assertEquals(expectedErrorsCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(hourValueGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidCommandWithInvalidMaxDaysOfWork_whenCallsCreateHourValue_shouldReturnNotification() {
	// given
	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(2);
	final Integer expectedDaysOfWork = 32;
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorMessage = "'daysOfWork' should be a number between 1 and 31";
	final var expectedErrorsCount = 1;

	final var aCommand = CreateHourValueCommand.from(
		expectedSalary, expectedPersonalHourValue,
		expectedDaysOfWork, expectedUser);

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	// then

	assertNotNull(actualException);
	assertEquals(expectedErrorsCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(hourValueGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidCommandWithInvalidUser_whenCallsCreateHourValue_shouldReturnNotification() {
	// given
	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(2);
	final Integer expectedDaysOfWork = Fixture.daysOfWork();
	final User invalidUser = Fixture.Users.abella();

	final var expectedErrorMessage = "Could find the user with id: %s"
		.formatted(invalidUser.getId().getValue());

	when(userGateway.findById(any()))
		.thenReturn(Optional.empty());

	final var aCommand = CreateHourValueCommand.from(
		expectedSalary, expectedPersonalHourValue,
		expectedDaysOfWork, invalidUser);

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	// then

	assertNotNull(actualException);
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(hourValueGateway, times(0)).create(any());
    }
}
