package com.taxes.calculator.domain.hourvalue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.domain.exceptions.NotificationException;

class HourValueTest {

    @Test
    void givenAValidHourValue_whenCallsCreateHourValue_shouldReturnHourValue() {
	// given
	final var expectedSalary = Fixture.bigDecimal(4);
	final var expectedHourValue = Fixture.bigDecimal(4);
	final var expectedDaysOfWork = Fixture.daysOfWork(); // 1- 31

	// when
	final var actualHour = HourValue.newHourValue(expectedSalary,
		expectedHourValue, expectedDaysOfWork);

	// then
	Assertions.assertNotNull(actualHour);
	Assertions.assertNotNull(actualHour.getId());
	Assertions.assertNotNull(actualHour.getExpectedSalary());
	Assertions.assertNotNull(actualHour.getPersonalHourValue());
	Assertions.assertNotNull(actualHour.getDaysOfWork());
	Assertions.assertNotNull(actualHour.getCreatedAt());
	Assertions.assertNotNull(actualHour.getUpdatedAt());
    }

    @Test
    void givenANegativeHourValue_whenCallsCreateHourValue_shouldReturnNotification() {
	// given
	final var expectedSalary = Fixture.bigDecimal(4);
	final var expectedHourValue = Fixture.negativeBigDecimal(4);
	final var expectedDaysOfWork = Fixture.daysOfWork(); // 1- 31

	final var expectedErrorMessage = "'hourValue' should not be negative";

	// when

	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> HourValue.newHourValue(expectedSalary,
				expectedHourValue,
				expectedDaysOfWork));

