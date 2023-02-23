package com.taxes.calculator.application.role.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleGateway;

class CreateRoleUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateRoleUseCase useCase;

    @Mock
    private RoleGateway roleGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(roleGateway);
    }

    @Test
    void givenAValidCommand_whenCreateRole_shouldReturnIt() {
	// given
	final var expectedAuthority = Fixture.Roles.guest()
		.getAuthority();

	final var aCommand = CreateRoleCommand
		.with(expectedAuthority);

	when(roleGateway.create(any())).thenAnswer(returnsFirstArg());

	when(roleGateway.findByAuthority(any()))
		.thenReturn(Optional.<Role>empty());

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then
	Assertions.assertNotNull(actualOutput);

	verify(roleGateway, times(1)).create(any());
    }

    @Test
    void givenAInvalidNullAuthorityCommand_whenCreateRole_shouldReturnNotification() {
	// given
	final String expectedAuthority = null;

	final var aCommand = CreateRoleCommand
		.with(expectedAuthority);

	final var expectedErrorMessage = "'authority' should not be null";
	final var expectedErrorMessage2 = "'authority' should not be empty";
	final var expectedErrorMessage3 = "'authority' must be between 4 and 20 characters";
	final var expectedErrorCount = 3;

	when(roleGateway.findByAuthority(any()))
		.thenReturn(Optional.<Role>empty());

	// when
	final var actualException = assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	Assertions.assertNotNull(actualException);
	assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());
	assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
	assertEquals(expectedErrorMessage3,
		actualException.getErrors().get(2).message());

	verify(roleGateway, times(0)).create(any());
    }

    @Test
    void givenAValidCommand_whenCreateRoleAlreadyExists_shouldReturnNotification() {
	// given
	final var expectedAuthority = Fixture.Roles.member();

	final var aCommand = CreateRoleCommand
		.with(expectedAuthority.getAuthority());

	final var expectedErrorMessage = "Role already exists with name: %s"
		.formatted(expectedAuthority.getAuthority());
	final var expectedErrorCount = 1;

	when(roleGateway.findByAuthority(any()))
		.thenReturn(Optional.of(expectedAuthority));

	// when
	final var actualException = assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	Assertions.assertNotNull(actualException);
	assertEquals(expectedErrorMessage,
		actualException.firstError().message());
	assertEquals(expectedErrorCount,
		actualException.getErrors().size());

	verify(roleGateway, times(0)).create(any());
    }
}
