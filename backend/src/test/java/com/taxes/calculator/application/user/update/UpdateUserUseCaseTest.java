package com.taxes.calculator.application.user.update;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.role.RoleGateway;
import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.domain.user.UserGateway;

class UpdateUserUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultUpdateUserUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @Mock
    private RoleGateway roleGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(userGateway);
    }

    @Test
    void givenAValidCommand_whenCallsUpdateUser_shouldReturnUserId() {
	// given
	final var aUser = Fixture.Users.asa();

	final var expectedId = aUser.getId();
	final var expectedName = "MiaAliphense";
	final var expectedPassword = "miamiamia";
	final var expectedIsActive = true;
	final var expectedRoles = Set.<RoleID>of();

	final var aCommand = UpdateUserCommand.with(
		expectedId.getValue(), expectedName, expectedPassword,
		expectedIsActive, expectedRoles);

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(aUser));

	when(userGateway.update(any()))
		.thenAnswer(AdditionalAnswers.returnsFirstArg());

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then
	Assertions.assertNotNull(actualOutput);
	Assertions.assertEquals(expectedId.getValue(),
		actualOutput.id());

	verify(userGateway, times(1)).findById(expectedId.getValue());

	verify(userGateway, times(1))
		.update(argThat(aUpdatedUser -> Objects
			.equals(expectedId, aUpdatedUser.getId())
			&& Objects.equals(expectedName,
				aUpdatedUser.getName())
			&& Objects.equals(expectedPassword,
				aUpdatedUser.getPassword())
			&& Objects.equals(expectedIsActive,
				aUpdatedUser.getActive())
			&& Objects.equals(expectedRoles,
				aUpdatedUser.getRoles())));
    }

    @Test
    void givenAValidCommandwithRoles_whenCallsUpdateUser_shouldReturnUserId() {
	// given
	final var aUser = Fixture.Users.asa();

	final var expectedId = aUser.getId();
	final var expectedName = "MiaAliphense";
	final var expectedPassword = "miamiamia";
	final var expectedIsActive = true;
	final var expectedRoles = Set.<RoleID>of(
		Fixture.Roles.guest().getId(),
		Fixture.Roles.member().getId());

	final var aCommand = UpdateUserCommand.with(
		expectedId.getValue(), expectedName, expectedPassword,
		expectedIsActive, expectedRoles);

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(aUser));

	when(userGateway.update(any()))
		.thenAnswer(AdditionalAnswers.returnsFirstArg());

	when(roleGateway.existsByIds(any()))
		.thenReturn(expectedRoles);

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then
	Assertions.assertNotNull(actualOutput);
	Assertions.assertEquals(expectedId.getValue(),
		actualOutput.id());

	verify(userGateway, times(1)).findById(expectedId.getValue());

	verify(userGateway, times(1))
		.update(argThat(aUpdatedUser -> Objects
			.equals(expectedId, aUpdatedUser.getId())
			&& Objects.equals(expectedName,
				aUpdatedUser.getName())
			&& Objects.equals(expectedPassword,
				aUpdatedUser.getPassword())
			&& Objects.equals(expectedIsActive,
				aUpdatedUser.getActive())
			&& Objects.equals(expectedRoles,
				aUpdatedUser.getRoles())));
    }

    @Test
    void givenAInvalidNameCommand_whenCallsUpdateUser_shouldReturnException() {
	// given
	final var aUser = Fixture.Users.asa();

	final var expectedId = aUser.getId();
	final String expectedName = null;
	final var expectedPassword = "miamiamia";
	final var expectedIsActive = true;
	final var expectedRoles = Set.<RoleID>of();

	final var expectedErrorCount = 2;
	final var expectedErrorMessage = "'name' should not be null";
	final var expectedErrorMessage2 = "'name' must be between 6 and 200 characters";

	final var aCommand = UpdateUserCommand.with(
		expectedId.getValue(), expectedName, expectedPassword,
		expectedIsActive, expectedRoles);

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(aUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedId.getValue()));
	Mockito.verify(roleGateway, times(0)).existsByIds(any());
	Mockito.verify(userGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidNullPassword_whenCallsUpdateUser_shouldReturnException() {
	// given
	final var aUser = Fixture.Users.asa();

	final var expectedId = aUser.getId();
	final String expectedName = "Mia";
	final String expectedPassword = null;
	final var expectedIsActive = true;
	final var expectedRoles = Set.<RoleID>of();

	final var expectedErrorCount = 3;
	final var expectedErrorMessage1 = "'name' must be between 6 and 200 characters";
	final var expectedErrorMessage2 = "'password' should not be null";
	final var expectedErrorMessage3 = "'password' must be between 6 and 255 characters";

	final var aCommand = UpdateUserCommand.with(
		expectedId.getValue(), expectedName, expectedPassword,
		expectedIsActive, expectedRoles);

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(aUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage1,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
	Assertions.assertEquals(expectedErrorMessage3,
		actualException.getErrors().get(2).message());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedId.getValue()));
	Mockito.verify(roleGateway, times(0)).existsByIds(any());
	Mockito.verify(userGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidMinLengthPassword_whenCallsUpdateUser_shouldReturnException() {
	// given
	final var aUser = Fixture.Users.asa();

	final var expectedId = aUser.getId();
	final String expectedName = "Mia";
	final String expectedPassword = Fixture.password(5);
	final var expectedIsActive = true;
	final var expectedRoles = Set.<RoleID>of();

	final var expectedErrorCount = 2;
	final var expectedErrorMessage1 = "'name' must be between 6 and 200 characters";
	final var expectedErrorMessage2 = "'password' must be between 6 and 255 characters";

	final var aCommand = UpdateUserCommand.with(
		expectedId.getValue(), expectedName, expectedPassword,
		expectedIsActive, expectedRoles);

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(aUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage1,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedId.getValue()));
	Mockito.verify(roleGateway, times(0)).existsByIds(any());
	Mockito.verify(userGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidMaxLengthPassword_whenCallsUpdateUser_shouldReturnException() {
	// given
	final var aUser = Fixture.Users.asa();

	final var expectedId = aUser.getId();
	final String expectedName = "Mia";
	final String expectedPassword = Fixture.password(256);
	final var expectedIsActive = true;
	final var expectedRoles = Set.<RoleID>of();

	final var expectedErrorCount = 2;
	final var expectedErrorMessage1 = "'name' must be between 6 and 200 characters";
	final var expectedErrorMessage2 = "'password' must be between 6 and 255 characters";

	final var aCommand = UpdateUserCommand.with(
		expectedId.getValue(), expectedName, expectedPassword,
		expectedIsActive, expectedRoles);

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(aUser));

	// when
	final var actualException = Assertions.assertThrows(
		NotificationException.class,
		() -> useCase.execute(aCommand));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage1,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());

	Mockito.verify(userGateway, times(1))
		.findById(eq(expectedId.getValue()));
	Mockito.verify(roleGateway, times(0)).existsByIds(any());
	Mockito.verify(userGateway, times(0)).update(any());
    }

    @Test
    void givenAInvalidActive_whenCallsUpdateUser_shouldReturnUserId() {
	// given
	final var aUser = Fixture.Users.asa();

	final var expectedId = aUser.getId();
	final String expectedName = "MiaAliphense";
	final String expectedPassword = Fixture.password(10);
	final Boolean initialIsActive = null;
	final Boolean expectedIsActive = true;
	final var expectedRoles = Set.<RoleID>of();

	final var aCommand = UpdateUserCommand.with(
		expectedId.getValue(), expectedName, expectedPassword,
		initialIsActive, expectedRoles);

	when(userGateway.findById(any()))
		.thenReturn(Optional.of(aUser));

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then
	Assertions.assertNotNull(actualOutput);
	Assertions.assertEquals(expectedId.getValue(),
		actualOutput.id());

	verify(userGateway, times(1)).findById(expectedId.getValue());

	verify(userGateway, times(1))
		.update(argThat(aUpdatedUser -> Objects
			.equals(expectedId, aUpdatedUser.getId())
			&& Objects.equals(expectedName,
				aUpdatedUser.getName())
			&& Objects.equals(expectedPassword,
				aUpdatedUser.getPassword())
			&& Objects.equals(expectedIsActive,
				aUpdatedUser.getActive())
			&& Objects.equals(expectedRoles,
				aUpdatedUser.getRoles())));
    }

}
