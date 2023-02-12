package com.taxes.calculator.domain.variabletax;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.user.User;

class VariableTaxTest {

    @Test
    public void givenValidParameters_whenCreateVariableTax_shouldReturnVariableTax() {
	// given
	final var expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();

	// when
	final var actualVariableTax = VariableTax.with(expectedDentalShop,
		expectedProsthetist, expectedTravel, expectedCreditCard,
		expectedWeekend, expectedUser);
	// then
	Assertions.assertNotNull(actualVariableTax);
	Assertions.assertNotNull(actualVariableTax.getId());
	Assertions.assertEquals(expectedDentalShop,
		actualVariableTax.getDentalShop());
	Assertions.assertEquals(expectedTravel, actualVariableTax.getTravel());
	Assertions.assertEquals(expectedCreditCard,
		actualVariableTax.getCreditCard());
	Assertions.assertEquals(expectedWeekend,
		actualVariableTax.getWeekend());
	Assertions.assertEquals(expectedUser.getName(),
		actualVariableTax.getUser().getName());
	Assertions.assertNotNull(actualVariableTax.getCreatedAt());
	Assertions.assertNotNull(actualVariableTax.getUpdatedAt());
    }

    @Test
    public void givenValidParametersWithNullUser_whenCreateVariableTax_shouldReturnVariableTax() {
	// given
	final var expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = null;

	// when
	final var actualVariableTax = VariableTax.with(expectedDentalShop,
		expectedProsthetist, expectedTravel, expectedCreditCard,
		expectedWeekend, expectedUser);
	// then
	Assertions.assertNotNull(actualVariableTax);
	Assertions.assertNotNull(actualVariableTax.getId());
	Assertions.assertEquals(expectedDentalShop,
		actualVariableTax.getDentalShop());
	Assertions.assertEquals(expectedTravel, actualVariableTax.getTravel());
	Assertions.assertEquals(expectedCreditCard,
		actualVariableTax.getCreditCard());
	Assertions.assertEquals(expectedWeekend,
		actualVariableTax.getWeekend());
	Assertions.assertEquals(expectedUser, actualVariableTax.getUser());
	Assertions.assertNotNull(actualVariableTax.getCreatedAt());
	Assertions.assertNotNull(actualVariableTax.getUpdatedAt());
    }

    @Test
    public void givenInvalidNullDentalShop_whenCreateVariableTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedDentalShop = null;
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'dentalShop' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> VariableTax.with(expectedDentalShop, expectedProsthetist,
			expectedTravel, expectedCreditCard, expectedWeekend,
			expectedUser));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNullProsthetist_whenCreateVariableTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = null;
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'prosthetist' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> VariableTax.with(expectedDentalShop, expectedProsthetist,
			expectedTravel, expectedCreditCard, expectedWeekend,
			expectedUser));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNullTravel_whenCreateVariableTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = null;
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'travel' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> VariableTax.with(expectedDentalShop, expectedProsthetist,
			expectedTravel, expectedCreditCard, expectedWeekend,
			expectedUser));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNullCreditCard_whenCreateVariableTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = null;
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'creditCard' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> VariableTax.with(expectedDentalShop, expectedProsthetist,
			expectedTravel, expectedCreditCard, expectedWeekend,
			expectedUser));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNullWeekend_whenCreateVariableTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = null;
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'weekend' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> VariableTax.with(expectedDentalShop, expectedProsthetist,
			expectedTravel, expectedCreditCard, expectedWeekend,
			expectedUser));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }
    
    
    @Test
    public void givenValidParameters_whenUpdateVariableTax_shouldReturnVariableTax() {
	// given
	final var expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();

	// when
	final var actualVariableTax = VariableTax.with(expectedDentalShop,
		expectedProsthetist, expectedTravel, expectedCreditCard,
		expectedWeekend, expectedUser);
	// then
	Assertions.assertNotNull(actualVariableTax);
	Assertions.assertNotNull(actualVariableTax.getId());
	Assertions.assertEquals(expectedDentalShop,
		actualVariableTax.getDentalShop());
	Assertions.assertEquals(expectedTravel, actualVariableTax.getTravel());
	Assertions.assertEquals(expectedCreditCard,
		actualVariableTax.getCreditCard());
	Assertions.assertEquals(expectedWeekend,
		actualVariableTax.getWeekend());
	Assertions.assertEquals(expectedUser.getName(),
		actualVariableTax.getUser().getName());
	Assertions.assertNotNull(actualVariableTax.getCreatedAt());
	Assertions.assertNotNull(actualVariableTax.getUpdatedAt());
    }

}
