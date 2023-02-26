package com.taxes.calculator.infrastructure.auth;

import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.taxes.calculator.domain.user.UserGateway;

public class AuthMySQLGateway {

    private final UserGateway userGateway;

    private final BCryptPasswordEncoder passwordEncoder;

    public AuthMySQLGateway(final UserGateway userGateway,
	    final BCryptPasswordEncoder passwordEncoder) {
	this.userGateway = Objects.requireNonNull(userGateway);
	this.passwordEncoder = Objects.requireNonNull(passwordEncoder);
    }
    
    

    

}
