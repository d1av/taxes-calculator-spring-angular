package com.taxes.calculator.application.variabletax.retrieve.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;

class GetVariableTaxByIdUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetVariableTaxByIdUseCase useCase;

    @Mock
    private VariableTaxGateway variableTaxGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(variableTaxGateway);
    }

    @Test
    void givenAValidCommand_whenCallsCreateVariableTax_shouldReturnVariableTax() {
	// given
	final var expectedDentalShop = BigDecimal.valueOf(1);
	final var expectedProsthetist = BigDecimal.valueOf(2);
	final var expectedTravel = BigDecimal.valueOf(3);
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final var expectedUser = Fixture.Users.abella();

	final var aVariableTax = VariableTax.newVariableTax(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend);
	aVariableTax.addUser(expectedUser.getId());
	final var expectedId = aVariableTax.getId().getValue();

	when(variableTaxGateway.findById(any()))
		.thenReturn(Optional.of(aVariableTax));

	// when
	final var actualOutput = useCase.execute(expectedId);

	// then

	assertEquals(expectedId, actualOutput.id());
	assertEquals(expectedDentalShop, actualOutput.dentalShop());
	assertEquals(expectedProsthetist, actualOutput.prosthetist());
	assertEquals(expectedTravel, actualOutput.travel());
	assertEquals(expectedCreditCard, actualOutput.creditCard());
	assertEquals(expectedWeekend, actualOutput.weekend());
	assertEquals(expectedUser.getId().getValue(),
		actualOutput.userId());
    }

    @Test
    void givenAValidCommand_whenCallsCreateVariableTaxWithNullUser_shouldReturnVariableTax() {
	// given
	final var expectedDentalShop = BigDecimal.valueOf(1);
	final var expectedProsthetist = BigDecimal.valueOf(2);
	final var expectedTravel = BigDecimal.valueOf(3);
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final User expectedUser = null;

	final var aVariableTax = VariableTax.newVariableTax(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend);

	final var expectedId = aVariableTax.getId().getValue();

	when(variableTaxGateway.findById(any()))
		.thenReturn(Optional.of(aVariableTax));

	// when
	final var actualOutput = useCase.execute(expectedId);

	// then

	assertEquals(expectedId, actualOutput.id());
	assertEquals(expectedDentalShop, actualOutput.dentalShop());
	assertEquals(expectedProsthetist, actualOutput.prosthetist());
	assertEquals(expectedTravel, actualOutput.travel());
	assertEquals(expectedCreditCard, actualOutput.creditCard());
	assertEquals(expectedWeekend, actualOutput.weekend());
	assertEquals(expectedUser, actualOutput.userId());
    }

    @Test
    void givenAValidCommand_whenCallsCreateVariableTaxDoesNotExist_shouldReturnNotFound() {
	// given
	final var expectedDentalShop = BigDecimal.valueOf(1);
	final var expectedProsthetist = BigDecimal.valueOf(2);
	final var expectedTravel = BigDecimal.valueOf(3);
	final var expectedCreditCard = BigDecimal.valueOf(5);
	final var expectedWeekend = BigDecimal.valueOf(4);
	final User expectedUser = Fixture.Users.asa();

	final var aVariableTax = VariableTax.newVariableTax(
		expectedDentalShop, expectedProsthetist,
		expectedTravel, expectedCreditCard, expectedWeekend);
	aVariableTax.addUser(expectedUser.getId());
	final var expectedId = aVariableTax.getId().getValue();

	final var expectedErrorMessage = "VariableTax with ID %s was not found"
		.formatted(expectedId);
	final var expectedErrorCount = 1;

	when(variableTaxGateway.findById(any()))
		.thenReturn(Optional.empty());

	// when
	final var actualException = assertThrows(
		NotFoundException.class,
		() -> useCase.execute(expectedId));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }
}
