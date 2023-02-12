package com.taxes.calculator;

import static org.mockito.ArgumentMatchers.anyString;

import java.math.BigDecimal;

import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.user.User;

import net.datafaker.Faker;

public class Fixture {
    private static Faker FAKER = new Faker();

    public static BigDecimal bigDecimal(int houses) {
	return BigDecimal.valueOf(FAKER.random().nextInt(0, houses * 10));
    }

    public static String name() {
	return FAKER.massEffect().character();
    }

    public static String password(int passwordLength) {
	passwordLength = Math.abs(passwordLength);
	if (passwordLength > 100)
	    passwordLength = 100;
	return FAKER.aws().subnetId().substring(passwordLength);
    }

    public static String text(int number) {
	int newNumber = Math.abs(number);
	if (newNumber > 5000)
	    newNumber = 5000;
	String text = "";

	while (text.length() < newNumber) {
	    text = text.concat(FAKER.onePiece().quote())
		    .concat(FAKER.lordOfTheRings().character());
	}
	return text.substring(0, newNumber);
    }

    public static class Roles {
	public static Role guest() {
	    return Role.newRole("guest");
	}

	public static Role member() {
	    return Role.newRole("member");
	}
    }

    public static class Users {
	public static User asa() {
	    return User.newUser("Asa Akira", "asaakira", true);
	}

	public static User active() {
	    return User.newUser(FAKER.onePiece().character(),
		    FAKER.darkSoul().covenants(), true);
	}

	public static User inactive() {
	    return User.newUser(FAKER.onePiece().character(),
		    FAKER.darkSoul().covenants(), false);
	}
    }
}
