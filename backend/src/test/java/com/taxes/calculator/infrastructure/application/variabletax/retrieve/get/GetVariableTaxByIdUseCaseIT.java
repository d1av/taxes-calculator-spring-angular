package com.taxes.calculator.infrastructure.application.variabletax.retrieve.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.variabletax.delete.DeleteVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.retrieve.get.GetVariableTaxByIdUseCase;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.infrastructure.IntegrationTest;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxJpaEntity;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxRepository;

@IntegrationTest
public class GetVariableTaxByIdUseCaseIT {

    @Autowired
    private GetVariableTaxByIdUseCase useCase;

    @Autowired
    private VariableTaxRepository repository;

    @Autowired
    private UserRepository userRepository;

    @SpyBean
    private VariableTaxGateway gateway;

    @Test
    void givenAValidCommand_whenFindVariableTax_shouldReturnIt() {
	// given
	final var aTax = Fixture.Tax.variable();
	final var expectedUser = Fixture.Tax.getUser();

	repository.saveAndFlush(VariableTaxJpaEntity.from(aTax));
	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var idToFind = aTax.getId().getValue();

	// when
	final var actualOutput = useCase.execute(idToFind);

	// then
	assertEquals(aTax.getId().getValue(), actualOutput.id());
	assertEquals(0, aTax.getProsthetist()
		.compareTo(actualOutput.prosthetist()));
	assertEquals(0,
		aTax.getTravel().compareTo(actualOutput.travel()));
	assertEquals(0, aTax.getCreditCard()
		.compareTo(actualOutput.creditCard()));
	assertEquals(0,
		aTax.getWeekend().compareTo(actualOutput.weekend()));
	assertEquals(aTax.getUserId().getValue(),
		actualOutput.userId());
	Mockito.verify(gateway).findById(aTax.getId());
    }

    @Test
    void givenAInvalidNullIdCommand_whenFindVariableTax_shouldReturnNothing() {
	// given
	final var aTax = Fixture.Tax.variable();
	final var expectedUser = Fixture.Tax.getUser();

	repository.saveAndFlush(VariableTaxJpaEntity.from(aTax));
	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var expectedErrorMessage = "The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!";

	final String idToFind = null;

	// when
	final var actualException = Assertions.assertThrows(
		InvalidDataAccessApiUsageException.class,
		() -> useCase.execute(idToFind));

	// then
	assertEquals(expectedErrorMessage,
		actualException.getMessage());
	Mockito.verify(gateway).findById(any());
    }
}
