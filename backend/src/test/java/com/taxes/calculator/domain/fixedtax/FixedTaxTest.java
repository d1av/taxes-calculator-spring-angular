package com.taxes.calculator.domain.fixedtax;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserID;

class FixedTaxTest {

    @Test
    void givenAValidFixedTax_whenCreateNewFixedTax_shouldReturnFixedTax() {
	// given
	final BigDecimal expectedRegionalCouncil = Fixture.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture.bigDecimal(4);
	final UserID expectedUser = Fixture.Users.asa().getId();

	// when
	final var actualTax = FixedTax.with(expectedRegionalCouncil,
		expectedTaxOverWork, expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts, expectedUser);

	// then

	Assertions.assertNotNull(actualTax);
	Assertions.assertNotNull(actualTax.getId());
	Assertions.assertEquals(expectedRegionalCouncil,
		actualTax.getRegionalCouncil());
	Assertions.assertEquals(expectedTaxOverWork,
		actualTax.getTaxOverWork());
	Assertions.assertEquals(expectedIncomeTax, actualTax.getIncomeTax());
	Assertions.assertEquals(expectedAccountant, actualTax.getAccountant());
	Assertions.assertEquals(expectedDentalShop, actualTax.getDentalShop());
	Assertions.assertEquals(expectedTransport, actualTax.getTransport());
	Assertions.assertEquals(expectedFood, actualTax.getFood());
	Assertions.assertEquals(expectedEducation, actualTax.getEducation());
	Assertions.assertEquals(expectedOtherFixedCosts,
		actualTax.getOtherFixedCosts());
	Assertions.assertEquals(expectedUser.getValue(),
		actualTax.getUser().getValue());
	Assertions.assertNotNull(actualTax.getCreatedAt());
	Assertions.assertNotNull(actualTax.getUpdatedAt());
    }

    @Test
    void givenAValidFixedTaxWithNullUser_whenCreateNewFixedTax_shouldReturnFixedTax() {
	// given
	final BigDecimal expectedRegionalCouncil = Fixture.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture.bigDecimal(4);
	final UserID expectedUser = null;

	// when
	final var actualTax = FixedTax.with(expectedRegionalCouncil,
		expectedTaxOverWork, expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts, expectedUser);

	// then

	Assertions.assertNotNull(actualTax);
	Assertions.assertNotNull(actualTax.getId());
	Assertions.assertEquals(expectedRegionalCouncil,
		actualTax.getRegionalCouncil());
	Assertions.assertEquals(expectedTaxOverWork,
		actualTax.getTaxOverWork());
	Assertions.assertEquals(expectedIncomeTax, actualTax.getIncomeTax());
	Assertions.assertEquals(expectedAccountant, actualTax.getAccountant());
	Assertions.assertEquals(expectedDentalShop, actualTax.getDentalShop());
	Assertions.assertEquals(expectedTransport, actualTax.getTransport());
	Assertions.assertEquals(expectedFood, actualTax.getFood());
	Assertions.assertEquals(expectedEducation, actualTax.getEducation());
	Assertions.assertEquals(expectedOtherFixedCosts,
		actualTax.getOtherFixedCosts());
	Assertions.assertEquals(expectedUser, actualTax.getUser());
	Assertions.assertNotNull(actualTax.getCreatedAt());
	Assertions.assertNotNull(actualTax.getUpdatedAt());
    }

    @Test
    void givenAInvalidNullRegionalCouncil_whenCreateNewFixedTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedRegionalCouncil = null;
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture.bigDecimal(4);
	final UserID expectedUser = null;

