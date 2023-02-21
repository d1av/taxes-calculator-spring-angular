package com.taxes.calculator.infrastructure.api.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.calculator.application.role.create.CreateRoleCommand;
import com.taxes.calculator.application.user.create.CreateUserCommand;
import com.taxes.calculator.application.user.create.CreateUserUseCase;
import com.taxes.calculator.application.user.delete.DeleteUserUseCase;
import com.taxes.calculator.application.user.retrieve.get.GetUserByIdUseCase;
import com.taxes.calculator.application.user.retrieve.list.ListUserUseCase;
import com.taxes.calculator.application.user.update.UpdateUserUseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.infrastructure.api.UserAPI;
import com.taxes.calculator.infrastructure.user.models.CreateUserRequest;
import com.taxes.calculator.infrastructure.user.models.UpdateUserRequest;
import com.taxes.calculator.infrastructure.user.models.UserListResponse;
import com.taxes.calculator.infrastructure.user.models.UserResponse;
import com.taxes.calculator.infrastructure.utils.SqlUtils;

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
		.created(new URI("/api/roles" + output.id()))
		.body(output);
    }

    @Override
    public Pagination<UserListResponse> listUsers(String search,
	    int page, int perPage, String sort, String direction) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public UserResponse getById(String id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ResponseEntity<?> updateById(String id,
	    UpdateUserRequest input) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
	// TODO Auto-generated method stub
	return null;
    }

}
