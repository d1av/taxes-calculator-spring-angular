package com.taxes.calculator.infrastructure.application.fixedtax.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.fixedtax.update.UpdateFixedTaxCommand;
import com.taxes.calculator.application.fixedtax.update.UpdateFixedTaxUseCase;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.infrastructure.IntegrationTest;
import com.taxes.calculator.infrastructure.fixedtax.persistence.FixedTaxJpaEntity;
import com.taxes.calculator.infrastructure.fixedtax.persistence.FixedTaxRepository;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;

@IntegrationTest
class UpdateCaseFixedTaxUseCaseIT {

    @Autowired
    private UpdateFixedTaxUseCase useCase;

    @Autowired
    private FixedTaxRepository repository;

    @Autowired
    private UserRepository userRepository;

    @SpyBean
    private FixedTaxGateway gateway;

    private static String ID_SAVED;

    @BeforeEach
    void mockUp() {
	final var fixedTax = repository.saveAndFlush(
		FixedTaxJpaEntity.from(Fixture.Tax.fixed()));
	ID_SAVED = fixedTax.getId();
    }

    @Test
    void givenAValidCommand_whenFixedTax_shouldReturnIt() {
	// given

	final BigDecimal expectedRegionalCouncil = Fixture
		.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture
		.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = UpdateFixedTaxCommand.with(ID_SAVED,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser.getId());

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then
	assertNotNull(actualOutput);

	final var savedEntity = this.repository
		.findById(actualOutput.id()).get();

	assertEquals(0, expectedRegionalCouncil
		.compareTo(savedEntity.getRegionalCouncil()));
	assertEquals(0, expectedTaxOverWork
		.compareTo(savedEntity.getTaxOverWork()));
	assertEquals(0, expectedIncomeTax
		.compareTo(savedEntity.getIncomeTax()));
	assertEquals(0, expectedAccountant
		.compareTo(savedEntity.getAccountant()));
	assertEquals(0, expectedDentalShop
		.compareTo(savedEntity.getDentalShop()));
	assertEquals(0, expectedTransport
		.compareTo(savedEntity.getTransport()));
	assertEquals(0,
		expectedFood.compareTo(savedEntity.getFood()));
	assertEquals(0, expectedEducation
		.compareTo(savedEntity.getEducation()));
	assertEquals(0, expectedOtherFixedCosts
		.compareTo(savedEntity.getOtherFixedCosts()));
	assertNotNull(savedEntity.getUpdatedAt());

	Mockito.verify(gateway).update(any());
    }

    @Test
    void givenAInvalidCommandRegional_whenFixedTax_shouldReturnNotification() {
	// given

	final BigDecimal expectedRegionalCouncil = null;
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture
		.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();

	final var expectedErrorMessage1 = "'regionalCouncil' should not be null";
	final var expectedErrorMessage2 = "'regionalCouncil' should be a number";
	final var expectedErrorSize = 2;

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = UpdateFixedTaxCommand.with(ID_SAVED,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser.getId());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	assertNotNull(actualException);
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
	Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandTaxOverWork_whenFixedTax_shouldReturnNotification() {
	// given

	final BigDecimal expectedRegionalCouncil = Fixture
		.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = null;
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture
		.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();

	final var expectedErrorMessage1 = "'taxOverWork' should not be null";
	final var expectedErrorMessage2 = "'taxOverWork' should be a number";
	final var expectedErrorSize = 2;

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = UpdateFixedTaxCommand.with(ID_SAVED,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser.getId());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	assertNotNull(actualException);
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
	Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandIncomeTax_whenFixedTax_shouldReturnNotification() {
	// given

	final BigDecimal expectedRegionalCouncil = Fixture
		.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = null;
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture
		.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();

	final var expectedErrorMessage1 = "'incomeTax' should not be null";
	final var expectedErrorMessage2 = "'incomeTax' should be a number";
	final var expectedErrorSize = 2;

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = UpdateFixedTaxCommand.with(ID_SAVED,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser.getId());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	assertNotNull(actualException);
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
	Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandAccountant_whenFixedTax_shouldReturnNotification() {
	// given

	final BigDecimal expectedRegionalCouncil = Fixture
		.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = null;
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture
		.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();

	final var expectedErrorMessage1 = "'accountant' should not be null";
	final var expectedErrorMessage2 = "'accountant' should be a number";
	final var expectedErrorSize = 2;

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = UpdateFixedTaxCommand.with(ID_SAVED,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser.getId());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	assertNotNull(actualException);
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
	Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandDentalShop_whenFixedTax_shouldReturnNotification() {
	// given

	final BigDecimal expectedRegionalCouncil = Fixture
		.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = null;
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture
		.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();

	final var expectedErrorMessage1 = "'dentalShop' should not be null";
	final var expectedErrorMessage2 = "'dentalShop' should be a number";
	final var expectedErrorSize = 2;

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = UpdateFixedTaxCommand.with(ID_SAVED,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser.getId());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	assertNotNull(actualException);
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
	Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandTransport_whenFixedTax_shouldReturnNotification() {
	// given

	final BigDecimal expectedRegionalCouncil = Fixture
		.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = null;
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture
		.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();

	final var expectedErrorMessage1 = "'transport' should not be null";
	final var expectedErrorMessage2 = "'transport' should be a number";
	final var expectedErrorSize = 2;

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = UpdateFixedTaxCommand.with(ID_SAVED,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser.getId());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	assertNotNull(actualException);
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
	Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandFood_whenFixedTax_shouldReturnNotification() {
	// given

	final BigDecimal expectedRegionalCouncil = Fixture
		.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = null;
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture
		.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();

	final var expectedErrorMessage1 = "'food' should not be null";
	final var expectedErrorMessage2 = "'food' should be a number";
	final var expectedErrorSize = 2;

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = UpdateFixedTaxCommand.with(ID_SAVED,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser.getId());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	assertNotNull(actualException);
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
	Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandEducation_whenFixedTax_shouldReturnNotification() {
	// given

	final BigDecimal expectedRegionalCouncil = Fixture
		.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = null;
	final BigDecimal expectedOtherFixedCosts = Fixture
		.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();

	final var expectedErrorMessage1 = "'education' should not be null";
	final var expectedErrorMessage2 = "'education' should be a number";
	final var expectedErrorSize = 2;

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = UpdateFixedTaxCommand.with(ID_SAVED,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser.getId());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	assertNotNull(actualException);
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
	Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandFixedCosts_whenFixedTax_shouldReturnNotification() {
	// given

	final BigDecimal expectedRegionalCouncil = Fixture
		.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = null;
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();

	final var expectedErrorMessage1 = "'otherFixedCosts' should not be null";
	final var expectedErrorMessage2 = "'otherFixedCosts' should be a number";
	final var expectedErrorSize = 2;

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var aCommand = UpdateFixedTaxCommand.with(ID_SAVED,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser.getId());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	assertNotNull(actualException);
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
	Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidCommandUser_whenFixedTax_shouldReturnNotification() {
	// given

	final BigDecimal expectedRegionalCouncil = Fixture
		.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture
		.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();
	final String expectedUserId = expectedUser.getId().getValue();

	final var expectedErrorMessage1 = "User could not be found with id: %s"
		.formatted(expectedUserId);
	final var expectedErrorSize = 1;

	final var aCommand = UpdateFixedTaxCommand.with(ID_SAVED,
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser.getId());

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	assertNotNull(actualException);
	assertEquals(expectedErrorSize,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1,
		actualException.getErrors().get(0).message());
	Mockito.verify(gateway, times(0)).update(any());
    }

}
