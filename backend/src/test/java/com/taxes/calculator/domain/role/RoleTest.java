package com.taxes.calculator.domain.role;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.domain.exceptions.NotificationException;

class RoleTest {

    @Test
    void givenValidParams_whenCallNewRole_shouldInstantiateARole() {
	// given
	final var expectedAuthority = Fixture.Roles.guest()
		.getAuthority();

	// when
	final var actualRole = Role.newRole(expectedAuthority);

	// then
	Assertions.assertNotNull(actualRole);
	Assertions.assertNotNull(actualRole.getId());
	Assertions.assertEquals(expectedAuthority,
		actualRole.getAuthority());
    }

    @Test
    void givenAValidRoleWithAuthorityInLowerCase_whenCallCreateRoleAndValidate_shouldReceiveRoleUpdated()
	    throws InterruptedException {
	// given
	final String expectedAuthority = "MODERATOR";
	final var lowercaseAuthority = "moderator";
	// when

	final var actualRole = Role.newRole(lowercaseAuthority);

	// then
	Assertions.assertNotNull(actualRole);
	Assertions.assertNotNull(actualRole.getId());
	Assertions.assertEquals(expectedAuthority.toUpperCase(),
		actualRole.getAuthority());
    }

    @Test
    void givenInvalidNullAuthority_whenCallNewRoleAndValidate_shouldReceiveAError() {
	// given
	final String expectedAuthority = null;
	final var expectedErrorCount = 3;
	final var expectedErrorMessage = "'authority' should not be null";
	final var expectedErrorMessage2 = "'authority' should not be empty";
	final var expectedErrorMessage3 = "'authority' must be between 4 and 20 characters";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class, () -> {
		    Role.newRole(expectedAuthority);
		});

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
	Assertions.assertEquals(expectedErrorMessage3,
		actualException.getErrors().get(2).message());
    }

    @Test
    void givenInvalidEmptyAuthority_whenCallNewRoleAndValidate_shouldReceiveAError() {
	// given
	final String expectedAuthority = "";
	final var expectedErrorCount = 2;
	final var expectedErrorMessage = "'authority' should not be empty";
	final var expectedErrorMessage2 = "'authority' must be between 4 and 20 characters";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class, () -> {
		    Role.newRole(expectedAuthority);
		});

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
    }

    @Test
    void givenInvalidAuthorityWithLenghtGreaterThan20_whenCallNewRoleAndValidate_shouldReceiveAError() {
	// given
	final String expectedAuthority = Fixture.text(50);
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'authority' must be between 4 and 20 characters";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class, () -> {
		    Role.newRole(expectedAuthority);
		});

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenInvalidAuthorityWithLenghtLessThan4_whenCallNewRoleAndValidate_shouldReceiveAError() {
	// given
	final String expectedAuthority = Fixture.text(3);
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'authority' must be between 4 and 20 characters";

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class, () -> {
		    Role.newRole(expectedAuthority);
		});

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    void givenAValidRole_whenCallUpdateRoleAndValidate_shouldReceiveRoleUpdated()
	    throws InterruptedException {
	// given
	final var expectedAuthority = "moderador";

	final var aRole = Fixture.Roles.guest();
	final var updated = aRole.getUpdatedAt();
	// when
	Thread.sleep(100);
	final var updatedRole = aRole.update(expectedAuthority);

	// then
	Assertions.assertEquals(aRole.getId(), updatedRole.getId());
	Assertions.assertEquals(expectedAuthority.toUpperCase(),
		updatedRole.getAuthority());
	Assertions.assertTrue(
		updated.isBefore(updatedRole.getUpdatedAt()));
	Assertions.assertEquals(aRole.getCreatedAt(),
		updatedRole.getCreatedAt());
    }

    @Test
    void givenAInvalidNullRole_whenCallUpdateRoleAndValidate_shouldReceiveRoleUpdated()
	    throws InterruptedException {
	// given
	final String expectedAuthority = null;
	final var expectedErrorCount = 3;
	final var expectedErrorMessage = "'authority' should not be null";
	final var expectedErrorMessage2 = "'authority' should not be empty";
	final var expectedErrorMessage3 = "'authority' must be between 4 and 20 characters";
	final var aRole = Fixture.Roles.member();

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class, () -> {
		    aRole.update(expectedAuthority);
		});

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
	Assertions.assertEquals(expectedErrorMessage3,
		actualException.getErrors().get(2).message());
    }

    @Test
    void givenAInvalidEmptyRole_whenCallUpdateRoleAndValidate_shouldReceiveRoleUpdated()
	    throws InterruptedException {
	// given
	final String expectedAuthority = "";
	final var expectedErrorCount = 2;
	final var expectedErrorMessage = "'authority' should not be empty";
	final var expectedErrorMessage2 = "'authority' must be between 4 and 20 characters";
	final var aRole = Fixture.Roles.member();

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class, () -> {
		    aRole.update(expectedAuthority);
		});

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
	Assertions.assertEquals(expectedErrorMessage2,
		actualException.getErrors().get(1).message());
    }

    @Test
    void givenAInvalidRoleWithLengthGreaterThan20_whenCallUpdateRoleAndValidate_shouldReceiveRoleUpdated()
	    throws InterruptedException {
	// given
	final String expectedAuthority = Fixture.text(21);
	final var expectedErrorCount = 1;
	final var expectedErrorMessage = "'authority' must be between 4 and 20 characters";
	final var aRole = Fixture.Roles.member();

	// when
	final var actualException = Assertions
		.assertThrows(NotificationException.class, () -> {
		    aRole.update(expectedAuthority);
		});

	// then
	Assertions.assertEquals(expectedErrorCount,
		actualException.getErrors().size());
	Assertions.assertEquals(expectedErrorMessage,
		actualException.firstError().message());
    }

    @Test
    public void givenAValidRoleWithAuthorityInLowerCase_whenCallUpdateRoleAndValidate_shouldReceiveRoleUpdated()
	    throws InterruptedException {
	// given
	final String expectedAuthority = "MODERATOR";
	final var lowercaseAuthority = "moderator";
	final var aRole = Role.newRole("associado");

	final var updatedAt = aRole.getUpdatedAt();

	// when
	Thread.sleep(100);
	final var updatedRole = aRole.update(lowercaseAuthority);

	// then
	Assertions.assertEquals(aRole.getId(), updatedRole.getId());
	Assertions.assertEquals(expectedAuthority.toUpperCase(),
		updatedRole.getAuthority());
	Assertions.assertTrue(
		updatedAt.isBefore(updatedRole.getUpdatedAt()));
	Assertions.assertEquals(aRole.getCreatedAt(),
		updatedRole.getCreatedAt());
    }

}
