package com.taxes.calculator.infrastructure.api.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.calculator.infrastructure.api.AuthAPI;
import com.taxes.calculator.infrastructure.auth.AuthMySQLGateway;
import com.taxes.calculator.infrastructure.auth.models.AuthOutput;
import com.taxes.calculator.infrastructure.auth.models.AuthRequest;
import com.taxes.calculator.infrastructure.auth.models.AuthResponse;
import com.taxes.calculator.infrastructure.auth.models.RegisterOutput;
import com.taxes.calculator.infrastructure.auth.models.RegisterRequest;
import com.taxes.calculator.infrastructure.auth.models.RegisterResponse;

@RestController
public class AuthController implements AuthAPI {

    private AuthMySQLGateway authService;

    public AuthController(AuthMySQLGateway authService) {
	this.authService = authService;
    }

    @Override
    public ResponseEntity<AuthResponse> login(AuthRequest input) {

	AuthOutput output = authService.login(input);

	return ResponseEntity.ok().body(AuthResponse.with(output));
    }

    @Override
    public ResponseEntity<RegisterResponse> register(
	    RegisterRequest request) {

	authService.checkIfUserExists(request.name());

	final RegisterOutput output = authService.register(request);

	final URI uri = URI.create("/api/users/" + output.id());

	return ResponseEntity.created(uri)
		.body(RegisterResponse.from(output));
    }

}
