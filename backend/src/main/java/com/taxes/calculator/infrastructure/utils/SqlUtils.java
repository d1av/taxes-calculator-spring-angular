package com.taxes.calculator.infrastructure.utils;

import java.util.Objects;
import java.util.Set;

public final class SqlUtils {
    
    private SqlUtils() {
    }

    public static String like(final String term) {
        if (Objects.isNull(term)) return null;
        return "%" + term + "%";
    }

    public static String upper(final String term) {
        if (Objects.isNull(term)) return null;
        return term.toUpperCase();
    }

}