package com.taxes.calculator.domain.totaltax;

import java.util.Objects;

import com.taxes.calculator.domain.Identifier;
import com.taxes.calculator.domain.utils.IdUtils;

public class TotalTaxID extends Identifier {

    private final String value;

    @Override
    public String getValue() {
	return value;
    }

    public TotalTaxID(final String value) {
	this.value = value;
    }

    public static TotalTaxID unique() {
	return new TotalTaxID(IdUtils.uuid());
    }

    public static TotalTaxID from(final String anId) {
	return new TotalTaxID(anId);
    }

    @Override
    public int hashCode() {
	return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	TotalTaxID other = (TotalTaxID) obj;
	return Objects.equals(value, other.value);
    }

}
