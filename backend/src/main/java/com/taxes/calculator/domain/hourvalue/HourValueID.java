package com.taxes.calculator.domain.hourvalue;

import java.util.Objects;

import com.taxes.calculator.domain.Identifier;
import com.taxes.calculator.domain.utils.IdUtils;

public class HourValueID extends Identifier{

    private final String value;

    public HourValueID(final String value) {
        this.value = value;
    }

    public static HourValueID unique() {
        return new HourValueID(IdUtils.uuid());
    }

    public static HourValueID from(final String anId) {
        return new HourValueID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HourValueID userID)) return false;
        return Objects.equals(getValue(), userID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

}
