package com.taxes.calculator.infrastructure.auth.models;

import java.util.List;

public record AuthResponse(String token, List<String> roles) {
    public static AuthResponse with(String token, List<String> roles) {
	return new AuthResponse(token, roles);
    }
    public static AuthResponse with(AuthOutput output) {
	return new AuthResponse(output.token(), output.roles());
    }
}
