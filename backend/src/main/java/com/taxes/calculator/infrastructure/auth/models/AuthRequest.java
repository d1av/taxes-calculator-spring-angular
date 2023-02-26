package com.taxes.calculator.infrastructure.auth.models;

public record AuthRequest(String name, String password) {
    public static AuthRequest with(String name, String password) {
	return new AuthRequest(name, password);
    }
}
