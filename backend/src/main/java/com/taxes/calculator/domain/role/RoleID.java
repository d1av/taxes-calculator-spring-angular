package com.taxes.calculator.domain.role;

import com.taxes.calculator.domain.Identifier;
import com.taxes.calculator.domain.utils.IdUtils;

import java.util.Objects;
import java.util.Optional;

public class RoleID extends Identifier {
    private final String value;

    public RoleID(final String value) {
	this.value = value;
    }

    public static RoleID unique() {
	return new RoleID(IdUtils.uuid());
    }

    public static RoleID from(final String anId) {
	return new RoleID(anId);
    }

    @Override
    public String getValue() {
	return value;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (!(o instanceof RoleID userID))
	    return false;
	return Objects.equals(getValue(), userID.getValue());
    }

    @Override
    public int hashCode() {
	return Objects.hash(getValue());
    }
}
