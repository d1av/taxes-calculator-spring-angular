package com.taxes.calculator.infrastructure.api.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.calculator.application.role.create.CreateRoleCommand;
import com.taxes.calculator.application.role.create.CreateRoleUseCase;
import com.taxes.calculator.application.role.delete.DeleteRoleUseCase;
import com.taxes.calculator.application.role.retrieve.get.GetRoleByIdUseCase;
import com.taxes.calculator.application.role.retrieve.list.ListRoleUseCase;
import com.taxes.calculator.application.role.update.UpdateRoleCommand;
import com.taxes.calculator.application.role.update.UpdateRoleUseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.infrastructure.api.RoleAPI;
import com.taxes.calculator.infrastructure.role.models.CreateRoleRequest;
import com.taxes.calculator.infrastructure.role.models.RoleListResponse;
import com.taxes.calculator.infrastructure.role.models.RoleResponse;
import com.taxes.calculator.infrastructure.role.models.UpdateRoleRequest;
import com.taxes.calculator.infrastructure.role.presenters.RoleApiPresenter;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class RoleController implements RoleAPI {

    private final CreateRoleUseCase createRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final DeleteRoleUseCase deleteRoleUseCase;
    private final GetRoleByIdUseCase getRoleByIdUseCase;
    private final ListRoleUseCase listRoleUseCase;

    public RoleController(final CreateRoleUseCase createRoleUseCase,
	    final UpdateRoleUseCase updateRoleUseCase,
	    final DeleteRoleUseCase deleteRoleUseCase,
	    final GetRoleByIdUseCase getRoleByIdUseCase,
	    final ListRoleUseCase listRoleUseCase) {
	this.createRoleUseCase = Objects
		.requireNonNull(createRoleUseCase);
	this.updateRoleUseCase = Objects
		.requireNonNull(updateRoleUseCase);
	this.deleteRoleUseCase = Objects
		.requireNonNull(deleteRoleUseCase);
	this.getRoleByIdUseCase = Objects
		.requireNonNull(getRoleByIdUseCase);
	this.listRoleUseCase = Objects
		.requireNonNull(listRoleUseCase);
    }

    @Override
    public ResponseEntity<?> createRole(
	    @Valid CreateRoleRequest input)
	    throws URISyntaxException {
	final var aCommand = CreateRoleCommand
		.with(input.authority());

	final var output = this.createRoleUseCase.execute(aCommand);

	return ResponseEntity
		.created(new URI("/api/roles" + output.id()))
		.body(output);
    }

    @Override
    public Pagination<RoleListResponse> listRoles(String search,
	    int page, int perPage, String sort, String direction) {

	return this.listRoleUseCase.execute(new SearchQuery(page,
		perPage, search, sort, direction))
		.map(RoleListResponse::present);
    }

    @Override
    public RoleResponse getById(String id) {
	return RoleApiPresenter
		.present(this.getRoleByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(String id,
	    UpdateRoleRequest input) {
	final var aCommand = UpdateRoleCommand.with(id,
		input.authority());

	final var output = this.updateRoleUseCase.execute(aCommand);

	return ResponseEntity.ok().body(output);
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
	this.deleteRoleUseCase.execute(id);

	return ResponseEntity.noContent().build();
    }

}
