package com.taxes.calculator.application.role.update;

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
import com.taxes.calculator.domain.role.RoleGateway;

class UpdateRoleUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultUpdateRoleUseCase useCase;

    @Mock
    private RoleGateway roleGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(roleGateway);
    }

    @Test
    void givenAValidCommand_whenUpdateRole_shouldReturnIt() {
	// given
	final var aAuthority = Fixture.Roles.guest();
	final var expectedAuthority = aAuthority.getAuthority();
	final var expectedId = aAuthority.getId().getValue();

	final var aCommand = UpdateRoleCommand.with(expectedId,
		expectedAuthority);

	when(roleGateway.update(any())).thenAnswer(returnsFirstArg());

	when(roleGateway.findById(any()))
		.thenReturn(Optional.of(aAuthority));

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then
	Assertions.assertNotNull(actualOutput);

	verify(roleGateway, times(1)).update(any());
    }

    @Test
    void givenAInvalidNullAuthorityCommand_whenUpdateRole_shouldReturnNotification() {
	// given
	final var aAuthority = Fixture.Roles.guest();
	final String expectedAuthority = null;
	final var expectedId = aAuthority.getId().getValue();

	final var aCommand = UpdateRoleCommand.with(expectedId,
		expectedAuthority);

	final var expectedErrorMessage = "'authority' should not be null";
	final var expectedErrorCount = 3;

	when(roleGateway.findById(any()))
		.thenReturn(Optional.of(aAuthority));

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

	verify(roleGateway, times(0)).update(any());
    }

    @Test
    void givenAValidCommand_whenUpdateRole_shouldReturnUpdatedRole() {
	// given
	final var aAuthority = Fixture.Roles.guest();
	final String expectedAuthority = "NEWROLE";
	final var expectedId = aAuthority.getId().getValue();

	final var aCommand = UpdateRoleCommand.with(expectedId,
		expectedAuthority);

	when(roleGateway.findById(any()))
		.thenReturn(Optional.of(aAuthority));

	when(roleGateway.update(any())).thenAnswer(returnsFirstArg());

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then
	assertEquals(expectedId, actualOutput.id());

	verify(roleGateway, times(1)).update(any());
    }
}
