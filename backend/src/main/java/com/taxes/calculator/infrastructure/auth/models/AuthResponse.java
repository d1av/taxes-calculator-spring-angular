package com.taxes.calculator.infrastructure.auth.models;

public record AuthResponse(String token) {
    public static AuthResponse with(String token) {
	return new AuthResponse(token);
    }
}
