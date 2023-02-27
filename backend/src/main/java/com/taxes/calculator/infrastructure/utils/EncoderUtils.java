package com.taxes.calculator.infrastructure.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public final class EncoderUtils {

    private static BCryptPasswordEncoder passwordEncoder;

    public EncoderUtils(BCryptPasswordEncoder passwordEncoder) {
	this.passwordEncoder = passwordEncoder;
    }

    public static String encode(String unencodedPassword) {
	return passwordEncoder.encode(unencodedPassword);
    }
}
