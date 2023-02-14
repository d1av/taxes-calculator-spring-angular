package com.taxes.calculator.application.user.create;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.application.utils.MapperUtils;
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
	return List.of(userGateway);
    }

    @Test
    void givenAValidCommand_whenCreateUser_shouldReturnIt() {
	// given
	final var expectedName = Fixture.name();
	final var expectedPassword = Fixture.password(6);
	final var expectedIsActive = true;
	final var expectedRoles = Set.of(Fixture.Roles.guest(),
		Fixture.Roles.member());

	final var aCommand = CreateUserCommand.with(expectedName,
		expectedPassword, expectedIsActive, expectedRoles);

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
	final var expectedRoles = Set.<Role>of();

	final var aCommand = CreateUserCommand.with(expectedName,
		expectedPassword, expectedIsActive, expectedRoles);

	final var mappedRoles = MapperUtils.toID(expectedRoles,
		Role::getId);

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

}
