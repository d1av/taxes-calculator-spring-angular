package com.taxes.calculator.application.variabletax.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;

class CreateVariableTaxUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateVariableTaxUseCase useCase;

    @Mock
    private VariableTaxGateway variableTaxGateway;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(variableTaxGateway, userGateway);
    }

    @Test
    void givenAValidCommand_whenCallsCreateVariableTax_shouldReturnVariableTaxId() {
	// given
	final var expectedDentalShop = BigDecimal.valueOf(3);
	final var expectedProsthetist = BigDecimal.valueOf(2);
	final var expectedTravel = BigDecimal.valueOf(3);
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final var expectedUser = Fixture.Users.abella();
	final var expectedUserId = expectedUser.getId();

	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser.getId().getValue());

	when(variableTaxGateway.create(any()))
		.thenAnswer(returnsFirstArg());

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));
	// when
	final var actualOutput = useCase.execute(aCommand);

	// then

	assertNotNull(actualOutput.id());

	Mockito.verify(variableTaxGateway, times(1))
		.create(argThat(aTax -> Objects.nonNull(aTax.getId())
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
			&& Objects.equals(expectedUserId,
				aTax.getUserId())));
    }

    @Test
    void givenAValidCommandWithValidUser_whenCallsCreateVariableTax_shouldReturnVariableTaxId() {
	// given
	final var expectedDentalShop = BigDecimal.valueOf(1);
	final var expectedProsthetist = BigDecimal.valueOf(2);
	final var expectedTravel = BigDecimal.valueOf(3);
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final User expectedUserEntity = Fixture.Users.asa();
	final String expectedUserId = expectedUserEntity.getId()
		.getValue();

	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUserId);

	when(variableTaxGateway.create(any()))
		.thenAnswer(returnsFirstArg());

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUserEntity));
	// when
	final var actualOutput = useCase.execute(aCommand);

	// then

	assertNotNull(actualOutput.id());

	Mockito.verify(variableTaxGateway, times(1))
		.create(argThat(aTax -> Objects.nonNull(aTax.getId())
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
			&& Objects.equals(expectedUserEntity.getId(),
				aTax.getUserId())));
    }

    @Test
    void givenAInvalidNullDentalCommand_whenCallsCreateVariableTax_shouldReturnVariableTaxId() {
	// given
	final BigDecimal expectedDentalShop = null;
	final var expectedProsthetist = BigDecimal.valueOf(2);
	final var expectedTravel = BigDecimal.valueOf(3);
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final var expectedUser = Fixture.Users.abella();

	final var expectedErrorMessage1 = "'dentalShop' should not be null";
	final var expectedErrorMessage2 = "'userId' should not be null";
	final var expectedErrorCount = 2;

	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser.getId().getValue());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.firstError().message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
    }

    @Test
    void givenAInvalidNullUserCommand_whenCallsCreateVariableTax_shouldReturnVariableTaxId() {
	// given
	final var expectedDentalShop = Fixture.bigDecimal(4);
	final var expectedProsthetist = BigDecimal.valueOf(2);
	final var expectedTravel = BigDecimal.valueOf(3);
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final String expectedUser = null;

	final var expectedErrorMessage1 = "'userId' should not be null";
	final var expectedErrorCount = 1;

	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser);

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalidNullProsthetistCommand_whenCallsCreateVariableTax_shouldReturnVariableTaxId() {
	// given
	final var expectedDentalShop = Fixture.bigDecimal(2);
	final BigDecimal expectedProsthetist = null;
	final var expectedTravel = BigDecimal.valueOf(3);
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final var expectedUser = Fixture.Users.abella();

	final var expectedErrorMessage1 = "'prosthetist' should not be null";
	final var expectedErrorMessage2 = "'userId' should not be null";
	final var expectedErrorCount = 2;

	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser.getId().getValue());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.firstError().message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
    }

    @Test
    void givenAInvalidNullTravelCommand_whenCallsCreateVariableTax_shouldReturnVariableTaxId() {
	// given
	final var expectedDentalShop = Fixture.bigDecimal(2);
	final var expectedProsthetist = Fixture.bigDecimal(2);
	final BigDecimal expectedTravel = null;
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final var expectedUser = Fixture.Users.abella();

	final var expectedErrorMessage1 = "'travel' should not be null";
	final var expectedErrorMessage2 = "'userId' should not be null";
	final var expectedErrorCount = 2;

	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser.getId().getValue());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.firstError().message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
    }

    @Test
    void givenAInvalidCreditCardTravelCommand_whenCallsCreateVariableTax_shouldReturnVariableTaxId() {
	// given
	final var expectedDentalShop = Fixture.bigDecimal(2);
	final var expectedProsthetist = Fixture.bigDecimal(2);
	final var expectedTravel = Fixture.bigDecimal(3);
	final BigDecimal expectedCreditCard = null;
	final var expectedWeekend = BigDecimal.valueOf(4);
	final var expectedUser = Fixture.Users.abella();

	final var expectedErrorMessage1 = "'creditCard' should not be null";
	final var expectedErrorMessage2 = "'userId' should not be null";
	final var expectedErrorCount = 2;

	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser.getId().getValue());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.firstError().message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
    }

    @Test
    void givenAInvalidWeekendCommand_whenCallsCreateVariableTax_shouldReturnVariableTaxId() {
	// given
	final var expectedDentalShop = Fixture.bigDecimal(2);
	final var expectedProsthetist = Fixture.bigDecimal(2);
	final var expectedTravel = Fixture.bigDecimal(3);
	final var expectedCreditCard = Fixture.bigDecimal(2);
	final BigDecimal expectedWeekend = null;
	final var expectedUser = Fixture.Users.abella();

	final var expectedErrorMessage1 = "'weekend' should not be null";
	final var expectedErrorMessage2 = "'userId' should not be null";
	final var expectedErrorCount = 2;

	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser.getId().getValue());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.firstError().message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
    }
}
