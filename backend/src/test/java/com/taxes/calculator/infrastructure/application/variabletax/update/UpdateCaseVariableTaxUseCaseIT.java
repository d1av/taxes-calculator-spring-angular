package com.taxes.calculator.infrastructure.application.variabletax.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.variabletax.create.CreateVariableTaxCommand;
import com.taxes.calculator.application.variabletax.update.UpdateVariableTaxCommand;
import com.taxes.calculator.application.variabletax.update.UpdateVariableTaxUseCase;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.infrastructure.IntegrationTest;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxJpaEntity;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxRepository;

@IntegrationTest
public class UpdateCaseVariableTaxUseCaseIT {

    @Autowired
    private UpdateVariableTaxUseCase useCase;

    @Autowired
    private VariableTaxRepository repository;

    @Autowired
    private UserRepository userRepository;

    @SpyBean
    private VariableTaxGateway gateway;

    @Test
    void givenAValidCommand_whenVariableTax_shouldReturnIt() {
	// given
	final var aTax = Fixture.Tax.variable();
	repository.saveAndFlush(VariableTaxJpaEntity.from(aTax));

	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Tax.getUser();
	final String expectedUserId = expectedUser.getId().getValue();

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = UpdateVariableTaxCommand.with(
		aTax.getId().getValue(), expectedDentalShop,
		expectedProsthetist, expectedTravel,
		expectedCreditCard, expectedWeekend, expectedUserId);

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

	Mockito.verify(gateway).update(any());
    }

    @Test
    void givenAInvalidValidCommandWithNullDentalShop_whenVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();
	repository.saveAndFlush(VariableTaxJpaEntity.from(aTax));

	final BigDecimal expectedDentalShop = null;
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Tax.getUser();
	final String expectedUserId = expectedUser.getId().getValue();

	final int expectedErrorSize = 1;
	final String expectedException = "'dentalShop' should not be null";

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = UpdateVariableTaxCommand.with(
		aTax.getId().getValue(), expectedDentalShop,
		expectedProsthetist, expectedTravel,
		expectedCreditCard, expectedWeekend, expectedUserId);

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedException,
		actualException.getErrors().get(0).message());

	Mockito.verify(gateway, times(0)).update(any());
    }
    
    @Test
    void givenAInvalidValidCommandWithNullProsthetist_whenVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();
	repository.saveAndFlush(VariableTaxJpaEntity.from(aTax));
	
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = null;
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Tax.getUser();
	final String expectedUserId = expectedUser.getId().getValue();
	
	final int expectedErrorSize = 1;
	final String expectedException = "'prosthetist' should not be null";
	
	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));
	
	final var aCommand = UpdateVariableTaxCommand.with(
		aTax.getId().getValue(), expectedDentalShop,
		expectedProsthetist, expectedTravel,
		expectedCreditCard, expectedWeekend, expectedUserId);
	
	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	
	// then
	
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedException,
		actualException.getErrors().get(0).message());
	
	Mockito.verify(gateway, times(0)).update(any());
    }
    
    @Test
    void givenAInvalidValidCommandWithNullTravel_whenVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();
	repository.saveAndFlush(VariableTaxJpaEntity.from(aTax));
	
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = null;
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Tax.getUser();
	final String expectedUserId = expectedUser.getId().getValue();
	
	final int expectedErrorSize = 1;
	final String expectedException = "'travel' should not be null";
	
	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));
	
	final var aCommand = UpdateVariableTaxCommand.with(
		aTax.getId().getValue(), expectedDentalShop,
		expectedProsthetist, expectedTravel,
		expectedCreditCard, expectedWeekend, expectedUserId);
	
	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	
	// then
	
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedException,
		actualException.getErrors().get(0).message());
	
	Mockito.verify(gateway, times(0)).update(any());
    }
    
    @Test
    void givenAInvalidValidCommandWithNullCreditCard_whenVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();
	repository.saveAndFlush(VariableTaxJpaEntity.from(aTax));
	
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = null;
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Tax.getUser();
	final String expectedUserId = expectedUser.getId().getValue();
	
	final int expectedErrorSize = 1;
	final String expectedException = "'creditCard' should not be null";
	
	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));
	
	final var aCommand = UpdateVariableTaxCommand.with(
		aTax.getId().getValue(), expectedDentalShop,
		expectedProsthetist, expectedTravel,
		expectedCreditCard, expectedWeekend, expectedUserId);
	
	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	
	// then
	
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedException,
		actualException.getErrors().get(0).message());
	
	Mockito.verify(gateway, times(0)).update(any());
    }
    
    @Test
    void givenAInvalidValidCommandWithNullWeekend_whenVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();
	repository.saveAndFlush(VariableTaxJpaEntity.from(aTax));
	
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = null;
	final User expectedUser = Fixture.Tax.getUser();
	final String expectedUserId = expectedUser.getId().getValue();
	
	final int expectedErrorSize = 1;
	final String expectedException = "'weekend' should not be null";
	
	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));
	
	final var aCommand = UpdateVariableTaxCommand.with(
		aTax.getId().getValue(), expectedDentalShop,
		expectedProsthetist, expectedTravel,
		expectedCreditCard, expectedWeekend, expectedUserId);
	
	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));
	
	// then
	
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedException,
		actualException.getErrors().get(0).message());
	
	Mockito.verify(gateway, times(0)).update(any());
    }
    
    @Test
    void givenAInvalidValidCommandWithNullUser_whenVariableTax_shouldReturnNotification() {
	// given
	final var aTax = Fixture.Tax.variable();
	repository.saveAndFlush(VariableTaxJpaEntity.from(aTax));
	
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedProsthetist = Fixture.bigDecimal(4);
	final BigDecimal expectedTravel = Fixture.bigDecimal(4);
	final BigDecimal expectedCreditCard = Fixture.bigDecimal(4);
	final BigDecimal expectedWeekend = Fixture.bigDecimal(4);
	final String expectedUserId = null;
	
	final String expectedException = "The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!";
	

	final var aCommand = UpdateVariableTaxCommand.with(
		aTax.getId().getValue(), expectedDentalShop,
		expectedProsthetist, expectedTravel,
		expectedCreditCard, expectedWeekend, expectedUserId);
	
	// when
	final var actualException = Assertions.assertThrows(
		InvalidDataAccessApiUsageException.class,
		() -> useCase.execute(aCommand));
	
	// then
	
	assertEquals(expectedException,
		actualException.getMessage());
	
	Mockito.verify(gateway, times(0)).update(any());
    }


}
