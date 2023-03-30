package com.taxes.calculator.domain.user;

import com.taxes.calculator.domain.Identifier;
import com.taxes.calculator.domain.utils.IdUtils;

import java.io.Serializable;
import java.util.Objects;

public class UserID extends Identifier implements Serializable {
    
    private static final long serialVersionUID = 9123760973010940984L;
    
    private final String value;

    public UserID(final String value) {
        this.value = value;
    }

    public static UserID unique() {
        return new UserID(IdUtils.uuid());
    }

    public static UserID from(final String anId) {
        return new UserID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserID userID)) return false;
        return Objects.equals(getValue(), userID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
