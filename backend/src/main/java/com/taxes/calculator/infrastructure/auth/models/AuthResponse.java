package com.taxes.calculator.infrastructure.auth.models;

import java.util.List;

public record AuthResponse(String token, List<String> roles,
	String userId) {
    public static AuthResponse with(String token,
	    List<String> roles, String userId) {
	return new AuthResponse(token, roles, userId);
    }

    public static AuthResponse with(AuthOutput output) {
	return new AuthResponse(output.token(), output.roles(),
		output.userId());
    }
}
