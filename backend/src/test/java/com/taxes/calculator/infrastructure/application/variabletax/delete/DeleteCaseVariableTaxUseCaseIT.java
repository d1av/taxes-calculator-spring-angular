package com.taxes.calculator.infrastructure.application.variabletax.delete;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.variabletax.delete.DeleteVariableTaxUseCase;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.infrastructure.IntegrationTest;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxJpaEntity;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxRepository;

@IntegrationTest
public class DeleteCaseVariableTaxUseCaseIT {

    @Autowired
    private DeleteVariableTaxUseCase useCase;

    @Autowired
    private VariableTaxRepository repository;

    @Autowired
    private UserRepository userRepository;

    @SpyBean
    private VariableTaxGateway gateway;

    @Test
    void givenAValidCommand_whenDeleteVariableTax_shouldReturnIt() {
	// given
	final var aTax = Fixture.Tax.variable();
	final var expectedUser = Fixture.Tax.getUser();
	repository.saveAndFlush(VariableTaxJpaEntity.from(aTax));

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final var idToDeleteString = aTax.getId().getValue();
	final var idToDelete = aTax.getId();

	// when
	useCase.execute(idToDeleteString);

	// then

	Mockito.verify(gateway).deleteById(eq(idToDelete));
    }

    @Test
    void givenAInvalidCommand_whenDeleteVariableTax_shouldOk() {
	// given
	final var aTax = Fixture.Tax.variable();
	final var expectedUser = Fixture.Tax.getUser();
	repository.saveAndFlush(VariableTaxJpaEntity.from(aTax));

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final String idToDeleteString = "Invalid ID";

	// when
	useCase.execute(idToDeleteString);

	// then

	Mockito.verify(gateway, times(1)).deleteById(any());
    }

}
