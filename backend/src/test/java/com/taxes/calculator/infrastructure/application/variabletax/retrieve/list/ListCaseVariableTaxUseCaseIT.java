package com.taxes.calculator.infrastructure.application.variabletax.retrieve.list;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.variabletax.retrieve.list.ListVariableTaxUseCase;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.variabletax.VariableTax;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;
import com.taxes.calculator.infrastructure.IntegrationTest;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxJpaEntity;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxRepository;

@IntegrationTest
class ListCaseVariableTaxUseCaseIT {

    @Autowired
    private ListVariableTaxUseCase useCase;

    @Autowired
    private VariableTaxRepository repository;

    @Autowired
    private UserRepository userRepository;

    @SpyBean
    private VariableTaxGateway gateway;

    @BeforeEach
    void mockUp() {
	User user1 = User.newUser("MiaKhalifa", "MiaKhalifa", true);
	User user2 = User.newUser("AsaAkira", "AsaAkira", true);
	User user3 = User.newUser("AbellaDanger", "AbellaDanger",
		true);
	User user4 = User.newUser("vinDiesel", "vindiesel", true);
	Fixture.bigDecimal(1);

	final var variableTaxes = Stream
		.of(VariableTax.newVariableTax(
			Fixture.bigDecimal(2), Fixture.bigDecimal(2),
			Fixture.bigDecimal(2), Fixture.bigDecimal(2),
			Fixture.bigDecimal(2), user1.getId()),
			VariableTax.newVariableTax(
				Fixture.bigDecimal(2),
				Fixture.bigDecimal(2),
				Fixture.bigDecimal(2),
				Fixture.bigDecimal(2),
				Fixture.bigDecimal(2), user2.getId()),
			VariableTax.newVariableTax(
				Fixture.bigDecimal(2),
				Fixture.bigDecimal(2),
				Fixture.bigDecimal(2),
				Fixture.bigDecimal(2),
				Fixture.bigDecimal(2), user3.getId()),
			VariableTax.newVariableTax(
				Fixture.bigDecimal(2),
				Fixture.bigDecimal(2),
				Fixture.bigDecimal(2),
				Fixture.bigDecimal(2),
				Fixture.bigDecimal(2), user4.getId()))
		.map(VariableTaxJpaEntity::from).toList();

	repository.saveAllAndFlush(variableTaxes);
    }

    @Test
    void givenAValidCommand_whenDeleteVariableTax_shouldReturnIt() {
	// given
	final var expectedPage = 8;
	final var expectedPerPage = 10;
	final var expectedTerms = "jasiajj 1jijsiajdija";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";
	final var expectedItemsCount = 0;
	final var expectedTotal = 0;

	final var aQuery = new SearchQuery(expectedPage,
		expectedPerPage, expectedTerms, expectedSort,
		expectedDirection);

	// when
	final var actualResult = useCase.execute(aQuery);

	// then
	Assertions.assertEquals(expectedItemsCount,
		actualResult.items().size());
	Assertions.assertEquals(expectedPage,
		actualResult.currentPage());
	Assertions.assertEquals(expectedPerPage,
		actualResult.perPage());
	Assertions.assertEquals(expectedTotal, actualResult.total());
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
	// useCase.execute(idToDeleteString);

	// then

	Mockito.verify(gateway, times(1)).deleteById(any());
    }

}
