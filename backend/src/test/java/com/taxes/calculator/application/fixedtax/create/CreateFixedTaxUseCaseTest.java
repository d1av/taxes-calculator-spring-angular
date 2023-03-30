package com.taxes.calculator.application.fixedtax.create;

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
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.user.UserID;

class CreateFixedTaxUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateFixedTaxUseCase useCase;

    @Mock
    private FixedTaxGateway fixedTaxGateway;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(fixedTaxGateway, userGateway);
    }

    @Test
    void givenAValidCommand_whenCallsCreateFixedTax_shouldReturnFixedTaxId() {
	// given
	final var expectedRegionalCouncil = BigDecimal.valueOf(1);
	final var expectedTaxOverWork = BigDecimal.valueOf(2);
	final var expectedIncomeTax = BigDecimal.valueOf(3);
	final var expectedAccountant = BigDecimal.valueOf(4);
	final var expectedDentalShop = BigDecimal.valueOf(5);
	final var expectedTransport = BigDecimal.valueOf(6);
	final var expectedFood = BigDecimal.valueOf(7);
	final var expectedEducation = BigDecimal.valueOf(8);
	final var expectedOtherFixedCosts = BigDecimal.valueOf(9);
	final var expectedUser = Fixture.Users.abella();
	final var expectedUserId = expectedUser.getId();

	final var aCommand = CreateFixedTaxCommand.with(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUserId);

	when(fixedTaxGateway.create(any()))
		.thenAnswer(returnsFirstArg());

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));
	// when
	final var actualOutput = useCase.execute(aCommand);

	// then

	assertNotNull(actualOutput.id());
	assertEquals(expectedRegionalCouncil,
		actualOutput.regionalCouncil());
	assertEquals(expectedTaxOverWork, actualOutput.taxOverWork());
	assertEquals(expectedIncomeTax, actualOutput.incomeTax());
	assertEquals(expectedAccountant, actualOutput.accountant());
	assertEquals(expectedDentalShop, actualOutput.dentalShop());
	assertEquals(expectedTransport, actualOutput.transport());
	assertEquals(expectedFood, actualOutput.food());
	assertEquals(expectedEducation, actualOutput.education());
	assertEquals(expectedOtherFixedCosts,
		actualOutput.otherFixedCosts());
	assertEquals(expectedUser.getId().getValue(),
		actualOutput.userId());

	Mockito.verify(fixedTaxGateway, times(1))
		.create(argThat(aTax -> Objects.nonNull(aTax.getId())
			&& Objects.equals(expectedRegionalCouncil,
				aTax.getRegionalCouncil())
			&& Objects.equals(expectedTaxOverWork,
				aTax.getTaxOverWork())
			&& Objects.equals(expectedIncomeTax,
				aTax.getIncomeTax())
			&& Objects.equals(expectedAccountant,
				aTax.getAccountant())
			&& Objects.equals(expectedDentalShop,
				aTax.getDentalShop())
			&& Objects.equals(expectedTransport,
				aTax.getTransport())
			&& Objects.equals(expectedFood,
				aTax.getFood())
			&& Objects.equals(expectedEducation,
				aTax.getEducation())
			&& Objects.equals(expectedOtherFixedCosts,
				aTax.getOtherFixedCosts())
			&& Objects.equals(expectedUserId,
				aTax.getUser())));
    }

    @Test
    void givenAValidCommandWithNullUser_whenCallsCreateFixedTax_shouldReturnFixedTaxId() {
	// given
	final var expectedRegionalCouncil = BigDecimal.valueOf(1);
	final var expectedTaxOverWork = BigDecimal.valueOf(2);
	final var expectedIncomeTax = BigDecimal.valueOf(3);
	final var expectedAccountant = BigDecimal.valueOf(4);
	final var expectedDentalShop = BigDecimal.valueOf(5);
	final var expectedTransport = BigDecimal.valueOf(6);
	final var expectedFood = BigDecimal.valueOf(7);
	final var expectedEducation = BigDecimal.valueOf(8);
	final var expectedOtherFixedCosts = BigDecimal.valueOf(9);
	final UserID expectedUser = null;

	final var aCommand = CreateFixedTaxCommand.with(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser);

	when(fixedTaxGateway.create(any()))
		.thenAnswer(returnsFirstArg());

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then

	assertNotNull(actualOutput.id());
	assertEquals(expectedRegionalCouncil,
		actualOutput.regionalCouncil());
	assertEquals(expectedTaxOverWork, actualOutput.taxOverWork());
	assertEquals(expectedIncomeTax, actualOutput.incomeTax());
	assertEquals(expectedAccountant, actualOutput.accountant());
	assertEquals(expectedDentalShop, actualOutput.dentalShop());
	assertEquals(expectedTransport, actualOutput.transport());
	assertEquals(expectedFood, actualOutput.food());
	assertEquals(expectedEducation, actualOutput.education());
	assertEquals(expectedOtherFixedCosts,
		actualOutput.otherFixedCosts());
	assertEquals(expectedUser, actualOutput.userId());

	Mockito.verify(fixedTaxGateway, times(1))
		.create(argThat(aTax -> Objects.nonNull(aTax.getId())
			&& Objects.equals(expectedRegionalCouncil,
				aTax.getRegionalCouncil())
			&& Objects.equals(expectedTaxOverWork,
				aTax.getTaxOverWork())
			&& Objects.equals(expectedIncomeTax,
				aTax.getIncomeTax())
			&& Objects.equals(expectedAccountant,
				aTax.getAccountant())
			&& Objects.equals(expectedDentalShop,
				aTax.getDentalShop())
			&& Objects.equals(expectedTransport,
				aTax.getTransport())
			&& Objects.equals(expectedFood,
				aTax.getFood())
			&& Objects.equals(expectedEducation,
				aTax.getEducation())
			&& Objects.equals(expectedOtherFixedCosts,
				aTax.getOtherFixedCosts())
			&& Objects.equals(expectedUser,
				aTax.getUser())));
    }

    @Test
    void givenAInvalidCommandWithNullRegional_whenCallsCreateFixedTax_shouldReturnFixedTaxId() {
	// given
	final BigDecimal expectedRegionalCouncil = null;
	final var expectedTaxOverWork = BigDecimal.valueOf(2);
	final var expectedIncomeTax = BigDecimal.valueOf(3);
	final var expectedAccountant = BigDecimal.valueOf(4);
	final var expectedDentalShop = BigDecimal.valueOf(5);
	final var expectedTransport = BigDecimal.valueOf(6);
	final var expectedFood = BigDecimal.valueOf(7);
	final var expectedEducation = BigDecimal.valueOf(8);
	final var expectedOtherFixedCosts = BigDecimal.valueOf(9);
	final var expectedUser = Fixture.Users.abella();
	final var expectedUserId = expectedUser.getId();

	final var expectedErrorMessage1 = "'regionalCouncil' should not be null";
	final var expectedErrorMessage2 = "'regionalCouncil' should be a number";
	final var expectedErrorCount = 2;

	final var aCommand = CreateFixedTaxCommand.with(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUserId);

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

	Mockito.verify(fixedTaxGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidCommandWithNullTaxOverWork_whenCallsCreateFixedTax_shouldReturnFixedTaxId() {
	// given
	final BigDecimal expectedRegionalCouncil = BigDecimal
		.valueOf(1);
	final BigDecimal expectedTaxOverWork = null;
	final var expectedIncomeTax = BigDecimal.valueOf(3);
	final var expectedAccountant = BigDecimal.valueOf(4);
	final var expectedDentalShop = BigDecimal.valueOf(5);
	final var expectedTransport = BigDecimal.valueOf(6);
	final var expectedFood = BigDecimal.valueOf(7);
	final var expectedEducation = BigDecimal.valueOf(8);
	final var expectedOtherFixedCosts = BigDecimal.valueOf(9);
	final var expectedUser = Fixture.Users.abella();
	final var expectedUserId = expectedUser.getId();

	final var expectedErrorMessage1 = "'taxOverWork' should not be null";
	final var expectedErrorMessage2 = "'taxOverWork' should be a number";
	final var expectedErrorCount = 2;

	final var aCommand = CreateFixedTaxCommand.with(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUserId);

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

	Mockito.verify(fixedTaxGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidCommandWithNullIncomeTax_whenCallsCreateFixedTax_shouldReturnFixedTaxId() {
	// given
	final BigDecimal expectedRegionalCouncil = BigDecimal
		.valueOf(1);
	final BigDecimal expectedTaxOverWork = BigDecimal.valueOf(2);
	final BigDecimal expectedIncomeTax = null;
	final var expectedAccountant = BigDecimal.valueOf(4);
	final var expectedDentalShop = BigDecimal.valueOf(5);
	final var expectedTransport = BigDecimal.valueOf(6);
	final var expectedFood = BigDecimal.valueOf(7);
	final var expectedEducation = BigDecimal.valueOf(8);
	final var expectedOtherFixedCosts = BigDecimal.valueOf(9);
	final var expectedUser = Fixture.Users.abella();
	final var expectedUserId = expectedUser.getId();

	final var expectedErrorMessage1 = "'incomeTax' should not be null";
	final var expectedErrorMessage2 = "'incomeTax' should be a number";
	final var expectedErrorCount = 2;

	final var aCommand = CreateFixedTaxCommand.with(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUserId);

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

	Mockito.verify(fixedTaxGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidCommandWithNullAccountant_whenCallsCreateFixedTax_shouldReturnFixedTaxId() {
	// given
	final BigDecimal expectedRegionalCouncil = BigDecimal
		.valueOf(1);
	final BigDecimal expectedTaxOverWork = BigDecimal.valueOf(2);
	final BigDecimal expectedIncomeTax = BigDecimal.valueOf(3);
	final BigDecimal expectedAccountant = null;
	final BigDecimal expectedDentalShop = BigDecimal.valueOf(5);
	final BigDecimal expectedTransport = BigDecimal.valueOf(6);
	final BigDecimal expectedFood = BigDecimal.valueOf(7);
	final BigDecimal expectedEducation = BigDecimal.valueOf(8);
	final BigDecimal expectedOtherFixedCosts = BigDecimal
		.valueOf(9);
	final var expectedUser = Fixture.Users.abella();
	final var expectedUserId = expectedUser.getId();

	final var expectedErrorMessage1 = "'accountant' should not be null";
	final var expectedErrorMessage2 = "'accountant' should be a number";
	final var expectedErrorCount = 2;

	final var aCommand = CreateFixedTaxCommand.with(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUserId);

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

	Mockito.verify(fixedTaxGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidCommandWithNullDentalShop_whenCallsCreateFixedTax_shouldReturnFixedTaxId() {
	// given
	final BigDecimal expectedRegionalCouncil = BigDecimal
		.valueOf(1);
	final BigDecimal expectedTaxOverWork = BigDecimal.valueOf(2);
	final BigDecimal expectedIncomeTax = BigDecimal.valueOf(3);
	final BigDecimal expectedAccountant = BigDecimal.valueOf(4);
	final BigDecimal expectedDentalShop = null;
	final BigDecimal expectedTransport = BigDecimal.valueOf(6);
	final BigDecimal expectedFood = BigDecimal.valueOf(7);
	final BigDecimal expectedEducation = BigDecimal.valueOf(8);
	final BigDecimal expectedOtherFixedCosts = BigDecimal
		.valueOf(9);
	final var expectedUser = Fixture.Users.abella();
	final var expectedUserId = expectedUser.getId();

	final var expectedErrorMessage1 = "'dentalShop' should not be null";
	final var expectedErrorMessage2 = "'dentalShop' should be a number";
	final var expectedErrorCount = 2;

	final var aCommand = CreateFixedTaxCommand.with(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUserId);

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

	Mockito.verify(fixedTaxGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidCommandWithNullTransport_whenCallsCreateFixedTax_shouldReturnFixedTaxId() {
	// given
	final BigDecimal expectedRegionalCouncil = BigDecimal
		.valueOf(1);
	final BigDecimal expectedTaxOverWork = BigDecimal.valueOf(2);
	final BigDecimal expectedIncomeTax = BigDecimal.valueOf(3);
	final BigDecimal expectedAccountant = BigDecimal.valueOf(4);
	final BigDecimal expectedDentalShop = BigDecimal.valueOf(4);
	final BigDecimal expectedTransport = null;
	final BigDecimal expectedFood = BigDecimal.valueOf(7);
	final BigDecimal expectedEducation = BigDecimal.valueOf(8);
	final BigDecimal expectedOtherFixedCosts = BigDecimal
		.valueOf(9);
	final var expectedUser = Fixture.Users.abella();
	final var expectedUserId = expectedUser.getId();

	final var expectedErrorMessage1 = "'transport' should not be null";
	final var expectedErrorMessage2 = "'transport' should be a number";
	final var expectedErrorCount = 2;

	final var aCommand = CreateFixedTaxCommand.with(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUserId);

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

	Mockito.verify(fixedTaxGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidCommandWithNullFood_whenCallsCreateFixedTax_shouldReturnFixedTaxId() {
	// given
	final BigDecimal expectedRegionalCouncil = BigDecimal
		.valueOf(1);
	final BigDecimal expectedTaxOverWork = BigDecimal.valueOf(2);
	final BigDecimal expectedIncomeTax = BigDecimal.valueOf(3);
	final BigDecimal expectedAccountant = BigDecimal.valueOf(4);
	final BigDecimal expectedDentalShop = BigDecimal.valueOf(4);
	final BigDecimal expectedTransport = BigDecimal.valueOf(4);
	final BigDecimal expectedFood = null;
	final BigDecimal expectedEducation = BigDecimal.valueOf(8);
	final BigDecimal expectedOtherFixedCosts = BigDecimal
		.valueOf(9);
	final var expectedUser = Fixture.Users.abella();
	final var expectedUserId = expectedUser.getId();

	final var expectedErrorMessage1 = "'food' should not be null";
	final var expectedErrorMessage2 = "'food' should be a number";
	final var expectedErrorCount = 2;

	final var aCommand = CreateFixedTaxCommand.with(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUserId);

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

	Mockito.verify(fixedTaxGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidCommandWithNullEducation_whenCallsCreateFixedTax_shouldReturnFixedTaxId() {
	// given
	final BigDecimal expectedRegionalCouncil = BigDecimal
		.valueOf(1);
	final BigDecimal expectedTaxOverWork = BigDecimal.valueOf(2);
	final BigDecimal expectedIncomeTax = BigDecimal.valueOf(3);
	final BigDecimal expectedAccountant = BigDecimal.valueOf(4);
	final BigDecimal expectedDentalShop = BigDecimal.valueOf(4);
	final BigDecimal expectedTransport = BigDecimal.valueOf(4);
	final BigDecimal expectedFood = BigDecimal.valueOf(4);
	final BigDecimal expectedEducation = null;
	final BigDecimal expectedOtherFixedCosts = BigDecimal
		.valueOf(9);
	final var expectedUser = Fixture.Users.abella();
	final var expectedUserId = expectedUser.getId();

	final var expectedErrorMessage1 = "'education' should not be null";
	final var expectedErrorMessage2 = "'education' should be a number";
	final var expectedErrorCount = 2;

	final var aCommand = CreateFixedTaxCommand.with(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUserId);

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
	Mockito.verify(fixedTaxGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidCommandWithNullFixedCosts_whenCallsCreateFixedTax_shouldReturnFixedTaxId() {
	// given
	final BigDecimal expectedRegionalCouncil = BigDecimal
		.valueOf(1);
	final BigDecimal expectedTaxOverWork = BigDecimal.valueOf(2);
	final BigDecimal expectedIncomeTax = BigDecimal.valueOf(3);
	final BigDecimal expectedAccountant = BigDecimal.valueOf(4);
	final BigDecimal expectedDentalShop = BigDecimal.valueOf(4);
	final BigDecimal expectedTransport = BigDecimal.valueOf(4);
	final BigDecimal expectedFood = BigDecimal.valueOf(4);
	final BigDecimal expectedEducation = BigDecimal.valueOf(4);
	final BigDecimal expectedOtherFixedCosts = null;
	final var expectedUser = Fixture.Users.abella();
	final var expectedUserId = expectedUser.getId();

	final var expectedErrorMessage1 = "'otherFixedCosts' should not be null";
	final var expectedErrorMessage2 = "'otherFixedCosts' should be a number";
	final var expectedErrorCount = 2;

	final var aCommand = CreateFixedTaxCommand.with(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUserId);

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

	Mockito.verify(fixedTaxGateway, times(0)).create(any());
    }

    @Test
    void givenAValidCommandWithNonExistantUserId_whenCallsCreateFixedTax_shouldReturnException() {
	// given
	final BigDecimal expectedRegionalCouncil = BigDecimal
		.valueOf(1);
	final BigDecimal expectedTaxOverWork = BigDecimal.valueOf(2);
	final BigDecimal expectedIncomeTax = BigDecimal.valueOf(3);
	final BigDecimal expectedAccountant = BigDecimal.valueOf(4);
	final BigDecimal expectedDentalShop = BigDecimal.valueOf(4);
	final BigDecimal expectedTransport = BigDecimal.valueOf(4);
	final BigDecimal expectedFood = BigDecimal.valueOf(4);
	final BigDecimal expectedEducation = BigDecimal.valueOf(4);
	final BigDecimal expectedOtherFixedCosts = BigDecimal
		.valueOf(4);
	final var invalidUser = User.newUser(
		UserID.from("INVALID_USER"), "Mia", "MiaMiaMia",
		true);
	final var expectedUserId = invalidUser.getId();

	final var expectedErrorMessage = "User could not be found with id: INVALID_USER";
	final var expectedErrorCount = 1;

	final var aCommand = CreateFixedTaxCommand.with(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUserId);

	when(userGateway.findById(any()))
		.thenReturn(Optional.empty());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(fixedTaxGateway, times(0)).create(any());
    }
}
