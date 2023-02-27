package com.taxes.calculator.infrastructure.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taxes.calculator.infrastructure.auth.models.AuthRequest;
import com.taxes.calculator.infrastructure.auth.models.AuthResponse;
import com.taxes.calculator.infrastructure.auth.models.RegisterRequest;
import com.taxes.calculator.infrastructure.auth.models.RegisterResponse;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping(value = "api/auth")
@Api(tags = { "Auth Controller" })
public interface AuthAPI {

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Created successfully"),
	    @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest input);

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Created successfully"),
	    @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<RegisterResponse> register(
	    @RequestBody RegisterRequest request);
}