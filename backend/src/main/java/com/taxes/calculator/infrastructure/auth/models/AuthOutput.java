package com.taxes.calculator.infrastructure.auth.models;

import java.util.List;

public record AuthOutput(String token, List<String> roles,
	String userId) {
    public static AuthOutput with(String token,
	    List<String> roles, String userId) {
	return new AuthOutput(token, roles, userId);
    }
}
