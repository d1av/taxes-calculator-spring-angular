package com.taxes.calculator.domain.user;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.role.RoleID;

public class UserTest {

    @Test
    public void givenAValidUser_whenCallsCreateUser_shouldCreateAndReturnIt() {
	// given
	final String expectedName = Fixture.name();
	final String expectedPassword = Fixture.password(8);
	final Set<RoleID> expectedRoles = Set
		.of(Fixture.Roles.member().getId());
	final Boolean expectedActive = true;

	// when
	final var actualUser = User.with(expectedName,
		expectedPassword, expectedActive, expectedRoles);

	final var copiedUser = User.with(actualUser);

	// then
	Assertions.assertNotNull(actualUser);
	Assertions.assertNotNull(actualUser.getId());
	Assertions.assertNotNull(actualUser.getPassword());
	Assertions.assertNotNull(actualUser.getRoles());
	Assertions.assertEquals(expectedName, actualUser.getName());
	Assertions.assertTrue(
		actualUser.getRoles().containsAll(expectedRoles));
	Assertions.assertEquals(expectedActive,
		actualUser.getActive());

	Assertions.assertEquals(actualUser.getId().getValue(),
		copiedUser.getId().getValue());
    }

    @Test
    public void givenAInvalidNullName_whenCallsCreateUser_shouldReturnAnNotification() {
	// given
	final String expectedName = null;
	final String expectedPassword = Fixture.password(8);
	final Set<RoleID> expectedRoles = Set
		.of(Fixture.Roles.member().getId());
	final boolean expectedActive = true;

	final var expectedErrorCount = 2;
	final var expectedErrorMessage = "'name' should not be null";
	final var expectedErrorMessage2 = "'name' must be between 6 and 200 characters";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> User.with(expectedName,
				expectedPassword, expectedActive,
				expectedRoles));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
    }

    @Test
    public void givenAInvalidEmptyName_whenCallsCreateUser_shouldReturnAnNotification() {
	// given
	final String expectedName = "";
	final String expectedPassword = Fixture.password(8);
	final Set<RoleID> expectedRoles = Set
		.of(Fixture.Roles.member().getId());
	final boolean expectedActive = true;

	final var expectedErrorCount = 2;
	final var expectedErrorMessage = "'name' should not be empty";
	final var expectedErrorMessage2 = "'name' must be between 6 and 200 characters";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> User.with(expectedName,
				expectedPassword, expectedActive,
				expectedRoles));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
    }

    @Test
    public void givenAInvalidBlankName_whenCallsCreateUser_shouldReturnAnNotification() {
	// given
	final String expectedName = "    ";
	final String expectedPassword = Fixture.password(8);
	final Set<RoleID> expectedRoles = Set
		.of(Fixture.Roles.member().getId());
	final boolean expectedActive = true;

	final var expectedErrorCount = 2;
	final var expectedErrorMesage1 = "'name' should not be empty";
	final var expectedErrorMesage2 = "'name' must be between 6 and 200 characters";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> User.with(expectedName,
				expectedPassword, expectedActive,
				expectedRoles));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMesage1,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMesage2,
		actualException.getErrors().get(1).message());
    }

    @Test
    public void givenAInvalidNullPassword_whenCallsCreateUser_shouldReturnAnNotification() {
	// given
	final String expectedName = Fixture.name();
	final String expectedPassword = null;
	final Set<RoleID> expectedRoles = Set
		.of(Fixture.Roles.member().getId());
	final boolean expectedActive = true;

	final var expectedErrorCount = 2;
	final var expectedErrorMessage = "'password' should not be null";
	final var expectedErrorMessage2 = "'password' must be between 6 and 255 characters";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> User.with(expectedName,
				expectedPassword, expectedActive,
				expectedRoles));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
    }

    @Test
    public void givenAInvalidEmptyPassword_whenCallsCreateUser_shouldReturnAnNotification() {
	// given
	final String expectedName = Fixture.name();
	final String expectedPassword = Fixture.password(1);
	final Set<RoleID> expectedRoles = Set
		.of(Fixture.Roles.member().getId());
	final boolean expectedActive = true;

	final var expectedErrorCount = 1;
	final var expectedErrorMessage1 = "'password' must be between 6 and 255 characters";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> User.with(expectedName,
				expectedPassword, expectedActive,
				expectedRoles));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage1,
		actualException.firstError().message());
    }

    @Test
    public void givenAInvalidBlankPassword_whenCallsCreateUser_shouldReturnAnNotification() {
	// given
	final String expectedName = Fixture.name();
	final String expectedPassword = "   ";
	final Set<RoleID> expectedRoles = Set
		.of(Fixture.Roles.member().getId());
	final boolean expectedActive = true;

	final var expectedErrorCount = 2;
	final var expectedErrorMessage = "'password' should not be empty";
	final var expectedErrorMessage2 = "'password' must be between 6 and 255 characters";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class,
			() -> User.with(expectedName,
				expectedPassword, expectedActive,
				expectedRoles));

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
    }
}
