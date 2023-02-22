package com.taxes.calculator.application.fixedtax.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

class UpdateFixedTaxUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultUpdateFixedTaxUseCase useCase;

    @Mock
    private FixedTaxGateway fixedTaxGateway;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(fixedTaxGateway, userGateway);
    }

    @Test
    void givenAValidCommand_whenCallsUpdateFixedTax_shouldReturnFixedTaxId() {
	// given
	final var aFixedTax = Fixture.Tax.fixed();
	final var expectedId = aFixedTax.getId().getValue();

	final var expectedRegionalCouncil = BigDecimal.valueOf(1);
	final var expectedTaxOverWork = BigDecimal.valueOf(2);
	final var expectedIncomeTax = BigDecimal.valueOf(3);
	final var expectedAccountant = BigDecimal.valueOf(4);
	final var expectedDentalShop = BigDecimal.valueOf(5);
	final var expectedTransport = BigDecimal.valueOf(6);
	final var expectedFood = BigDecimal.valueOf(7);
	final var expectedEducation = BigDecimal.valueOf(8);
	final var expectedOtherFixedCosts = BigDecimal.valueOf(9);
	final var expectedUserId = aFixedTax.getUser();
	final var expectedUser = User.newUser(expectedUserId,
		"Asa Akira", "asaakira", true);

	final var aCommand = UpdateFixedTaxCommand.with(expectedId,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUserId);

	when(fixedTaxGateway.findById(any()))
		.thenReturn(Optional.of(aFixedTax));

	when(fixedTaxGateway.update(any()))
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
		actualOutput.user().getValue());

	Mockito.verify(fixedTaxGateway, times(1))
		.update(argThat(aTax -> Objects.nonNull(aTax.getId())
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
				aTax.getUser())
			));
    }

    @Test
    void givenAValidCommandWithNullUser_whenCallsUpdateFixedTax_shouldReturnNotification() {
	// given
	final var aFixedTax = Fixture.Tax.fixed();
	final var expectedId = aFixedTax.getId().getValue();

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

	final var expectedErrorMessage = "User should not be null on update.";
	final var expectedErrorSize = 1;

	final var aCommand = UpdateFixedTaxCommand.with(expectedId,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser);

	when(fixedTaxGateway.findById(any()))
		.thenReturn(Optional.of(aFixedTax));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	Assertions.assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(fixedTaxGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandWithNullRegional_whenCallsUpdateFixedTax_shouldReturnFixedTaxId() {
	// given
	final var aFixedTax = Fixture.Tax.fixed();
	final var expectedId = aFixedTax.getId();

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

	final var expectedErrorMessage = "'regionalCouncil' should not be null";
	final var expectedErrorCount = 1;

	when(fixedTaxGateway.findById(any()))
		.thenReturn(Optional.of(aFixedTax));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	final var aCommand = UpdateFixedTaxCommand.with(
		expectedId.getValue(), expectedRegionalCouncil,
		expectedTaxOverWork, expectedIncomeTax,
		expectedAccountant, expectedDentalShop,
		expectedTransport, expectedFood, expectedEducation,
		expectedOtherFixedCosts, expectedUserId);

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(fixedTaxGateway, times(1))
		.findById(eq(expectedId));
	Mockito.verify(fixedTaxGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandWithNullTaxOverWork_whenCallsUpdateFixedTax_shouldReturnFixedTaxId() {
	// given
	final var aFixedTax = Fixture.Tax.fixed();
	final var expectedId = aFixedTax.getId().getValue();

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

	final var expectedErrorMessage = "'taxOverWork' should not be null";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateFixedTaxCommand.with(expectedId,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUserId);

	when(fixedTaxGateway.findById(any()))
		.thenReturn(Optional.of(aFixedTax));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(fixedTaxGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandWithNullIncomeTax_whenCallsUpdateFixedTax_shouldReturnFixedTaxId() {
	// given
	final var aFixedTax = Fixture.Tax.fixed();
	final var expectedId = aFixedTax.getId();

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

	final var expectedErrorMessage = "'incomeTax' should not be null";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateFixedTaxCommand.with(
		expectedId.getValue(), expectedRegionalCouncil,
		expectedTaxOverWork, expectedIncomeTax,
		expectedAccountant, expectedDentalShop,
		expectedTransport, expectedFood, expectedEducation,
		expectedOtherFixedCosts, expectedUserId);

	when(fixedTaxGateway.findById(any()))
		.thenReturn(Optional.of(aFixedTax));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedUserId));
	Mockito.verify(fixedTaxGateway, times(1))
		.findById(eq(expectedId));
	Mockito.verify(fixedTaxGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandWithNullAccountant_whenCallsUpdateFixedTax_shouldReturnFixedTaxId() {
	// given
	final var aFixedTax = Fixture.Tax.fixed();
	final var expectedId = aFixedTax.getId();

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

	final var expectedErrorMessage = "'accountant' should not be null";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateFixedTaxCommand.with(
		expectedId.getValue(), expectedRegionalCouncil,
		expectedTaxOverWork, expectedIncomeTax,
		expectedAccountant, expectedDentalShop,
		expectedTransport, expectedFood, expectedEducation,
		expectedOtherFixedCosts, expectedUserId);

	when(fixedTaxGateway.findById(any()))
		.thenReturn(Optional.of(aFixedTax));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedUser.getId()));
	Mockito.verify(fixedTaxGateway, times(1))
		.findById(eq(expectedId));
	Mockito.verify(fixedTaxGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandWithNullDentalShop_whenCallsUpdateFixedTax_shouldReturnFixedTaxId() {
	// given
	final var aFixedTax = Fixture.Tax.fixed();
	final var expectedId = aFixedTax.getId();

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

	final var expectedErrorMessage = "'dentalShop' should not be null";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateFixedTaxCommand.with(
		expectedId.getValue(), expectedRegionalCouncil,
		expectedTaxOverWork, expectedIncomeTax,
		expectedAccountant, expectedDentalShop,
		expectedTransport, expectedFood, expectedEducation,
		expectedOtherFixedCosts, expectedUserId);

	when(fixedTaxGateway.findById(any()))
		.thenReturn(Optional.of(aFixedTax));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedUser.getId()));
	Mockito.verify(fixedTaxGateway, times(1))
		.findById(eq(expectedId));
	Mockito.verify(fixedTaxGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandWithNullTransport_whenCallsUpdateFixedTax_shouldReturnFixedTaxId() {
	// given
	final var aFixedTax = Fixture.Tax.fixed();
	final var expectedId = aFixedTax.getId();

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

	final var expectedErrorMessage = "'transport' should not be null";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateFixedTaxCommand.with(
		expectedId.getValue(), expectedRegionalCouncil,
		expectedTaxOverWork, expectedIncomeTax,
		expectedAccountant, expectedDentalShop,
		expectedTransport, expectedFood, expectedEducation,
		expectedOtherFixedCosts, expectedUserId);

	when(fixedTaxGateway.findById(any()))
		.thenReturn(Optional.of(aFixedTax));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedUser.getId()));
	Mockito.verify(fixedTaxGateway, times(1))
		.findById(eq(expectedId));
	Mockito.verify(fixedTaxGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandWithNullFood_whenCallsUpdateFixedTax_shouldReturnFixedTaxId() {
	// given
	final var aFixedTax = Fixture.Tax.fixed();
	final var expectedId = aFixedTax.getId();

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

	final var expectedErrorMessage = "'food' should not be null";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateFixedTaxCommand.with(
		expectedId.getValue(), expectedRegionalCouncil,
		expectedTaxOverWork, expectedIncomeTax,
		expectedAccountant, expectedDentalShop,
		expectedTransport, expectedFood, expectedEducation,
		expectedOtherFixedCosts, expectedUserId);

	when(fixedTaxGateway.findById(any()))
		.thenReturn(Optional.of(aFixedTax));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedUser.getId()));
	Mockito.verify(fixedTaxGateway, times(1))
		.findById(eq(expectedId));
	Mockito.verify(fixedTaxGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandWithNullEducation_whenCallsUpdateFixedTax_shouldReturnFixedTaxId() {
	// given
	final var aFixedTax = Fixture.Tax.fixed();
	final var expectedId = aFixedTax.getId();

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

	final var expectedErrorMessage = "'education' should not be null";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateFixedTaxCommand.with(
		expectedId.getValue(), expectedRegionalCouncil,
		expectedTaxOverWork, expectedIncomeTax,
		expectedAccountant, expectedDentalShop,
		expectedTransport, expectedFood, expectedEducation,
		expectedOtherFixedCosts, expectedUserId);

	when(fixedTaxGateway.findById(any()))
		.thenReturn(Optional.of(aFixedTax));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedUser.getId()));
	Mockito.verify(fixedTaxGateway, times(1))
		.findById(eq(expectedId));
	Mockito.verify(fixedTaxGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandWithNullFixedCosts_whenCallsUpdateFixedTax_shouldReturnFixedTaxId() {
	// given
	final var aFixedTax = Fixture.Tax.fixed();
	final var expectedId = aFixedTax.getId();

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

	final var expectedErrorMessage = "'otherFixedCosts' should not be null";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateFixedTaxCommand.with(
		expectedId.getValue(), expectedRegionalCouncil,
		expectedTaxOverWork, expectedIncomeTax,
		expectedAccountant, expectedDentalShop,
		expectedTransport, expectedFood, expectedEducation,
		expectedOtherFixedCosts, expectedUserId);

	when(fixedTaxGateway.findById(any()))
		.thenReturn(Optional.of(aFixedTax));

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(expectedUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedUser.getId()));
	Mockito.verify(fixedTaxGateway, times(1))
		.findById(eq(expectedId));
	Mockito.verify(fixedTaxGateway, times(0)).update(any());
    }

    @Test
    void givenAValidCommandWithNonExistantUserId_whenCallsUpdateFixedTax_shouldReturnException() {
	// given
	final var aFixedTax = Fixture.Tax.fixed();
	final var expectedId = aFixedTax.getId().getValue();

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
	final var invalidUser = User
		.newUser(UserID.from("INVALID_USER"), "Mia",
			"MiaMiaMia", true)
		.getId();

	final var expectedErrorMessage = "User could not be found with id: INVALID_USER";
	final var expectedErrorCount = 1;

	final var aCommand = UpdateFixedTaxCommand.with(expectedId,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		invalidUser);

	when(fixedTaxGateway.findById(any()))
		.thenReturn(Optional.of(aFixedTax));

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

	Mockito.verify(userGateway, times(1))
		.findById(eq(invalidUser));
	Mockito.verify(fixedTaxGateway, times(1)).findById(any());
	Mockito.verify(fixedTaxGateway, times(0)).update(any());
    }
}
