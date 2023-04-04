package com.taxes.calculator.infrastructure.application.variabletax.retrieve.list;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.List;
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

	final var users = Stream.of(user1, user2, user3, user4)
		.map(UserJpaEntity::from).toList();

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
	userRepository.saveAllAndFlush(users);
    }

    @Test
    void givenAValidCommand_whenDeleteVariableTax_shouldReturnIt() {
	// given
	final var expectedPage = 0;
	final var expectedPerPage = 4;
	final var expectedTerms = "";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";
	final var expectedItemsCount = 4;
	final var expectedTotal = 4;

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
    void givenAValidCommandWithNoResultTerms_whenDeleteVariableTax_shouldReturnEmpty() {
	// given
	final var expectedPage = 0;
	final var expectedPerPage = 4;
	final var expectedTerms = "dasdasd  vbasdada";
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
    void givenAValidCommand_whenDeleteVariableTax_shouldReturnException() {
	// given
	final var expectedPage = 0;
	final var expectedPerPage = 4;
	final var expectedTerms = "";
	final var expectedSort = "createdAt";
	final var expectedDirection = "asc";

	final var actualMessage = "Gateway error";

	final var aQuery = new SearchQuery(expectedPage,
		expectedPerPage, expectedTerms, expectedSort,
		expectedDirection);

	Mockito.doThrow(new IllegalStateException("Gateway error"))
		.when(gateway).findAll(any());
	// when
	final var actualException = Assertions.assertThrows(
		IllegalStateException.class,
		() -> useCase.execute(aQuery));

	// then
	Assertions.assertEquals(actualMessage,
		actualException.getMessage());

	Mockito.verify(gateway).findAll(eq(aQuery));
    }

}
