package com.taxes.calculator.infrastructure.api;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.infrastructure.variabletax.models.CreateVariableTaxRequest;
import com.taxes.calculator.infrastructure.variabletax.models.UpdateVariableTaxRequest;
import com.taxes.calculator.infrastructure.variabletax.models.VariableTaxListResponse;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping(value = "api/variabletaxes")
@Api(tags = { "VariableTax Controller" })
public interface VariableTaxAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new VariableTax")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Created successfully"),
	    @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<?> createVariableTax(
	    @Valid @RequestBody CreateVariableTaxRequest input)
	    throws URISyntaxException;

    @GetMapping
    @Operation(summary = "List all VariableTaxs paginated")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Listed successfully"),
	    @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    Pagination<VariableTaxListResponse> listVariableTaxs(
	    @RequestParam(name = "search", required = false, defaultValue = "") final String search,
	    @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
	    @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
	    @RequestParam(name = "sort", required = false, defaultValue = "id") final String sort,
	    @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction);

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a VariableTax by it's identifier")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "VariableTax retrieved successfully"),
	    @ApiResponse(responseCode = "404", description = "VariableTax was not found"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<?> getById(@PathVariable(name = "id") String id);

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a VariableTax by it's identifier")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "VariableTax updated successfully"),
	    @ApiResponse(responseCode = "404", description = "VariableTax was not found"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<?> updateById(@PathVariable(name = "id") String id,
	    @RequestBody UpdateVariableTaxRequest input);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a VariableTax by it's identifier")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "204", description = "VariableTax deleted successfully"),
	    @ApiResponse(responseCode = "404", description = "VariableTax was not found"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<?> deleteById(
	    @PathVariable(name = "id") String id);
}