package com.taxes.calculator.infrastructure.application.variabletax.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;

import javax.validation.constraints.AssertTrue;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.variabletax.create.CreateVariableTaxCommand;
import com.taxes.calculator.application.variabletax.create.CreateVariableTaxUseCase;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.infrastructure.IntegrationTest;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxRepository;

@IntegrationTest
class CreateCaseVariableTaxUseCaseIT {

    @Autowired
    private CreateVariableTaxUseCase useCase;

    @Autowired
    private VariableTaxRepository repository;

    @Autowired
    private UserRepository userRepository;

    @SpyBean
    private VariableTaxGateway gateway;

    @Test
    void givenAValidCommand_whenVariableTax_shouldReturnIt() {
	// given

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUserId);

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then
	assertNotNull(actualOutput);

	final var savedEntity = this.repository
		.findById(actualOutput.id()).get();

	assertEquals(0, expectedDentalShop
		.compareTo(savedEntity.getDentalShop()));
	assertEquals(0, expectedProsthetist
		.compareTo(savedEntity.getProsthetist()));
	assertEquals(0,
		expectedTravel.compareTo(savedEntity.getTravel()));
	assertEquals(0, expectedCreditCard
		.compareTo(savedEntity.getCreditCard()));
	assertEquals(0,
		expectedWeekend.compareTo(savedEntity.getWeekend()));
	assertNotNull(savedEntity.getCreatedAt());
	assertNotNull(savedEntity.getUpdatedAt());

	Mockito.verify(gateway).create(any());
    }

    @Test
    void givenAInvalidNullDentalShopCommand_whenVariableTaxCreate_shouldThrowNotificationException() {
	// given

	final BigDecimal expectedDentalShop = null;
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'dentalShop' should not be null";

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUserId);

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.getErrors().get(0).message());

	Mockito.verify(gateway,times(0)).create(any());
    }
    
    @Test
    void givenAInvalidNullProsthetistCommand_whenVariableTaxCreate_shouldThrowNotificationException() {
	// given
	
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = null;
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();
	
	final var expectedErrorCount = 1;
	final var expectedErrorMessage1 = "'prosthetist' should not be null";
	
	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));
	
	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUserId);
	
	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	
	// then
	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());

	
	Mockito.verify(gateway,times(0)).create(any());
    }
    @Test
    void givenAInvalidNullTravelCommand_whenVariableTaxCreate_shouldThrowNotificationException() {
	// given
	
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = null;
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();
	
	final var expectedErrorCount = 1;
	final var expectedErrorMessage1 = "'travel' should not be null";
	
	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));
	
	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUserId);
	
	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	
	// then
	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());
	
	Mockito.verify(gateway,times(0)).create(any());
    }
    @Test
    void givenAInvalidNullCreaditCardCommand_whenVariableTaxCreate_shouldThrowNotificationException() {
	// given
	
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = null;
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();
	
	final var expectedErrorCount = 1;
	final var expectedErrorMessage1 = "'creditCard' should not be null";

	
	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));
	
	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUserId);
	
	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	
	// then
	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());

	
	Mockito.verify(gateway,times(0)).create(any());
    }
    
    @Test
    void givenAInvalidNullWeekendCommand_whenVariableTaxCreate_shouldThrowNotificationException() {
	// given
	
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = null;
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();
	
	final var expectedErrorCount = 1;
	final var expectedErrorMessage1 = "'weekend' should not be null";
	
	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));
	
	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUserId);
	
	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	
	// then
	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());
	
	Mockito.verify(gateway,times(0)).create(any());
    }
    
    @Test
    void givenAInvalidNullUserCommand_whenVariableTaxCreate_shouldThrowNotificationException() {
	// given
	
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = null;
	
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'userId' should not be null";
	
	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));
	
	final var aCommand = CreateVariableTaxCommand.with(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend,
		expectedUserId);
	
	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	
	// then
	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.getErrors().get(0).message());
	
	Mockito.verify(gateway,times(0)).create(any());
    }

}
