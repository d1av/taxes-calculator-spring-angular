package com.taxes.calculator.domain.role;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    ADMIN("ADMINISTRATOR"),
    USER("USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static Optional<Role> of(final String label) {
        return Arrays.stream(Role.values())
                .filter(it -> it.role.equalsIgnoreCase(label))
                .findFirst();
    }
}
