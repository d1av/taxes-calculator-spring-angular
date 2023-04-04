package com.taxes.calculator.infrastructure.application.fixedtax.create;

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

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.fixedtax.create.CreateFixedTaxCommand;
import com.taxes.calculator.application.fixedtax.create.CreateFixedTaxUseCase;
import com.taxes.calculator.application.variabletax.create.CreateVariableTaxCommand;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.infrastructure.IntegrationTest;
import com.taxes.calculator.infrastructure.fixedtax.persistence.FixedTaxRepository;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;

@IntegrationTest
class CreateCaseFixedTaxUseCaseIT {

    @Autowired
    private CreateFixedTaxUseCase useCase;

    @Autowired
    private FixedTaxRepository repository;

    @Autowired
    private UserRepository userRepository;

    @SpyBean
    private FixedTaxGateway gateway;

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

	final var aCommand = CreateFixedTaxCommand.with(
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

	assertEquals(0, expectedRegionalCouncil.compareTo(savedEntity.getRegionalCouncil()));
	assertEquals(0, expectedTaxOverWork.compareTo(savedEntity.getTaxOverWork()));
	assertEquals(0, expectedIncomeTax.compareTo(savedEntity.getIncomeTax()));
	assertEquals(0, expectedAccountant.compareTo(savedEntity.getAccountant()));
	assertEquals(0, expectedDentalShop.compareTo(savedEntity.getDentalShop()));
	assertEquals(0, expectedTransport.compareTo(savedEntity.getTransport()));
	assertEquals(0, expectedFood.compareTo(savedEntity.getFood()));
	assertEquals(0, expectedEducation.compareTo(savedEntity.getEducation()));
	assertEquals(0, expectedOtherFixedCosts.compareTo(savedEntity.getOtherFixedCosts()));
	assertNotNull(savedEntity.getUpdatedAt());

	Mockito.verify(gateway).create(any());
    }

}
