package com.taxes.calculator.domain.role;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.validation.handler.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void givenValidParams_whenCallNewRole_shouldInstantiateARole() {
        // given
        final var expectedAuthority = Fixture.Roles.guest().getAuthority();

        // when
        final var actualRole = Role.newRole(expectedAuthority);

        // then
        Assertions.assertNotNull(actualRole);
        Assertions.assertNotNull(actualRole.getId());
        Assertions.assertEquals(expectedAuthority, actualRole.getAuthority());
    }

    @Test
    public void givenInvalidNullAuthority_whenCallNewRoleAndValidate_shouldReceiveAError() {
        // given
        final String expectedAuthority = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'authority' should not be null";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class, () -> {
                    Role.newRole(expectedAuthority);
                });

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.firstError().message());
    }

    @Test
    public void givenInvalidEmptyAuthority_whenCallNewRoleAndValidate_shouldReceiveAError() {
        // given
        final String expectedAuthority = "";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'authority' should not be empty";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class, () -> {
                    Role.newRole(expectedAuthority);
                });

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.firstError().message());
    }

    @Test
    public void givenInvalidAuthorityWithLenghtGreaterThan20_whenCallNewRoleAndValidate_shouldReceiveAError() {
        // given
        final String expectedAuthority = Fixture.text(50);
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'authority' must be between 4 and 20 characters";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class, () -> {
                    Role.newRole(expectedAuthority);
                });

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.firstError().message());
    }

    @Test
    public void givenInvalidAuthorityWithLenghtLessThan4_whenCallNewRoleAndValidate_shouldReceiveAError() {
        // given
        final String expectedAuthority = Fixture.text(3);
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'authority' must be between 4 and 20 characters";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class, () -> {
                    Role.newRole(expectedAuthority);
                });

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.firstError().message());
    }


}
