package com.taxes.calculator.application.hourvalue.calculate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.exceptions.DomainException;
import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.fixedtax.FixedTaxID;
import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.hourvalue.HourValueID;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxID;

class CalculateHourValueUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCalculateHourValueUseCase useCase;

    @Mock
    private HourValueGateway hourValueGateway;
    @Mock
    private FixedTaxGateway fixedTaxGateway;
    @Mock
    private VariableTaxGateway variableTaxGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(hourValueGateway, fixedTaxGateway,
		variableTaxGateway);
    }

    @Test
    void givenValidIds_whenCallsCalculate_shouldReturnHourValue() {
	// given
	final var aVariableTax = Fixture.Calculate.variable();
	final var aFixedTax = Fixture.Calculate.fixed();
	final var aHourValue = Fixture.Calculate.hourValue();
	final var aUser = Fixture.Calculate.nonVariableUser();

	final var expectedVariableTaxId = aVariableTax.getId();
	final var expectedFixedTaxId = aFixedTax.getId();
	final var expectedHourValueId = aHourValue.getId();

	final var expectedSalary = BigDecimal.valueOf(10);
	final var expectedCalculatedHourValue = BigDecimal.valueOf(0.9375);

	when(variableTaxGateway.findById(expectedVariableTaxId))
		.thenReturn(Optional.of(aVariableTax));
	when(fixedTaxGateway.findById(expectedFixedTaxId))
		.thenReturn(Optional.of(aFixedTax));
	when(hourValueGateway.findById(expectedHourValueId))
		.thenReturn(Optional.of(aHourValue));

	final var aCommand = CalculateHourValueCommand.with(
		expectedFixedTaxId.getValue(),
		expectedVariableTaxId.getValue(),
		expectedHourValueId.getValue(), aUser.getId().getValue());

	// when

	final var actualOutput = useCase.execute(aCommand);

	// then

	assertEquals(expectedHourValueId.getValue(),
		actualOutput.hourValueId());
	assertEquals(expectedFixedTaxId.getValue(),
		actualOutput.fixedTaxId());
	assertEquals(expectedVariableTaxId.getValue(),
		actualOutput.variableTaxId());
	assertEquals(expectedSalary, actualOutput.expectedSalary());
	assertEquals(aHourValue.getDaysOfWork() * 8,
		actualOutput.hoursWorked());
	assertEquals(expectedCalculatedHourValue,
		actualOutput.hourValue());
    }

    @Test
    void givenInvalidVariableTaxId_whenCallsCalculate_shouldReturnNotification() {
	// given
	final var aVariableTax = Fixture.Calculate.variable();
	final var aFixedTax = Fixture.Calculate.fixed();
	final var aHourValue = Fixture.Calculate.hourValue();
	final var aUser = Fixture.Calculate.nonVariableUser();

	final var expectedVariableTaxId = VariableTaxID.from("INVALID");
	final var expectedFixedTaxId = aFixedTax.getId();
	final var expectedHourValueId = aHourValue.getId();

	final var expectedErrorMessage = "Variable Tax not found with id: INVALID";
	final var expectedErrorCount = 1;

	when(variableTaxGateway.findById(expectedVariableTaxId))
		.thenReturn(Optional.empty());

	final var aCommand = CalculateHourValueCommand.with(
		expectedFixedTaxId.getValue(),
		expectedVariableTaxId.getValue(),
		expectedHourValueId.getValue(), aUser.getId().getValue());

	// when

	final var actualException = assertThrows(DomainException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenInvalidFixedTaxId_whenCallsCalculate_shouldReturnNotification() {
	// given
	final var aVariableTax = Fixture.Calculate.variable();
	final var aFixedTax = Fixture.Calculate.fixed();
	final var aHourValue = Fixture.Calculate.hourValue();
	final var aUser = Fixture.Calculate.nonVariableUser();

	final var expectedFixedTaxId = FixedTaxID.from("INVALID");
	final var expectedVariableTaxId = aVariableTax.getId();
	final var expectedHourValueId = aHourValue.getId();

	final var expectedErrorMessage = "Fixed Tax not found with id: INVALID";
	final var expectedErrorCount = 1;

	when(fixedTaxGateway.findById(expectedFixedTaxId))
		.thenReturn(Optional.empty());
	when(variableTaxGateway.findById(expectedVariableTaxId))
		.thenReturn(Optional.of(aVariableTax));

	final var aCommand = CalculateHourValueCommand.with(
		expectedFixedTaxId.getValue(),
		expectedVariableTaxId.getValue(),
		expectedHourValueId.getValue(), aUser.getId().getValue());

	// when

	final var actualException = assertThrows(DomainException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenInvalidHourValueId_whenCallsCalculate_shouldReturnNotification() {
	// given
	final var aVariableTax = Fixture.Calculate.variable();
	final var aFixedTax = Fixture.Calculate.fixed();
	final var aHourValue = Fixture.Calculate.hourValue();
	final var aUser = Fixture.Calculate.nonVariableUser();

	final var expectedHourValueId = HourValueID.from("INVALID");
	final var expectedVariableTaxId = aVariableTax.getId();
	final var expectedFixedTaxId = aFixedTax.getId();

	final var expectedErrorMessage = "Hour Value not found with id: INVALID";
	final var expectedErrorCount = 1;

	when(hourValueGateway.findById(expectedHourValueId))
		.thenReturn(Optional.empty());
	when(variableTaxGateway.findById(expectedVariableTaxId))
		.thenReturn(Optional.of(aVariableTax));
	when(fixedTaxGateway.findById(expectedFixedTaxId))
		.thenReturn(Optional.of(aFixedTax));

	final var aCommand = CalculateHourValueCommand.with(
		expectedFixedTaxId.getValue(),
		expectedVariableTaxId.getValue(),
		expectedHourValueId.getValue(), aUser.getId().getValue());

	// when

	final var actualException = assertThrows(DomainException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenInvalidUserId_whenCallsCalculate_shouldReturnNotification() {
	// given
	final var aVariableTax = Fixture.Calculate.variable();
	final var aFixedTax = Fixture.Calculate.fixed();
	final var aHourValue = Fixture.Calculate.hourValue();
	final var aUser = Fixture.Calculate.nonVariableUser();

	final var expectedHourValueId = aHourValue.getId();
	final var expectedVariableTaxId = aVariableTax.getId();
	final var expectedFixedTaxId = aFixedTax.getId();
	final var invalidUserID = UserID.from("INVALID").getValue();

	final var expectedErrorMessage1 = "Wrong Variable Tax for User with id: INVALID";
	final var expectedErrorMessage2 = "Wrong Fixed Tax for User with id: INVALID";
	final var expectedErrorMessage3 = "Wrong Hour Value for User with id: INVALID";
	final var expectedErrorCount = 3;

	when(hourValueGateway.findById(expectedHourValueId))
		.thenReturn(Optional.of(aHourValue));
	when(variableTaxGateway.findById(expectedVariableTaxId))
		.thenReturn(Optional.of(aVariableTax));
	when(fixedTaxGateway.findById(expectedFixedTaxId))
		.thenReturn(Optional.of(aFixedTax));

	final var aCommand = CalculateHourValueCommand.with(
		expectedFixedTaxId.getValue(),
		expectedVariableTaxId.getValue(),
		expectedHourValueId.getValue(), invalidUserID);

	// when

	final var actualException = assertThrows(DomainException.class,
		() -> useCase.execute(aCommand));

	// then

	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage1, actualException.getErrors().get(0).message());
	assertEquals(expectedErrorMessage2, actualException.getErrors().get(1).message());
	assertEquals(expectedErrorMessage3, actualException.getErrors().get(2).message());
    }
}
