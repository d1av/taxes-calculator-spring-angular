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
import com.taxes.calculator.infrastructure.role.models.CreateRoleRequest;
import com.taxes.calculator.infrastructure.role.models.RoleListResponse;
import com.taxes.calculator.infrastructure.role.models.RoleResponse;
import com.taxes.calculator.infrastructure.role.models.UpdateRoleRequest;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RequestMapping(value = "api/roles")
@Api(tags ={"Role Controller"})
public interface RoleAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new role")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Created successfully"),
	    @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<?> createRole(
	    @Valid @RequestBody CreateRoleRequest input)
	    throws URISyntaxException;

    @GetMapping
    @Operation(summary = "List all roles paginated")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Listed successfully"),
	    @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    Pagination<RoleListResponse> listRoles(
	    @RequestParam(name = "search", required = false, defaultValue = "") final String search,
	    @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
	    @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
	    @RequestParam(name = "sort", required = false, defaultValue = "authority") final String sort,
	    @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction);

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a role by it's identifier")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "role retrieved successfully"),
	    @ApiResponse(responseCode = "404", description = "role was not found"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    RoleResponse getById(@PathVariable(name = "id") String id);

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a role by it's identifier")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "role updated successfully"),
	    @ApiResponse(responseCode = "404", description = "role was not found"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<?> updateById(@PathVariable(name = "id") String id,
	    @RequestBody UpdateRoleRequest input);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a role by it's identifier")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "204", description = "role deleted successfully"),
	    @ApiResponse(responseCode = "404", description = "role was not found"),
	    @ApiResponse(responseCode = "500", description = "A internal server error was thrown") })
    ResponseEntity<?> deleteById(
	    @PathVariable(name = "id") String id);
}