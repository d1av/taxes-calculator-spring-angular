package com.taxes.calculator.domain.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class InstantUtils {
    private InstantUtils() {
    }

    // 9 casas = NANOSECONDS
    // 6 casa = MICROSECONDS
    public static Instant now() {
        return Instant.now().truncatedTo(ChronoUnit.MICROS);
    }
}
