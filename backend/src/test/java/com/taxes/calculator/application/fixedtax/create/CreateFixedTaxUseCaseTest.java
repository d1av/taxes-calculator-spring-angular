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

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;

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

	final var aCommand = CreateFixedTaxCommand.with(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts,
		expectedUser);

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
		actualOutput.user().getId().getValue());

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
	final User expectedUser = null;

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
	assertEquals(expectedUser, actualOutput.user());

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
}
