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
import com.taxes.calculator.infrastructure.hourvalue.models.CalculateHourValueRequest;
import com.taxes.calculator.infrastructure.hourvalue.models.CreateHourValueRequest;
import com.taxes.calculator.infrastructure.hourvalue.models.HourValueListResponse;
import com.taxes.calculator.infrastructure.hourvalue.models.UpdateHourValueRequest;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping(value = "api/hourvalues")
@Api(tags = { "HourValue Controller" })
public interface HourValueAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new HourValue")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Created successfully"),
	    @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<?> create(
	    @Valid @RequestBody CreateHourValueRequest input)
	    throws URISyntaxException;

    @GetMapping
    @Operation(summary = "List all HourValues paginated")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Listed successfully"),
	    @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    Pagination<HourValueListResponse> list(
	    @RequestParam(name = "search", required = false, defaultValue = "") final String search,
	    @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
	    @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
	    @RequestParam(name = "sort", required = false, defaultValue = "id") final String sort,
	    @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction);

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a HourValue by it's identifier")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "HourValue retrieved successfully"),
	    @ApiResponse(responseCode = "404", description = "HourValue was not found"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<?> getById(@PathVariable(name = "id") String id);

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a HourValue by it's identifier")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "HourValue updated successfully"),
	    @ApiResponse(responseCode = "404", description = "HourValue was not found"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<?> updateById(@PathVariable(name = "id") String id,
	    @RequestBody UpdateHourValueRequest input);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a HourValue by it's identifier")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "204", description = "HourValue deleted successfully"),
	    @ApiResponse(responseCode = "404", description = "HourValue was not found"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<?> deleteById(
	    @PathVariable(name = "id") String id);
    
    @PostMapping(value = "calculate",
	    consumes = MediaType.APPLICATION_JSON_VALUE,
	    produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new HourValue")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Created successfully"),
	    @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<?> calculateHourValue(
	    @Valid @RequestBody CalculateHourValueRequest input);
}