	// then
	Assertions.assertEquals(expectedErrorMessage,
		actualException.getErrors().get(0).message());
    }
    
    @Test
    void givenANegativeSalary_whenCallsCreateHourValue_shouldReturnNotification() {
	// given
	final var expectedSalary = Fixture.negativeBigDecimal(4);
	final var expectedHourValue = Fixture.bigDecimal(4);
	final var expectedDaysOfWork = Fixture.daysOfWork(); // 1- 31
	
	final var expectedErrorMessage = "'expectedSalary' should not be negative";
	
	// when
	
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> HourValue.newHourValue(expectedSalary,
				expectedHourValue,
				expectedDaysOfWork));
	
	// then
	Assertions.assertEquals(expectedErrorMessage,
		actualException.getErrors().get(0).message());
    }

    @Test
    void givenAInvalidSalary_whenCallsCreateHourValue_shouldReturnNotification() {
	// given
	final BigDecimal expectedSalary = null;
	final var expectedHourValue = Fixture.bigDecimal(4);
	final var expectedDaysOfWork = Fixture.daysOfWork();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'expectedSalary' should not be null";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> HourValue.newHourValue(expectedSalary,
				expectedHourValue,
				expectedDaysOfWork));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalid7DigitSalary_whenCallsCreateHourValue_shouldReturnNotification() {
	// given
	final BigDecimal expectedSalary = Fixture.bigDecimal(7);
	final var expectedHourValue = Fixture.bigDecimal(4);
	final var expectedDaysOfWork = Fixture.daysOfWork();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'expectedSalary' should not be above 999.999";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> HourValue.newHourValue(expectedSalary,
				expectedHourValue,
				expectedDaysOfWork));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalidHourValue_whenCallsCreateHourValue_shouldReturnNotification() {
	// given
	final var expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedHourValue = null;
	final var expectedDaysOfWork = Fixture.daysOfWork();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'hourValue' should not be null";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> HourValue.newHourValue(expectedSalary,
				expectedHourValue,
				expectedDaysOfWork));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalidDaysOfWork_whenCallsCreateHourValue_shouldReturnNotification() {
	// given
	final var expectedSalary = Fixture.bigDecimal(4);
	final var expectedHourValue = Fixture.bigDecimal(4);
	final Integer expectedDaysOfWork = null;

	final var expectedErrorCount = 2;
	final var expectedErrorMessage = "'daysOfWork' should not be null";
	final var expectedErrorMessage2 = "'daysOfWork' should be a number between 1 and 31";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> HourValue.newHourValue(expectedSalary,
				expectedHourValue,
				expectedDaysOfWork));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
    }

    @Test
    void givenAValidHourValue_whenCallsUpdateHourValue_shouldReturnHourValue()
	    throws InterruptedException {
	// given
	final var aHourValue = Fixture.HourValues.valid();

	final var expectedId = aHourValue.getId();
	final var expectedCreatedAt = aHourValue.getCreatedAt();
	final var expectedUpdatedAt = aHourValue.getUpdatedAt();
	final var expectedSalary = Fixture.bigDecimal(4);
	final var expectedHourValue = Fixture.bigDecimal(4);
	final var expectedDaysOfWork = Fixture.daysOfWork(); // 1- 31

	// when
	Thread.sleep(10);
	final var updatedHour = aHourValue.update(expectedSalary,
		expectedHourValue, expectedDaysOfWork);

	// then
	Assertions.assertEquals(expectedId, updatedHour.getId());
	Assertions.assertEquals(expectedSalary,
		updatedHour.getExpectedSalary());
	Assertions.assertEquals(expectedHourValue,
		updatedHour.getPersonalHourValue());
	Assertions.assertEquals(expectedDaysOfWork,
		updatedHour.getDaysOfWork());
	Assertions.assertEquals(expectedCreatedAt,
		updatedHour.getCreatedAt());
	Assertions.assertTrue(expectedUpdatedAt
		.isBefore(updatedHour.getUpdatedAt()));
    }

    @Test
    void givenAInvalidSalary_whenCallsUpdateHourValue_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();

	final BigDecimal expectedSalary = null;
	final var expectedHourValue = Fixture.bigDecimal(4);
	final var expectedDaysOfWork = Fixture.daysOfWork(); // 1- 31

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'expectedSalary' should not be null";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aHourValue.update(expectedSalary,
				expectedHourValue,
				expectedDaysOfWork));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalidHourValue_whenCallsUpdateHourValue_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();

	final var expectedSalary = Fixture.bigDecimal(4);
	final BigDecimal expectedHourValue = null;
	final var expectedDaysOfWork = Fixture.daysOfWork(); // 1- 31

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'hourValue' should not be null";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aHourValue.update(expectedSalary,
				expectedHourValue,
				expectedDaysOfWork));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenAInvalidDaysOfWork_whenCallsUpdateHourValue_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();

	final var expectedSalary = Fixture.bigDecimal(4);
	final var expectedHourValue = Fixture.bigDecimal(4);
	final var expectedDaysOfWork = 32; // 1- 31

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'daysOfWork' should be a number between 1 and 31";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aHourValue.update(expectedSalary,
				expectedHourValue,
				expectedDaysOfWork));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenA7DigitSalary_whenCallsUpdateHourValue_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();

	final BigDecimal expectedSalary = Fixture.bigDecimal(7);
	final var expectedHourValue = Fixture.bigDecimal(4);
	final var expectedDaysOfWork = Fixture.daysOfWork(); // 1- 31

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'expectedSalary' should not be above 999.999";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aHourValue.update(expectedSalary,
				expectedHourValue,
				expectedDaysOfWork));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenA7DigitHourValue_whenCallsUpdateHourValue_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();

	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final var expectedHourValue = Fixture.bigDecimal(7);
	final var expectedDaysOfWork = Fixture.daysOfWork(); // 1- 31

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'hourValue' should not be above 999.999";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aHourValue.update(expectedSalary,
				expectedHourValue,
				expectedDaysOfWork));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenANegativeDigitHourValue_whenCallsUpdateHourValue_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();

	final BigDecimal expectedSalary = Fixture.bigDecimal(4);
	final var expectedHourValue = Fixture.negativeBigDecimal(1);
	final var expectedDaysOfWork = Fixture.daysOfWork(); // 1- 31

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'hourValue' should not be negative";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aHourValue.update(expectedSalary,
				expectedHourValue,
				expectedDaysOfWork));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenANegativeDigitSalary_whenCallsUpdateHourValue_shouldReturnNotification() {
	// given
	final var aHourValue = Fixture.HourValues.valid();

	final BigDecimal expectedSalary = Fixture
		.negativeBigDecimal(4);
	final var expectedHourValue = Fixture.bigDecimal(4);
	final var expectedDaysOfWork = Fixture.daysOfWork(); // 1- 31

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'expectedSalary' should not be negative";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> aHourValue.update(expectedSalary,
				expectedHourValue,
				expectedDaysOfWork));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }
}
