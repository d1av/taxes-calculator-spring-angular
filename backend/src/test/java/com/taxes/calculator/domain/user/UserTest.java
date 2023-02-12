package com.taxes.calculator.domain.user;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.role.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class UserTest {

    @Test
    public void givenAValidUser_whenCallsCreateUser_shouldCreateAndReturnIt() {
        // given
        final String expectedName = Fixture.name();
        final String expectedPassword = Fixture.password(8);
        final Set<Role> expectedRoles = Set.of(Fixture.Roles.member());
        final Boolean expectedActive = true;

        // when
        final var actualUser = User.with(
                expectedName,
                expectedPassword,
                expectedActive,
                expectedRoles);

        final var copiedUser = User.with(actualUser);

        // then
        Assertions.assertNotNull(actualUser);
        Assertions.assertNotNull(actualUser.getId());
        Assertions.assertNotNull(actualUser.getPassword());
        Assertions.assertNotNull(actualUser.getRoles());
        Assertions.assertEquals(expectedName, actualUser.getName());
        Assertions.assertTrue(actualUser.getRoles().containsAll(expectedRoles));
        Assertions.assertEquals(expectedActive, actualUser.getActive());

        Assertions.assertEquals(actualUser.getId().getValue(), copiedUser.getId().getValue());
    }

    @Test
    public void givenAInvalidNullName_whenCallsCreateUser_shouldReturnAnNotification() {
        // given
        final String expectedName = null;
        final String expectedPassword = Fixture.password(8);
        final Set<Role> expectedRoles = Set.of(Fixture.Roles.member());
        final boolean expectedActive = true;

        final var expectedErrorCount = 1;
        final var expectedErrorMesage = "'name' should not be null";

        // when
        final var actualException =
                Assertions.assertThrows(
                        NotificationException.class, () -> User.with(
                                expectedName,
                                expectedPassword,
                                expectedActive,
                                expectedRoles)
                );

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMesage, actualException.firstError().message());
    }
    
    
    @Test
    public void givenAInvalidEmptyName_whenCallsCreateUser_shouldReturnAnNotification() {
    	// given
    	final String expectedName = "";
    	final String expectedPassword = Fixture.password(8);
    	final Set<Role> expectedRoles = Set.of(Fixture.Roles.member());
    	final boolean expectedActive = true;
    	
    	final var expectedErrorCount = 1;
    	final var expectedErrorMesage = "'name' should not be empty";
    	
    	// when
    	final var actualException =
    			Assertions.assertThrows(
    					NotificationException.class, () -> User.with(
    							expectedName,
    							expectedPassword,
    							expectedActive,
    							expectedRoles)
    					);
    	
    	// then
    	Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    	Assertions.assertEquals(expectedErrorMesage, actualException.firstError().message());
    }
    
    @Test
    public void givenAInvalidBlankName_whenCallsCreateUser_shouldReturnAnNotification() {
    	// given
    	final String expectedName = "    ";
    	final String expectedPassword = Fixture.password(8);
    	final Set<Role> expectedRoles = Set.of(Fixture.Roles.member());
    	final boolean expectedActive = true;
    	
    	final var expectedErrorCount = 1;
    	final var expectedErrorMesage = "'name' should not be empty";
    	
    	// when
    	final var actualException =
    			Assertions.assertThrows(
    					NotificationException.class, () -> User.with(
    							expectedName,
    							expectedPassword,
    							expectedActive,
    							expectedRoles)
    					);
    	
    	// then
    	Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    	Assertions.assertEquals(expectedErrorMesage, actualException.firstError().message());
    }
    
    @Test
    public void givenAInvalidNullPassword_whenCallsCreateUser_shouldReturnAnNotification() {
        // given
        final String expectedName = Fixture.name();
        final String expectedPassword = null;
        final Set<Role> expectedRoles = Set.of(Fixture.Roles.member());
        final boolean expectedActive = true;

        final var expectedErrorCount = 1;
        final var expectedErrorMesage = "'password' should not be null";

        // when
        final var actualException =
                Assertions.assertThrows(
                        NotificationException.class, () -> User.with(
                                expectedName,
                                expectedPassword,
                                expectedActive,
                                expectedRoles)
                );

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMesage, actualException.firstError().message());
    }
    
    
    @Test
    public void givenAInvalidEmptyPassword_whenCallsCreateUser_shouldReturnAnNotification() {
    	// given
    	final String expectedName = Fixture.name();
    	final String expectedPassword = Fixture.password(22);
    	final Set<Role> expectedRoles = Set.of(Fixture.Roles.member());
    	final boolean expectedActive = true;
    	
    	final var expectedErrorCount = 1;
    	final var expectedErrorMesage = "'password' must be between 6 and 20 characters";
    	
    	// when
    	final var actualException =
    			Assertions.assertThrows(
    					NotificationException.class, () -> User.with(
    							expectedName,
    							expectedPassword,
    							expectedActive,
    							expectedRoles)
    					);
    	
    	// then
    	Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    	Assertions.assertEquals(expectedErrorMesage, actualException.firstError().message());
    }
    
    @Test
    public void givenAInvalidBlankPassword_whenCallsCreateUser_shouldReturnAnNotification() {
    	// given
    	final String expectedName = Fixture.name();
    	final String expectedPassword = "   ";
    	final Set<Role> expectedRoles = Set.of(Fixture.Roles.member());
    	final boolean expectedActive = true;
    	
    	final var expectedErrorCount = 1;
    	final var expectedErrorMesage = "'password' should not be empty";
    	
    	// when
    	final var actualException =
    			Assertions.assertThrows(
    					NotificationException.class, () -> User.with(
    							expectedName,
    							expectedPassword,
    							expectedActive,
    							expectedRoles)
    					);
    	
    	// then
    	Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    	Assertions.assertEquals(expectedErrorMesage, actualException.firstError().message());
    }
}

