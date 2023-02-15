package com.taxes.calculator.application.user.retrieve.get;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.user.UserID;

class GetUserByIdUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetUserByIdUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(userGateway);
    }

    @Test
    void givenAValidId_whenCallsGetUser_shouldReturnUser() {
	// given
	final var expectedName = Fixture.name();
	final var expectedPassword = Fixture.password(10); // not shown in output
	final var expectedIsActive = true;
	final var expectedRoles = Set.<Role>of();

	final var aUser = User.newUser(expectedName, expectedPassword,
		expectedIsActive);

	final var expectedId = aUser.getId();

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(aUser));

	// when
	final var actualUser = useCase.execute(expectedId.getValue());

	// then
	Assertions.assertEquals(expectedId.getValue(),
		actualUser.id());
	Assertions.assertEquals(expectedName, actualUser.name());
	Assertions.assertEquals(expectedIsActive,
		actualUser.active());
	Assertions.assertEquals(expectedRoles, actualUser.roles());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedId));
    }

    @Test
    void givenAValidId_whenCallsGetUserAndDoesNotExists_shouldReturnNotFound() {
	// given
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "User with ID 123 was not found";
	final var expectedId = UserID.from("123");

	when(userGateway.findById(any()))
		.thenReturn(Optional.empty());

	// when
	final var actualException = Assertions.assertThrows(
		NotFoundException.class,
		() -> useCase.execute(expectedId.getValue()));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedId));
    }

    @Test
    void givenAValidId_whenCallsGetUserAndGatewayThrows_shouldReturnError() {
	// given
	final var expectedName = Fixture.name();
	final var expectedPassword = Fixture.password(10); // not shown in output
	final var expectedIsActive = true;

	final var expectedErrorMessage = "Gateway error";

	final var aUser = User.newUser(expectedName, expectedPassword,
		expectedIsActive);

	final var expectedId = aUser.getId();

	doThrow(new IllegalStateException("Gateway error"))
		.when(userGateway).findById(eq(expectedId));
	// when
	final var actualException = Assertions.assertThrows(
		IllegalStateException.class,
		() -> useCase.execute(expectedId.getValue()));

	// then
	Assertions.assertEquals(expectedErrorMessage,
		actualException.getMessage());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedId));
    }

}
