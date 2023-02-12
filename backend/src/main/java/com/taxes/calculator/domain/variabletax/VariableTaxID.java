package com.taxes.calculator.domain.variabletax;

import java.util.Objects;

import com.taxes.calculator.domain.Identifier;
import com.taxes.calculator.domain.utils.IdUtils;

public class VariableTaxID extends Identifier {
	private final String value;

	private VariableTaxID(final String value) {
		this.value = value;
	}

	public static VariableTaxID unique() {
		return new VariableTaxID(IdUtils.uuid());
	}

	public static VariableTaxID from(final String anId) {
		return new VariableTaxID(anId);
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof VariableTaxID userID))
			return false;
		return Objects.equals(getValue(), userID.getValue());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getValue());
	}
}
