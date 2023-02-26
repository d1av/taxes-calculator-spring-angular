package com.taxes.calculator.infrastructure.auth.models;

import java.util.List;

public record AuthOutput(String token, List<String> roles) {
    public static AuthOutput with(String token, List<String> roles) {
	return new AuthOutput(token, roles);
    }
}
