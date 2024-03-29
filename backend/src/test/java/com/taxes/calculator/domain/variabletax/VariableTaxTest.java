package com.taxes.calculator.domain.variabletax;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserID;

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
	final UserID expectedUserId = expectedUser.getId();

	// when
	final var actualVariableTax = VariableTax.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUserId);
	// then
	Assertions.assertNotNull(actualVariableTax);
	Assertions.assertNotNull(actualVariableTax.getId());
	Assertions.assertEquals(expectedDentalShop,
		actualVariableTax.getDentalShop());
	Assertions.assertEquals(expectedTravel,
		actualVariableTax.getTravel());
	Assertions.assertEquals(expectedCreditCard,
		actualVariableTax.getCreditCard());
	Assertions.assertEquals(expectedWeekend,
		actualVariableTax.getWeekend());
	Assertions.assertEquals(expectedUser.getId().getValue(),
		actualVariableTax.getUserId().getValue());
	Assertions.assertNotNull(actualVariableTax.getCreatedAt());
	Assertions.assertNotNull(actualVariableTax.getUpdatedAt());
    }

    @Test
    public void givenValidParametersWithValidUser_whenCreateVariableTax_shouldReturnVariableTax() {
	// given
	final var expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final UserID expectedUser = Fixture.Tax.variable()
		.getUserId();

	// when
	final var actualVariableTax = VariableTax.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUser);
	// then
	Assertions.assertNotNull(actualVariableTax);
	Assertions.assertNotNull(actualVariableTax.getId());
	Assertions.assertEquals(expectedDentalShop,
		actualVariableTax.getDentalShop());
	Assertions.assertEquals(expectedTravel,
		actualVariableTax.getTravel());
	Assertions.assertEquals(expectedCreditCard,
		actualVariableTax.getCreditCard());
	Assertions.assertEquals(expectedWeekend,
		actualVariableTax.getWeekend());
	Assertions.assertEquals(expectedUser,
		actualVariableTax.getUserId());
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
	final UserID expectedUserId = expectedUser.getId();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'dentalShop' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> VariableTax.with(expectedDentalShop,
			expectedProsthetist, expectedTravel,
			expectedCreditCard, expectedWeekend,
			expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNullUser_whenCreateVariableTax_shouldReturnNotification() {
	// given
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final UserID expectedUserId = null;

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'userId' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> VariableTax.with(expectedDentalShop,
			expectedProsthetist, expectedTravel,
			expectedCreditCard, expectedWeekend,
			expectedUserId));
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
	final UserID expectedUserId = expectedUser.getId();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'prosthetist' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> VariableTax.with(expectedDentalShop,
			expectedProsthetist, expectedTravel,
			expectedCreditCard, expectedWeekend,
			expectedUserId));
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
	final UserID expectedUserId = expectedUser.getId();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'travel' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> VariableTax.with(expectedDentalShop,
			expectedProsthetist, expectedTravel,
			expectedCreditCard, expectedWeekend,
			expectedUserId));
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
	final UserID expectedUserId = expectedUser.getId();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'creditCard' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> VariableTax.with(expectedDentalShop,
			expectedProsthetist, expectedTravel,
			expectedCreditCard, expectedWeekend,
			expectedUserId));
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
	final UserID expectedUserId = expectedUser.getId();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'weekend' should not be null";

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> VariableTax.with(expectedDentalShop,
			expectedProsthetist, expectedTravel,
			expectedCreditCard, expectedWeekend,
			expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenValidParameters_whenUpdateVariableTax_shouldReturnVariableTax()
	    throws InterruptedException {
	// given
	final var aTax = Fixture.Tax.variable();

	final var expectedUpdatedAt = aTax.getUpdatedAt();
	final var expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	// when
	Thread.sleep(100);
	final var actualVariableTax = aTax.update(expectedDentalShop,
		expectedProsthetist, expectedTravel,
		expectedCreditCard, expectedWeekend)
		.addUser(expectedUserId);
	// then
	Assertions.assertEquals(aTax.getId(),
		actualVariableTax.getId());
	Assertions.assertNotNull(actualVariableTax);
	Assertions.assertNotNull(actualVariableTax.getId());
	Assertions.assertEquals(expectedDentalShop,
		actualVariableTax.getDentalShop());
	Assertions.assertEquals(expectedTravel,
		actualVariableTax.getTravel());
	Assertions.assertEquals(expectedCreditCard,
		actualVariableTax.getCreditCard());
	Assertions.assertEquals(expectedWeekend,
		actualVariableTax.getWeekend());
	Assertions.assertEquals(aTax.getUserId().getValue(),
		actualVariableTax.getUserId().getValue());
	Assertions.assertEquals(aTax.getCreatedAt(),
		actualVariableTax.getCreatedAt());
	Assertions.assertTrue(expectedUpdatedAt
		.isBefore(actualVariableTax.getUpdatedAt()));
    }

    @Test
    public void givenInvalidNullDentalShop_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = null;
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'dentalShop' should not be null";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNullProsthetist_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = null;
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'prosthetist' should not be null";
	final UserID expectedUserId = expectedUser.getId();

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNullUser_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'userId' should not be null";
	final String expectedUserId = null;

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> VariableTax.create(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend,
				expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenInvalidNullTravel_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = null;
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'travel' should not be null";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNullCreditCard_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = null;
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'creditCard' should not be null";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNullWeekend_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = null;
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	// when
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'weekend' should not be null";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNegativeWeekend_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture
		.negativeBigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	// when
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'weekend' should not be negative";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNegativeDentalShop_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture
		.negativeBigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	// when
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'dentalShop' should not be negative";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNegativeProsthetist_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture
		.negativeBigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	// when
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'prosthetist' should not be negative";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNegativeTravel_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture
		.negativeBigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	// when
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'travel' should not be negative";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalidNegativeCreditCard_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture
		.negativeBigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	// when
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'creditCard' should not be negative";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalid7DigitDentalShop_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(7);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	// when
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'dentalShop' should not be above 999.999";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalid7DigitProsthetist_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(7);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	// when
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'prosthetist' should not be above 999.999";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenInvalid7DigitTravel_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(7);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	// when
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'travel' should not be above 999.999";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenInvalid7DigitCreditCard_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(7);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	// when
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'creditCard' should not be above 999.999";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenInvalid7DigitWeekend_whenUpdateVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(7);
	final User expectedUser = Fixture.Users.asa();
	final UserID expectedUserId = expectedUser.getId();

	// when
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'weekend' should not be above 999.999";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aTax.update(expectedDentalShop,
				expectedProsthetist, expectedTravel,
				expectedCreditCard, expectedWeekend)
				.addUser(expectedUserId));
	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

}
