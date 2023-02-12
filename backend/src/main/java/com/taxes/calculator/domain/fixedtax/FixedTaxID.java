package com.taxes.calculator.domain.fixedtax;

import java.util.Objects;

import com.taxes.calculator.domain.Identifier;
import com.taxes.calculator.domain.utils.IdUtils;

public class FixedTaxID extends Identifier {
	private final String value;

	private FixedTaxID(final String value) {
		this.value = value;
	}

	public static FixedTaxID unique() {
		return new FixedTaxID(IdUtils.uuid());
	}

	public static FixedTaxID from(final String anId) {
		return new FixedTaxID(anId);
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof FixedTaxID userID))
			return false;
		return Objects.equals(getValue(), userID.getValue());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getValue());
	}
}
