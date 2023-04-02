package com.taxes.calculator.infrastructure.application.variabletax.retrieve.list;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.variabletax.delete.DeleteVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.retrieve.list.ListVariableTaxUseCase;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.infrastructure.IntegrationTest;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxJpaEntity;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxRepository;

@IntegrationTest
public class ListCaseVariableTaxUseCaseIT {

    @Autowired
    private ListVariableTaxUseCase useCase;

    @Autowired
    private VariableTaxRepository repository;

    @Autowired
    private UserRepository userRepository;

    @SpyBean
    private VariableTaxGateway gateway;

    @Test
    void givenAValidCommand_whenDeleteVariableTax_shouldReturnIt() {
	// given
	final var aTax = List.of(Fixture.Tax.variable(),
		Fixture.Tax.variable2());
	final var expectedUser = Fixture.Tax.getUser();
	repository.saveAll(aTax.stream()
		.map(VariableTaxJpaEntity::from).toList());



//	// when
//	useCase.execute(idToDeleteString);
//
//	// then
//
//	Mockito.verify(gateway).deleteById(eq(idToDelete));
    }

    @Test
    void givenAInvalidCommand_whenListVariableTax_shouldOk() {
	// given
	final var aTax = Fixture.Tax.variable();
	final var expectedUser = Fixture.Tax.getUser();
	repository.saveAndFlush(VariableTaxJpaEntity.from(aTax));

	userRepository.saveAndFlush(UserJpaEntity.from(expectedUser));

	final String idToDeleteString = "Invalid ID";

	// when
	//useCase.execute(idToDeleteString);

	// then

	Mockito.verify(gateway, times(1)).deleteById(any());
    }

}
