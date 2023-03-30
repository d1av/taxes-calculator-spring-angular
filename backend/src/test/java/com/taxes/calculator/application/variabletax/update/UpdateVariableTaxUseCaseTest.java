package com.taxes.calculator.application.variabletax.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
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
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;

class UpdateVariableTaxUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultUpdateVariableTaxUseCase useCase;

    @Mock
    private VariableTaxGateway variableTaxGateway;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(variableTaxGateway, userGateway);
    }

    @Test
    void givenAValidCommand_whenCallsUpdateVariableTax_shouldReturnVariableTaxId() {
	// given
	final var aVariableTax = Fixture.Tax.variableNullUser();
	final var expectedId = aVariableTax.getId().getValue();

	final var expectedDentalShop = BigDecimal.valueOf(1);
	final var expectedProsthetist = BigDecimal.valueOf(2);
	final var expectedTravel = BigDecimal.valueOf(3);
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final var expectedUser = Fixture.Users.abella();

	final var aCommand = UpdateVariableTaxCommand.with(expectedId,
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser.getId().getValue());

	when(variableTaxGateway.findById(any()))
		.thenReturn(Optional.of(aVariableTax));

	when(variableTaxGateway.update(any()))
		.thenAnswer(returnsFirstArg());

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));
	// when
	final var actualOutput = useCase.execute(aCommand);

	// then

	assertEquals(expectedId, actualOutput.id());

	Mockito.verify(variableTaxGateway, times(1))
		.update(argThat(aTax -> Objects.equals(expectedId,
			aTax.getId().getValue())
			&& Objects.equals(expectedDentalShop,
				aTax.getDentalShop())
			&& Objects.equals(expectedProsthetist,
				aTax.getProsthetist())
			&& Objects.equals(expectedTravel,
				aTax.getTravel())
			&& Objects.equals(expectedCreditCard,
				aTax.getCreditCard())
			&& Objects.equals(expectedWeekend,
				aTax.getWeekend())
			&& Objects.equals(
				expectedUser.getId().getValue(),
				aTax.getUserId().getValue())));
    }

    @Test
    void givenAValidCommandWithNullUser_whenCallsUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aVariableTax = Fixture.Tax.variableNullUser();
	final var expectedId = aVariableTax.getId().getValue();

	final var expectedDentalShop = BigDecimal.valueOf(1);
	final var expectedProsthetist = BigDecimal.valueOf(2);
	final var expectedTravel = BigDecimal.valueOf(3);
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final User expectedUser = null;

	final var expectedErrorMessage = "User could not be found: INVALID-USER-ID";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateVariableTaxCommand.with(expectedId,
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		"INVALID-USER-ID");

	when(variableTaxGateway.findById(any()))
		.thenReturn(Optional.of(aVariableTax));

	when(userGateway.findById(any()))
		.thenReturn(Optional.empty());
	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalidNullDentalCommand_whenCallsUpdateVariableTax_shouldReturnVariableTaxId() {
	// given
	final var aVariableTax = Fixture.Tax.variable();
	final var expectedId = aVariableTax.getId().getValue();

	final BigDecimal expectedDentalShop = null;
	final var expectedProsthetist = BigDecimal.valueOf(2);
	final var expectedTravel = BigDecimal.valueOf(3);
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final var expectedUser = Fixture.Users.abella();

	final var expectedErrorMessage = "'dentalShop' should not be null";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateVariableTaxCommand.with(expectedId,
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser.getId().getValue());

	when(variableTaxGateway.findById(any()))
		.thenReturn(Optional.of(aVariableTax));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalidNullProsthetistCommand_whenCallsUpdateVariableTax_shouldReturnVariableTaxId() {
	// given
	final var aVariableTax = Fixture.Tax.variable();
	final var expectedId = aVariableTax.getId().getValue();

	final var expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = null;
	final var expectedTravel = BigDecimal.valueOf(3);
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final var expectedUser = Fixture.Users.abella();

	final var expectedErrorMessage1 = "'prosthetist' should not be null";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateVariableTaxCommand.with(expectedId,
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser.getId().getValue());

	when(variableTaxGateway.findById(any()))
		.thenReturn(Optional.of(aVariableTax));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage1,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalidNullTravelCommand_whenCallsUpdateVariableTax_shouldReturnVariableTaxId() {
	// given
	final var aVariableTax = Fixture.Tax.variable();
	final var expectedId = aVariableTax.getId().getValue();

	final var expectedDentalShop = Fixture.bigDecimal(2);
	final var expectedProsthetist = Fixture.bigDecimal(2);
	final BigDecimal expectedTravel = null;
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final var expectedUser = Fixture.Users.abella();

	final var expectedErrorMessage = "'travel' should not be null";
	final var expectedErrorCount = 1;

	when(variableTaxGateway.findById(any()))
		.thenReturn(Optional.of(aVariableTax));
	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	final var aCommand = UpdateVariableTaxCommand.with(expectedId,
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser.getId().getValue());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalidCreditCardTravelCommand_whenCallsUpdateVariableTax_shouldReturnVariableTaxId() {
	// given
	final var aVariableTax = Fixture.Tax.variable();
	final var expectedId = aVariableTax.getId().getValue();

	final var expectedDentalShop = Fixture.bigDecimal(4);
	final var expectedProsthetist = Fixture.bigDecimal(4);
	final var expectedTravel = Fixture.bigDecimal(3);
	final BigDecimal expectedCreditCard = null;
	final var expectedWeekend = BigDecimal.valueOf(4);
	final var expectedUser = Fixture.Users.abella();

	final var expectedErrorMessage = "'creditCard' should not be null";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateVariableTaxCommand.with(expectedId,
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser.getId().getValue());

	when(variableTaxGateway.findById(any()))
		.thenReturn(Optional.of(aVariableTax));
	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalidWeekendCommand_whenCallsUpdateVariableTax_shouldReturnVariableTaxId() {
	// given
	final var aVariableTax = Fixture.Tax.variable();
	final var expectedId = aVariableTax.getId().getValue();

	final var expectedDentalShop = Fixture.bigDecimal(3);
	final var expectedProsthetist = Fixture.bigDecimal(2);
	final var expectedTravel = Fixture.bigDecimal(3);
	final var expectedCreditCard = Fixture.bigDecimal(2);
	final BigDecimal expectedWeekend = null;
	final var expectedUser = Fixture.Users.abella();

	final var expectedErrorMessage = "'weekend' should not be null";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateVariableTaxCommand.with(expectedId,
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser.getId().getValue());

	when(variableTaxGateway.findById(any()))
		.thenReturn(Optional.of(aVariableTax));
	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

}
