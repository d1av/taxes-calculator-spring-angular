package com.taxes.calculator.application.hourvalue.update;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.application.fixedtax.update.UpdateFixedTaxCommand;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.hourvalue.HourValueID;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;

class UpdateHourValueUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultUpdateHourValueUseCase useCase;

    @Mock
    private HourValueGateway hourValueGateway;
    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(hourValueGateway, userGateway);
    }

    @Test
    void givenAValidCommand_whenCallsUpdateHourValue_shouldReturnHourValueId() {
	// given
	final var aHourValue = Fixture.HourValues.valid();
	final var anId = aHourValue.getId();

	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(2);
	final Integer expectedDaysOfWork = Fixture.daysOfWork();
	final User expectedUser = Fixture.Users.abella();

	when(hourValueGateway.findById(eq(anId)))
		.thenReturn(Optional.of(aHourValue));

	when(hourValueGateway.update(any()))
		.thenAnswer(returnsFirstArg());

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	final var aCommand = UpdateHourValueCommand.with(
		anId.getValue(), expectedSalary,
		expectedPersonalHourValue, expectedDaysOfWork,
		expectedUser.getId().getValue());

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then

	assertEquals(anId.getValue(), actualOutput.id());

	Mockito.verify(hourValueGateway, times(1))
		.update(argThat(aHour -> Objects.equals(
			anId.getValue(), aHour.getId().getValue())
			&& Objects.equals(expectedSalary,
				aHour.getExpectedSalary())
			&& Objects.equals(expectedPersonalHourValue,
				aHour.getPersonalHourValue())
			&& Objects.equals(expectedDaysOfWork,
				aHour.getDaysOfWork())
			&& Objects.equals(
				expectedUser.getId().getValue(),
				aHour.getUserId().getValue())));
    }

    @Test
    void givenAValidCommand_whenCallsUpdateHourValueWithNullUser_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();
	final var anId = aHourValue.getId();

	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(2);
	final Integer expectedDaysOfWork = Fixture.daysOfWork();
	final String expectedUser = null;

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "User cannot be null on update";

	when(hourValueGateway.findById(eq(anId)))
		.thenReturn(Optional.of(aHourValue));

	final var aCommand = UpdateHourValueCommand.with(
		anId.getValue(), expectedSalary,
		expectedPersonalHourValue, expectedDaysOfWork,
		expectedUser);

	// when
	final var actualException = assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(hourValueGateway, times(0)).update(any());
	Mockito.verify(userGateway, times(0)).update(any());
    }

    @Test
    void givenAValidCommand_whenCallsUpdateHourValueWithInvalidUser_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();
	final var anId = aHourValue.getId();

	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(2);
	final Integer expectedDaysOfWork = Fixture.daysOfWork();
	final HourValueID expectedUser = HourValueID
		.from("INVALID-USER");

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "Could find the user with id: INVALID-USER";

	when(hourValueGateway.findById(eq(anId)))
		.thenReturn(Optional.of(aHourValue));

	when(userGateway.findById(any()))
		.thenReturn(Optional.empty());

	final var aCommand = UpdateHourValueCommand.with(
		anId.getValue(), expectedSalary,
		expectedPersonalHourValue, expectedDaysOfWork,
		expectedUser.getValue());

	// when
	final var actualException = assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(hourValueGateway, times(0)).update(any());
	Mockito.verify(userGateway, times(0)).update(any());
    }

    @Test
    void givenANullExpectedSalaryCommand_whenCallsUpdateHourValue_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();
	final var anId = aHourValue.getId();

	final BigDecimal expectedSalary = null;
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(2);
	final Integer expectedDaysOfWork = Fixture.daysOfWork();
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'expectedSalary' should not be null";

	when(hourValueGateway.findById(eq(anId)))
		.thenReturn(Optional.of(aHourValue));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	final var aCommand = UpdateHourValueCommand.with(
		anId.getValue(), expectedSalary,
		expectedPersonalHourValue, expectedDaysOfWork,
		expectedUser.getId().getValue());

	// when
	final var actualException = assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(hourValueGateway, times(0)).update(any());
	Mockito.verify(userGateway, times(0)).update(any());
    }

    @Test
    void givenANullPersonalHourValueCommand_whenCallsUpdateHourValue_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();
	final var anId = aHourValue.getId();

	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = null;
	final Integer expectedDaysOfWork = Fixture.daysOfWork();
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'hourValue' should not be null";

	when(hourValueGateway.findById(eq(anId)))
		.thenReturn(Optional.of(aHourValue));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	final var aCommand = UpdateHourValueCommand.with(
		anId.getValue(), expectedSalary,
		expectedPersonalHourValue, expectedDaysOfWork,
		expectedUser.getId().getValue());

	// when
	final var actualException = assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(hourValueGateway, times(0)).update(any());
	Mockito.verify(userGateway, times(0)).update(any());
    }

    @Test
    void givenANullDaysOfWorkCommand_whenCallsUpdateHourValue_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();
	final var anId = aHourValue.getId();

	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(3);
	final Integer expectedDaysOfWork = null;
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'daysOfWork' should not be null";

	when(hourValueGateway.findById(eq(anId)))
		.thenReturn(Optional.of(aHourValue));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	final var aCommand = UpdateHourValueCommand.with(
		anId.getValue(), expectedSalary,
		expectedPersonalHourValue, expectedDaysOfWork,
		expectedUser.getId().getValue());

	// when
	final var actualException = assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(hourValueGateway, times(0)).update(any());
	Mockito.verify(userGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidMaxDaysOfWorkCommand_whenCallsUpdateHourValue_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();
	final var anId = aHourValue.getId();

	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(3);
	final Integer expectedDaysOfWork = 32;
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'daysOfWork' should be a number between 1 and 31";

	when(hourValueGateway.findById(eq(anId)))
		.thenReturn(Optional.of(aHourValue));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	final var aCommand = UpdateHourValueCommand.with(
		anId.getValue(), expectedSalary,
		expectedPersonalHourValue, expectedDaysOfWork,
		expectedUser.getId().getValue());

	// when
	final var actualException = assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(hourValueGateway, times(0)).update(any());
	Mockito.verify(userGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidMinDaysOfWorkCommand_whenCallsUpdateHourValue_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();
	final var anId = aHourValue.getId();

	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedPersonalHourValue = Fixture
		.bigDecimal(3);
	final Integer expectedDaysOfWork = 0;
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'daysOfWork' should be a number between 1 and 31";

	when(hourValueGateway.findById(eq(anId)))
		.thenReturn(Optional.of(aHourValue));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	final var aCommand = UpdateHourValueCommand.with(
		anId.getValue(), expectedSalary,
		expectedPersonalHourValue, expectedDaysOfWork,
		expectedUser.getId().getValue());

	// when
	final var actualException = assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(hourValueGateway, times(0)).update(any());
	Mockito.verify(userGateway, times(0)).update(any());
    }

}
