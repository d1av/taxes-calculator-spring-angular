package com.taxes.calculator.application.fixedtax.retrieve.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.fixedtax.FixedTaxID;
import com.taxes.calculator.domain.user.UserGateway;

class GetFixedTaxByIdUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetFixedTaxByIdUseCase useCase;

    @Mock
    private FixedTaxGateway fixedTaxGateway;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(fixedTaxGateway, userGateway);
    }

    @Test
    void givenAValidId_whenCallsGetFixedTax_shouldReturnFixedTax() {
	// given

	final var expectedRegionalCouncil = BigDecimal.valueOf(40);
	final var expectedTaxOverWork = BigDecimal.valueOf(2);
	final var expectedIncomeTax = BigDecimal.valueOf(3);
	final var expectedAccountant = BigDecimal.valueOf(4);
	final var expectedDentalShop = BigDecimal.valueOf(5);
	final var expectedTransport = BigDecimal.valueOf(6);
	final var expectedFood = BigDecimal.valueOf(7);
	final var expectedEducation = BigDecimal.valueOf(8);
	final var expectedOtherFixedCosts = BigDecimal.valueOf(9);
	final var expectedUser = Fixture.Users.asa();

	final var aFixedTax = FixedTax.newFixedTax(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts);

	aFixedTax.addUser(expectedUser);

	final var anId = aFixedTax.getId();

	when(fixedTaxGateway.findById(eq(anId)))
		.thenReturn(Optional.of(aFixedTax));
	// when
	final var actualOutput = useCase.execute(anId.getValue());

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
		actualOutput.userId());
    }

    @Test
    void givenAValidId_whenCallsGetFixedTaxAndDoesNotExists_shouldReturnNotFound() {
	// given
	final var expectedRegionalCouncil = BigDecimal.valueOf(40);
	final var expectedTaxOverWork = BigDecimal.valueOf(2);
	final var expectedIncomeTax = BigDecimal.valueOf(3);
	final var expectedAccountant = BigDecimal.valueOf(4);
	final var expectedDentalShop = BigDecimal.valueOf(5);
	final var expectedTransport = BigDecimal.valueOf(6);
	final var expectedFood = BigDecimal.valueOf(7);
	final var expectedEducation = BigDecimal.valueOf(8);
	final var expectedOtherFixedCosts = BigDecimal.valueOf(9);
	final var expectedUser = Fixture.Users.asa();

	final var aFixedTax = FixedTax.newFixedTax(
		expectedRegionalCouncil, expectedTaxOverWork,
		expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts);
	aFixedTax.addUser(expectedUser);

	final var expectedErrorMessage = "VariableTax with ID INVALID_ID was not found";
	final var expectedErrorCount = 1;

	final var anId = FixedTaxID.from("INVALID_ID");

	when(fixedTaxGateway.findById(eq(anId)))
		.thenReturn(Optional.empty());
	// when
	final var actualException = Assertions.assertThrows(
		NotFoundException.class,
		() -> useCase.execute(anId.getValue()));

	// then
	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	verify(fixedTaxGateway, times(1)).findById(eq(anId));
    }

}
