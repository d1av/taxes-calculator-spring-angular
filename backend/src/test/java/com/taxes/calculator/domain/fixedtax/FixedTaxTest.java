package com.taxes.calculator.domain.fixedtax;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.taxes.calculator.Fixture;
import com.taxes.calculator.domain.user.User;

class FixedTaxTest {

    @Test
    public void givenAValidFixedTax_whenCreateNewFixedTax_shouldReturnFixedTax() {
	// given
	final BigDecimal expectedRegionalCouncil = Fixture.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture.bigDecimal(4);
	final User expectedUser = Fixture.Users.asa();

	// when
	final var actualTax = FixedTax.with(expectedRegionalCouncil,
		expectedTaxOverWork, expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts, expectedUser);

	// then

	Assertions.assertNotNull(actualTax);
	Assertions.assertNotNull(actualTax.getId());
	Assertions.assertEquals(expectedRegionalCouncil,
		actualTax.getRegionalCouncil());
	Assertions.assertEquals(expectedTaxOverWork,
		actualTax.getTaxOverWork());
	Assertions.assertEquals(expectedIncomeTax, actualTax.getIncomeTax());
	Assertions.assertEquals(expectedAccountant, actualTax.getAccountant());
	Assertions.assertEquals(expectedDentalShop, actualTax.getDentalShop());
	Assertions.assertEquals(expectedTransport, actualTax.getTransport());
	Assertions.assertEquals(expectedFood, actualTax.getFood());
	Assertions.assertEquals(expectedEducation, actualTax.getEducation());
	Assertions.assertEquals(expectedOtherFixedCosts,
		actualTax.getOtherFixedCosts());
	Assertions.assertEquals(expectedUser.getName(),
		actualTax.getUser().getName());
	Assertions.assertNotNull(actualTax.getCreatedAt());
	Assertions.assertNotNull(actualTax.getUpdatedAt());
    }

    @Test
    public void givenAValidFixedTaxWithNullUser_whenCreateNewFixedTax_shouldReturnFixedTax() {
	// given
	final BigDecimal expectedRegionalCouncil = Fixture.bigDecimal(4);
	final BigDecimal expectedTaxOverWork = Fixture.bigDecimal(4);
	final BigDecimal expectedIncomeTax = Fixture.bigDecimal(4);
	final BigDecimal expectedAccountant = Fixture.bigDecimal(4);
	final BigDecimal expectedDentalShop = Fixture.bigDecimal(4);
	final BigDecimal expectedTransport = Fixture.bigDecimal(4);
	final BigDecimal expectedFood = Fixture.bigDecimal(4);
	final BigDecimal expectedEducation = Fixture.bigDecimal(4);
	final BigDecimal expectedOtherFixedCosts = Fixture.bigDecimal(4);
	final User expectedUser = null;

	// when
	final var actualTax = FixedTax.with(expectedRegionalCouncil,
		expectedTaxOverWork, expectedIncomeTax, expectedAccountant,
		expectedDentalShop, expectedTransport, expectedFood,
		expectedEducation, expectedOtherFixedCosts, expectedUser);

	// then

	Assertions.assertNotNull(actualTax);
	Assertions.assertNotNull(actualTax.getId());
	Assertions.assertEquals(expectedRegionalCouncil,
		actualTax.getRegionalCouncil());
	Assertions.assertEquals(expectedTaxOverWork,
		actualTax.getTaxOverWork());
	Assertions.assertEquals(expectedIncomeTax, actualTax.getIncomeTax());
	Assertions.assertEquals(expectedAccountant, actualTax.getAccountant());
	Assertions.assertEquals(expectedDentalShop, actualTax.getDentalShop());
	Assertions.assertEquals(expectedTransport, actualTax.getTransport());
	Assertions.assertEquals(expectedFood, actualTax.getFood());
	Assertions.assertEquals(expectedEducation, actualTax.getEducation());
	Assertions.assertEquals(expectedOtherFixedCosts,
		actualTax.getOtherFixedCosts());
	Assertions.assertEquals(expectedUser, actualTax.getUser());
	Assertions.assertNotNull(actualTax.getCreatedAt());
	Assertions.assertNotNull(actualTax.getUpdatedAt());
    }

}
