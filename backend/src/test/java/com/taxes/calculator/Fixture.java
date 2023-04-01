package com.taxes.calculator;

import java.math.BigDecimal;
import java.util.Set;

import com.taxes.calculator.domain.fixedtax.FixedTax;
import com.taxes.calculator.domain.hourvalue.HourValue;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.utils.InstantUtils;
import com.taxes.calculator.domain.variabletax.VariableTax;

import net.datafaker.Faker;

public class Fixture {
    private static Faker FAKER = new Faker();

    public static class Calculate {
	public static FixedTax fixed() {
	    return FixedTax.with(BigDecimal.valueOf(10),
		    BigDecimal.valueOf(10), BigDecimal.valueOf(10),
		    BigDecimal.valueOf(10), BigDecimal.valueOf(10),
		    BigDecimal.valueOf(10), BigDecimal.valueOf(10),
		    BigDecimal.valueOf(10), BigDecimal.valueOf(10),
		    nonVariableUser().getId());
	}

	public static VariableTax variable() {
	    return VariableTax.with(BigDecimal.valueOf(10),
		    BigDecimal.valueOf(10), BigDecimal.valueOf(10),
		    BigDecimal.valueOf(10), BigDecimal.valueOf(10),
		    nonVariableUser().getId());
	}

	public static HourValue hourValue() {
	    return HourValue
		    .newHourValue(BigDecimal.valueOf(10),
			    BigDecimal.valueOf(10), 20)
		    .addUser(nonVariableUser().getId());
	}

	public static User nonVariableUser() {
	    final var now = InstantUtils.now();
	    return User.with(UserID.from("123"), "Alipheese Fateburn", "miamiamia",
		    true, Set.of(), now, now, null);
	}
    }

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
	static User user= Fixture.Users.asa();
	public static VariableTax variable() {
	    return VariableTax.with(bigDecimal(4), bigDecimal(4),
		    bigDecimal(4), bigDecimal(4), bigDecimal(4),
		    user.getId());
	}
	public static VariableTax variable2() {
	    return VariableTax.with(bigDecimal(4), bigDecimal(4),
		    bigDecimal(4), bigDecimal(4), bigDecimal(4),
		    Fixture.Users.abella().getId());
	}
	
	
	public static User getUser() {
	    return user;
	}

	public static VariableTax variableNullUser() {
	    return VariableTax.with(bigDecimal(4), bigDecimal(4),
		    bigDecimal(4), bigDecimal(4), bigDecimal(4),
		    null);
	}
    }

    public static BigDecimal bigDecimal(int houses) {
	return BigDecimal.valueOf(FAKER.random().nextDouble(
		Math.pow(10, houses), Math.pow(10, houses)) - 100);
    }

    public static BigDecimal negativeBigDecimal(int houses) {
	return BigDecimal.valueOf(
		-FAKER.random().nextDouble(Math.pow(10, houses),
			Math.pow(10, houses)) - 100);
    }

    public static Integer daysOfWork() {
	return FAKER.random().nextInt(1, 31);
    }

    public static String name() {
	StringBuilder builder = new StringBuilder();
	while (builder.length() < 20) {
	    builder.append(FAKER.massEffect().character());
	}
	return builder.toString();
    }

    public static String password(int passwordLength) {
	passwordLength = Math.abs(passwordLength);
	if (passwordLength > 300)
	    passwordLength = 300;

	StringBuilder stringBuilder = new StringBuilder();

	for (int i = 0; i < passwordLength; i++) {
	    stringBuilder
		    .append(FAKER.aws().subnetId().substring(0, 1));
	}

	return stringBuilder.toString();
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
	    return Role.newRole("role_guest");
	}

	public static Role member() {
	    return Role.newRole("role_member");
	}

	public static String roleName() {
	    return "ROLE_"
		    + FAKER.onePiece().akumasNoMi().replace(" ", "");
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
