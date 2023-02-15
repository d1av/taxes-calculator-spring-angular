package com.taxes.calculator.application.user.delete;

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
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.user.UserID;

class DeleteUserUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteUserUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
	return List.of(userGateway);
    }

    @Test
    void givenAValidUserID_whenCallsDeleteUser_shouldBeOk() {
	// given
	final var aUser = Fixture.Users.asa();
	final var expectedId = aUser.getId();

	doNothing().when(userGateway).deleteById(any());

	// when
	Assertions.assertDoesNotThrow(
		() -> useCase.execute(expectedId.getValue()));

	// then
	Mockito.verify(userGateway, times(1)).deleteById(expectedId);
    }

    @Test
    void givenAInvalidUserID_whenCallsDeleteUser_shouldBeOk() {
	// given
	final var aUser = Fixture.Users.asa();
	final var expectedId = UserID.from("INVALID_ID");

	doNothing().when(userGateway).deleteById(any());

	// when
	Assertions.assertDoesNotThrow(
		() -> useCase.execute(expectedId.getValue()));

	// then
	Mockito.verify(userGateway, times(1)).deleteById(expectedId);
    }

    @Test
    void givenAValidUserId_whenCallsDeleteUserAndGatewayThrows_shouldReceiveException() {
	// given
	final var aUser = Fixture.Users.asa();
	final var expectedId = aUser.getId();
	final var expectedMessage = "Gateway Error";

	doThrow(new IllegalStateException("Gateway Error"))
		.when(userGateway).deleteById(any());

	// when
	final var actualException = Assertions.assertThrows(
		IllegalStateException.class,
		() -> useCase.execute(expectedId.getValue()));

	// then
	Mockito.verify(userGateway, times(1)).deleteById(expectedId);
	Assertions.assertEquals(expectedMessage,
		actualException.getMessage());
    }

}