	final var expectedErrorSize = 1;
	final var expectedErrorMessage = "'regionalCouncil' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> FixedTax.with(expectedRegionalCouncil,
			expectedTaxOverWork, expectedIncomeTax,
			expectedAccountant, expectedDentalShop,
			expectedTransport, expectedFood, expectedEducation,
			expectedOtherFixedCosts, expectedUser));

	// then
	Assertions.assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalidNullTaxOverWork_whenCreateNewFixedTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedRegionalCouncil = Fixture.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = null;
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture.bigDecimal(4);
	final UserID expectedUser = null;

	final var expectedErrorSize = 1;
	final var expectedErrorMessage = "'taxOverWork' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> FixedTax.with(expectedRegionalCouncil,
			expectedTaxOverWork, expectedIncomeTax,
			expectedAccountant, expectedDentalShop,
			expectedTransport, expectedFood, expectedEducation,
			expectedOtherFixedCosts, expectedUser));

	// then
	Assertions.assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalidNullIncomeTax_whenCreateNewFixedTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedRegionalCouncil = Fixture.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = null;
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture.bigDecimal(4);
	final UserID expectedUser = null;

	final var expectedErrorSize = 1;
	final var expectedErrorMessage = "'incomeTax' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> FixedTax.with(expectedRegionalCouncil,
			expectedTaxOverWork, expectedIncomeTax,
			expectedAccountant, expectedDentalShop,
			expectedTransport, expectedFood, expectedEducation,
			expectedOtherFixedCosts, expectedUser));

	// then
	Assertions.assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalidNullAccountant_whenCreateNewFixedTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedRegionalCouncil = Fixture.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = null;
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture.bigDecimal(4);
	final UserID expectedUser = null;

	final var expectedErrorSize = 1;
	final var expectedErrorMessage = "'accountant' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> FixedTax.with(expectedRegionalCouncil,
			expectedTaxOverWork, expectedIncomeTax,
			expectedAccountant, expectedDentalShop,
			expectedTransport, expectedFood, expectedEducation,
			expectedOtherFixedCosts, expectedUser));

	// then
	Assertions.assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }
    
    @Test
    void givenAInvalidNullDentalShop_whenCreateNewFixedTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedRegionalCouncil = Fixture.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = null;
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture.bigDecimal(4);
	final UserID expectedUser = null;

	final var expectedErrorSize = 1;
	final var expectedErrorMessage = "'dentalShop' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> FixedTax.with(expectedRegionalCouncil,
			expectedTaxOverWork, expectedIncomeTax,
			expectedAccountant, expectedDentalShop,
			expectedTransport, expectedFood, expectedEducation,
			expectedOtherFixedCosts, expectedUser));

	// then
	Assertions.assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }
    
    @Test
    void givenAInvalidNullTransport_whenCreateNewFixedTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedRegionalCouncil = Fixture.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = null;
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture.bigDecimal(4);
	final UserID expectedUser = null;

	final var expectedErrorSize = 1;
	final var expectedErrorMessage = "'transport' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> FixedTax.with(expectedRegionalCouncil,
			expectedTaxOverWork, expectedIncomeTax,
			expectedAccountant, expectedDentalShop,
			expectedTransport, expectedFood, expectedEducation,
			expectedOtherFixedCosts, expectedUser));

	// then
	Assertions.assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }
    
    @Test
    void givenAInvalidNullFood_whenCreateNewFixedTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedRegionalCouncil = Fixture.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = null;
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture.bigDecimal(4);
	final UserID expectedUser = null;

	final var expectedErrorSize = 1;
	final var expectedErrorMessage = "'food' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> FixedTax.with(expectedRegionalCouncil,
			expectedTaxOverWork, expectedIncomeTax,
			expectedAccountant, expectedDentalShop,
			expectedTransport, expectedFood, expectedEducation,
			expectedOtherFixedCosts, expectedUser));

	// then
	Assertions.assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }
    
    @Test
    void givenAInvalidNullEducation_whenCreateNewFixedTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedRegionalCouncil = Fixture.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = null;
	final BigDecimal expectedOtherFixedCosts = Fixture.bigDecimal(4);
	final UserID expectedUser = null;

	final var expectedErrorSize = 1;
	final var expectedErrorMessage = "'education' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> FixedTax.with(expectedRegionalCouncil,
			expectedTaxOverWork, expectedIncomeTax,
			expectedAccountant, expectedDentalShop,
			expectedTransport, expectedFood, expectedEducation,
			expectedOtherFixedCosts, expectedUser));

	// then
	Assertions.assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }
    
    @Test
    void givenAInvalidNullOtherFixedCosts_whenCreateNewFixedTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedRegionalCouncil = Fixture.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = null;
	final UserID expectedUser = null;

	final var expectedErrorSize = 1;
	final var expectedErrorMessage = "'otherFixedCosts' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> FixedTax.with(expectedRegionalCouncil,
			expectedTaxOverWork, expectedIncomeTax,
			expectedAccountant, expectedDentalShop,
			expectedTransport, expectedFood, expectedEducation,
			expectedOtherFixedCosts, expectedUser));

	// then
	Assertions.assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }


}
