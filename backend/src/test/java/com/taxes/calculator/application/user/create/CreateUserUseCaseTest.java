package com.taxes.calculator.application.user.create;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.application.utils.MapperUtils;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleGateway;
import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.domain.user.UserGateway;

class CreateUserUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateUserUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @Mock
    private RoleGateway roleGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(userGateway, roleGateway);
    }

    @Test
    void givenAValidCommand_whenCreateUser_shouldReturnIt() {
	// given
	final var expectedName = Fixture.name();
	final var expectedPassword = Fixture.password(6);
	final var expectedIsActive = true;
	final var expectedRoles = Set.of(Fixture.Roles.guest(),
		Fixture.Roles.member());

	final var expectedItems = expectedRoles.stream()
		.map(Role::getId).map(RoleID::getValue)
		.collect(Collectors.toSet());

	final var aCommand = CreateUserCommand.with(expectedName,
		expectedPassword, expectedIsActive, expectedItems);

	final var mappedRoles = MapperUtils.toID(expectedRoles,
		Role::getId);

	when(userGateway.create(any())).thenAnswer(returnsFirstArg());

	when(roleGateway.existsByIds(eq(mappedRoles)))
		.thenReturn(mappedRoles);

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then
	Assertions.assertNotNull(actualOutput);

	verify(userGateway).create(argThat(aUser -> Objects
		.nonNull(aUser.getId())
		&& Objects.equals(expectedName, aUser.getName())
		&& Objects.equals(expectedPassword,
			aUser.getPassword())
		&& Objects.equals(expectedIsActive, aUser.getActive())
		&& Objects.nonNull(aUser.getCreatedAt())
		&& Objects.nonNull(aUser.getUpdatedAt())));
    }

    @Test
    void givenAValidCommandWithNullRoles_whenCreateUser_shouldReturnIt() {
	// given
	final var expectedName = Fixture.name();
	final var expectedPassword = Fixture.password(6);
	final var expectedIsActive = true;
	final var expectedRoles = Set.<String>of();

	final var aCommand = CreateUserCommand.with(expectedName,
		expectedPassword, expectedIsActive, expectedRoles);

	when(userGateway.create(any())).thenAnswer(returnsFirstArg());

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then
	Assertions.assertNotNull(actualOutput);

	verify(userGateway).create(argThat(aUser -> Objects
		.nonNull(aUser.getId())
		&& Objects.equals(expectedName, aUser.getName())
		&& Objects.equals(expectedPassword,
			aUser.getPassword())
		&& Objects.equals(expectedIsActive, aUser.getActive())
		&& Objects.nonNull(aUser.getCreatedAt())
		&& Objects.nonNull(aUser.getUpdatedAt())));
    }

    @Test
    void givenAInvalidNullNameCommand_whenCreateUser_shouldReturnException() {
	// given
	final String expectedName = null;
	final var expectedPassword = Fixture.password(6);
	final var expectedIsActive = true;
	final var expectedRoles = Set.of(Fixture.Roles.guest(),
		Fixture.Roles.member());

	final var expectedItems = expectedRoles.stream()
		.map(Role::getId).map(RoleID::getValue)
		.collect(Collectors.toSet());

	final var expectedErrorCount = 2;
	final var expectedErrorMessage = "'name' should not be null";
	final var expectedErrorMessage2 = "'name' must be between 1 and 200 characters";

	final var aCommand = CreateUserCommand.with(expectedName,
		expectedPassword, expectedIsActive, expectedItems);

	final var mappedRoles = MapperUtils.toID(expectedRoles,
		Role::getId);

	when(roleGateway.existsByIds(any())).thenReturn(mappedRoles);

	// when

	final var actualException = Assertions
		.assertThrows(NotificationException.class, () -> {
		    useCase.execute(aCommand);
		});

	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());

	verify(userGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidNullPasswordCommand_whenCreateUser_shouldReturnException() {
	// given
	final var expectedName = Fixture.name();
	final String expectedPassword = null;
	final var expectedIsActive = true;
	final var expectedRoles = Set.of(Fixture.Roles.guest(),
		Fixture.Roles.member());

	final var expectedItems = expectedRoles.stream()
		.map(Role::getId).map(RoleID::getValue)
		.collect(Collectors.toSet());

	final var expectedErrorCount = 2;
	final var expectedErrorMessage = "'password' should not be null";
	final var expectedErrorMessage2 = "'password' must be between 6 and 20 characters";

	final var aCommand = CreateUserCommand.with(expectedName,
		expectedPassword, expectedIsActive, expectedItems);

	final var mappedRoles = MapperUtils.toID(expectedRoles,
		Role::getId);

	when(roleGateway.existsByIds(any())).thenReturn(mappedRoles);

	// when

	final var actualException = Assertions
		.assertThrows(NotificationException.class, () -> {
		    useCase.execute(aCommand);
		});

	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());

	verify(userGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidMinLengthPasswordCommand_whenCreateUser_shouldReturnException() {
	// given
	final var expectedName = Fixture.name();
	final String expectedPassword = Fixture.text(4);
	final var expectedIsActive = true;
	final var expectedItems = Set.of(Fixture.Roles.guest(),
		Fixture.Roles.member());

	final var expectedRoles = expectedItems.stream()
		.map(Role::getId).map(RoleID::getValue)
		.collect(Collectors.toSet());

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'password' must be between 6 and 20 characters";

	final var aCommand = CreateUserCommand.with(expectedName,
		expectedPassword, expectedIsActive, expectedRoles);

	final var mappedRoles = MapperUtils.toID(expectedItems,
		Role::getId);

	when(roleGateway.existsByIds(any())).thenReturn(mappedRoles);

	// when

	final var actualException = Assertions
		.assertThrows(NotificationException.class, () -> {
		    useCase.execute(aCommand);
		});

	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	verify(userGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidMaxLengthPasswordCommand_whenCreateUser_shouldReturnException() {
	// given
	final var expectedName = Fixture.name();
	final String expectedPassword = Fixture.text(21);
	final var expectedIsActive = true;
	final var expectedItems = Set.of(Fixture.Roles.guest(),
		Fixture.Roles.member());

	final var expectedRoles = expectedItems.stream()
		.map(Role::getId).map(RoleID::getValue)
		.collect(Collectors.toSet());

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'password' must be between 6 and 20 characters";

	final var aCommand = CreateUserCommand.with(expectedName,
		expectedPassword, expectedIsActive, expectedRoles);

	final var mappedRoles = MapperUtils.toID(expectedItems,
		Role::getId);

	when(roleGateway.existsByIds(any())).thenReturn(mappedRoles);

	// when

	final var actualException = Assertions
		.assertThrows(NotificationException.class, () -> {
		    useCase.execute(aCommand);
		});

	// then
	Assertions.assertNotNull(actualException);
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());

	verify(userGateway, times(0)).create(any());
    }

    @Test
    void givenAInvalidNullActiveCommand_whenCreateUser_shouldReturnActiveUser() {
	// given
	final var expectedName = Fixture.name();
	final var expectedPassword = Fixture.text(8);
	final Boolean expectedIsActive = null;
	final var expectedItems = Set.of(Fixture.Roles.guest(),
		Fixture.Roles.member());

	final var expectedRoles = expectedItems.stream()
		.map(Role::getId).map(RoleID::getValue)
		.collect(Collectors.toSet());

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'password' must be between 6 and 20 characters";

	final var aCommand = CreateUserCommand.with(expectedName,
		expectedPassword, expectedIsActive, expectedRoles);

	final var mappedRoles = MapperUtils.toID(expectedItems,
		Role::getId);

	when(userGateway.create(any())).thenAnswer(returnsFirstArg());

	when(roleGateway.existsByIds(eq(mappedRoles)))
		.thenReturn(mappedRoles);

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then
	Assertions.assertNotNull(actualOutput);

	verify(userGateway).create(argThat(aUser -> Objects
		.nonNull(aUser.getId())
		&& Objects.equals(expectedName, aUser.getName())
		&& Objects.equals(expectedPassword,
			aUser.getPassword())
		&& Objects.equals(true, aUser.getActive())
		&& Objects.nonNull(aUser.getCreatedAt())
		&& Objects.nonNull(aUser.getUpdatedAt())));
    }

    @Test
    void givenAEmptyRoleCommand_whenCreateUser_shouldReturnUser() {
	// given
	final var expectedName = Fixture.name();
	final var expectedPassword = Fixture.password(10);
	final var expectedIsActive = true;
	final var expectedRoles = Set.<String>of();

	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'password' should not be null";

	final var aCommand = CreateUserCommand.with(expectedName,
		expectedPassword, expectedIsActive, expectedRoles);

	when(userGateway.create(any())).thenAnswer(returnsFirstArg());

	// when
	final var actualOutput = useCase.execute(aCommand);

	// then
	Assertions.assertNotNull(actualOutput);

	verify(userGateway).create(argThat(aUser -> Objects
		.nonNull(aUser.getId())
		&& Objects.equals(expectedName, aUser.getName())
		&& Objects.equals(expectedPassword,
			aUser.getPassword())
		&& Objects.equals(true, aUser.getActive())
		&& Objects.nonNull(aUser.getCreatedAt())
		&& Objects.nonNull(aUser.getUpdatedAt())));
    }

}
