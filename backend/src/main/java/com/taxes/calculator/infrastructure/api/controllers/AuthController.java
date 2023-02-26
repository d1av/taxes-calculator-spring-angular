package com.taxes.calculator.infrastructure.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.calculator.infrastructure.api.AuthAPI;
import com.taxes.calculator.infrastructure.auth.AuthMySQLGateway;
import com.taxes.calculator.infrastructure.auth.models.AuthRequest;
import com.taxes.calculator.infrastructure.auth.models.AuthResponse;

@RestController
public class AuthController implements AuthAPI {

    private AuthMySQLGateway authService;

    public AuthController(AuthMySQLGateway authService) {
        this.authService = authService;
    }
    
    @Override
    public ResponseEntity<AuthResponse> login(AuthRequest input) {

	        String token = authService.login(input);        
	                
	        return ResponseEntity.ok().body(AuthResponse.with(token));
    }



}
