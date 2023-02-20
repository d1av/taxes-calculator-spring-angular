package com.taxes.calculator.application.role.retrieve.get;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleGateway;
import com.taxes.calculator.domain.role.RoleID;

class GetRoleByIdUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetRoleByIdUseCase useCase;

    @Mock
    private RoleGateway roleGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(roleGateway);
    }

    @Test
    void givenAValidId_whenCallsGetRole_shouldReturnRoleUPPERCASE() {
	// given
	final var aAuthority = Fixture.Roles.roleName();
	
	final var expectedAuthority = aAuthority.toUpperCase();

	final var aRole = Role.newRole(expectedAuthority);

	final var expectedId = aRole.getId();

	when(roleGateway.findById(any()))
		.thenReturn(Optional.of(aRole));

	// when
	final var actualRole = useCase.execute(expectedId.getValue());

	// then
	Assertions.assertEquals(expectedId.getValue(),
		actualRole.id());
	Assertions.assertEquals(expectedAuthority,
		actualRole.authority());

	Mockito.verify(roleGateway, times(1))
		.findById(eq(expectedId));
    }

    @Test
    void givenAValidId_whenCallsGetRoleAndDoesNotExists_shouldReturnNotFound() {
	// given
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "Role with ID 123 was not found";
	final var expectedId = RoleID.from("123");

	when(roleGateway.findById(any()))
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

	Mockito.verify(roleGateway, times(1))
		.findById(eq(expectedId));
    }

    @Test
    void givenAValidId_whenCallsGetRoleAndGatewayThrows_shouldReturnError() {
	// given
	final var aAuthority = Fixture.name();

	final var expectedErrorMessage = "Gateway error";

	final var aRole = Role.newRole(aAuthority);

	final var expectedId = aRole.getId();

	doThrow(new IllegalStateException("Gateway error"))
		.when(roleGateway).findById(eq(expectedId));
	// when
	final var actualException = Assertions.assertThrows(
		IllegalStateException.class,
		() -> useCase.execute(expectedId.getValue()));

	// then
	Assertions.assertEquals(expectedErrorMessage,
		actualException.getMessage());

	Mockito.verify(roleGateway, times(1))
		.findById(eq(expectedId));
    }

}
