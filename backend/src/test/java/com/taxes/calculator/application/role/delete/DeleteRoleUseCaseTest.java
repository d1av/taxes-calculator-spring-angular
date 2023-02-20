package com.taxes.calculator.application.role.delete;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.application.UseCaseTest;
import com.taxes.calculator.domain.role.RoleGateway;
import com.taxes.calculator.domain.role.RoleID;

class DeleteRoleUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteRoleUseCase useCase;

    @Mock
    private RoleGateway roleGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(roleGateway);
    }

    @Test
    void givenAValidRoleId_whenCallsDeleteRole_shouldReturnOk() {
	// given
	final var expectedRole = Fixture.Roles.guest();

	final var expectedId = expectedRole.getId();

	doNothing().when(roleGateway).deleteById(any());
	// when
	Assertions.assertDoesNotThrow(
		() -> useCase.execute(expectedId.getValue()));

	// then
	Mockito.verify(roleGateway, times(1))
		.deleteById(expectedId);

    }

    @Test
    void givenAInvalidRoleId_whenCallsDeleteRole_shouldReturnOk() {
	// given
	final var expectedRole = Fixture.Roles.member();

	final var expectedId = RoleID.from("INVALID_ID");

	doNothing().when(roleGateway).deleteById(any());
	// when
	Assertions.assertDoesNotThrow(
		() -> useCase.execute(expectedId.getValue()));

	// then
	Mockito.verify(roleGateway, times(1))
		.deleteById(expectedId);
    }

    @Test
    void givenAValidRoleId_whenCallsDeleteRoleAndGatewatThrowsUnexpectedError_shouldReceiveException() {
	// given
	final var expectedRole = Fixture.Roles.member();
	final var expectedId = expectedRole.getId();

	doThrow(new IllegalStateException("Gateway error"))
		.when(roleGateway).deleteById(any());
	// when
	Assertions.assertThrows(IllegalStateException.class,
		() -> useCase.execute(expectedId.getValue()));

	// then

	Mockito.verify(roleGateway, times(1))
		.deleteById(expectedId);

    }

}
