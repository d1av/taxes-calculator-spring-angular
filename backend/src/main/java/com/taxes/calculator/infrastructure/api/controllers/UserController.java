package com.taxes.calculator.infrastructure.api.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.calculator.application.user.create.CreateUserCommand;
import com.taxes.calculator.application.user.create.CreateUserUseCase;
import com.taxes.calculator.application.user.delete.DeleteUserUseCase;
import com.taxes.calculator.application.user.retrieve.get.GetUserByIdUseCase;
import com.taxes.calculator.application.user.retrieve.list.ListUserUseCase;
import com.taxes.calculator.application.user.update.UpdateUserCommand;
import com.taxes.calculator.application.user.update.UpdateUserUseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.infrastructure.api.UserAPI;
import com.taxes.calculator.infrastructure.user.models.CreateUserRequest;
import com.taxes.calculator.infrastructure.user.models.UpdateUserRequest;
import com.taxes.calculator.infrastructure.user.models.UserListResponse;
import com.taxes.calculator.infrastructure.user.models.UserResponse;

@RestController
public class UserController implements UserAPI {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final ListUserUseCase listUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(final CreateUserUseCase createUserUseCase,
	    final UpdateUserUseCase updateUserUseCase,
	    final GetUserByIdUseCase getUserByIdUseCase,
	    final ListUserUseCase listUserUseCase,
	    final DeleteUserUseCase deleteUserUseCase) {
	this.createUserUseCase = Objects
		.requireNonNull(createUserUseCase);
	this.updateUserUseCase = Objects
		.requireNonNull(updateUserUseCase);
	this.getUserByIdUseCase = Objects
		.requireNonNull(getUserByIdUseCase);
	this.listUserUseCase = Objects
		.requireNonNull(listUserUseCase);
	this.deleteUserUseCase = Objects
		.requireNonNull(deleteUserUseCase);
    }

    @Override
    public ResponseEntity<?> createUser(
	    @Valid CreateUserRequest input)
	    throws URISyntaxException {

	final var aCommand = CreateUserCommand.with(input.name(),
		input.password(), input.active(), input.roles());

	final var output = this.createUserUseCase.execute(aCommand);

	return ResponseEntity
		.created(new URI("/api/users" + output.id()))
		.body(output);
    }

    @Override
    public Pagination<UserListResponse> listUsers(String search,
	    int page, int perPage, String sort, String direction) {
	return this.listUserUseCase.execute(new SearchQuery(page,
		perPage, search, sort, direction))
		.map(UserListResponse::present);
    }

    @Override
    public ResponseEntity<UserResponse> getById(String id) {
	final var output = this.getUserByIdUseCase.execute(id);
	return ResponseEntity.ok().body(UserResponse.from(output));
    }

    @Override
    public ResponseEntity<?> updateById(String id,
	    UpdateUserRequest input) {
	final var aCommand = UpdateUserCommand.with(id, input.name(),
		input.password(), input.active(),
		input.roles().stream().map(x -> RoleID.from(x))
			.collect(Collectors.toSet()));

	final var output = this.updateUserUseCase.execute(aCommand);

	return ResponseEntity.ok().body(output);
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
	this.deleteUserUseCase.execute(id);
	return ResponseEntity.noContent().build();
    }

}
