package com.taxes.calculator.infrastructure.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping(value = "api/totaltaxes")
@Api(tags = { "TotalTax Controller" })
public interface TotalTaxAPI {

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a TotalTax by it's identifier")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "TotalTax retrieved successfully"),
	    @ApiResponse(responseCode = "404", description = "TotalTax was not found"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<?> getByUser(@PathVariable(name = "id") String id);

}