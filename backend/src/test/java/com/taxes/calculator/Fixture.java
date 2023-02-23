package com.taxes.calculator;

import java.math.BigDecimal;

import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.variabletax.VariableTax;

import net.datafaker.Faker;

public class Fixture {
    private static Faker FAKER = new Faker();

    public static class Tax {
	public static FixedTax fixed() {
	    return FixedTax.with(bigDecimal(4), bigDecimal(4),
		    bigDecimal(4), bigDecimal(4), bigDecimal(4),
		    bigDecimal(4), bigDecimal(4), bigDecimal(4),
		    bigDecimal(4), Fixture.Users.asa().getId());
	}

	public static FixedTax fixedNullUser() {
	    return FixedTax.with(bigDecimal(4), bigDecimal(4),
		    bigDecimal(4), bigDecimal(4), bigDecimal(4),
		    bigDecimal(4), bigDecimal(4), bigDecimal(4),
		    bigDecimal(4), null);
	}

	public static VariableTax variable() {
	    return VariableTax.with(bigDecimal(4), bigDecimal(4),
		    bigDecimal(4), bigDecimal(4), bigDecimal(4),
		    Fixture.Users.asa().getId());
	}

	public static VariableTax variableNullUser() {
	    return VariableTax.with(bigDecimal(4), bigDecimal(4),
		    bigDecimal(4), bigDecimal(4), bigDecimal(4),
		    null);
	}
    }

    public static BigDecimal bigDecimal(int houses) {
	return BigDecimal
		.valueOf(FAKER.random().nextInt(0, houses * 10));
    }

    public static Integer daysOfWork() {
	return FAKER.random().nextInt(1, 31);
    }

    public static String name() {
	return FAKER.massEffect().character();
    }

    public static String password(int passwordLength) {
	passwordLength = Math.abs(passwordLength);
	if (passwordLength > 100)
	    passwordLength = 100;
	return FAKER.aws().subnetId().substring(0, passwordLength);
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

    public static class HourValues {
	public static HourValue valid() {
	    return HourValue.newHourValue(Fixture.bigDecimal(4),
		    Fixture.bigDecimal(4), Fixture.daysOfWork());
	}

	public static HourValue one() {
	    return HourValue.newHourValue(Fixture.bigDecimal(2),
		    Fixture.bigDecimal(5), Fixture.daysOfWork());
	}

	public static HourValue two() {
	    return HourValue.newHourValue(Fixture.bigDecimal(2),
		    Fixture.bigDecimal(10), Fixture.daysOfWork());
	}
    }

    public static class Roles {
	public static Role guest() {
	    return Role.newRole("guest");
	}

	public static Role member() {
	    return Role.newRole("member");
	}

	public static String roleName() {
	    return FAKER.onePiece().akumasNoMi().replace(" ", "");
	}
    }

    public static class Users {
	public static User asa() {
	    return User.newUser("Asa Akira", "asaakira", true);
	}

	public static User abella() {
	    return User.newUser("Abella Danger", "abelladanger",
		    true);
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
