package com.taxes.calculator.infrastructure.api.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.calculator.application.role.create.CreateRoleCommand;
import com.taxes.calculator.application.fixedtax.create.CreateFixedTaxCommand;
import com.taxes.calculator.application.fixedtax.create.CreateFixedTaxUseCase;
import com.taxes.calculator.application.fixedtax.delete.DeleteFixedTaxUseCase;
import com.taxes.calculator.application.fixedtax.retrieve.get.GetFixedTaxByIdUseCase;
import com.taxes.calculator.application.fixedtax.retrieve.list.ListFixedTaxUseCase;
import com.taxes.calculator.application.fixedtax.update.UpdateFixedTaxUseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.infrastructure.api.FixedTaxAPI;
import com.taxes.calculator.infrastructure.fixedtax.models.CreateFixedTaxRequest;
import com.taxes.calculator.infrastructure.fixedtax.models.FixedTaxListResponse;
import com.taxes.calculator.infrastructure.fixedtax.models.UpdateFixedTaxRequest;

@RestController
public class FixedTaxController implements FixedTaxAPI {

    private final CreateFixedTaxUseCase createFixedTaxUseCase;
    private final UpdateFixedTaxUseCase updateFixedTaxUseCase;
    private final DeleteFixedTaxUseCase deleteFixedTaxUseCase;
    private final GetFixedTaxByIdUseCase getRoleByIdUseCase;
    private final ListFixedTaxUseCase listFixedTaxUseCase;

    public FixedTaxController(
	    final CreateFixedTaxUseCase createFixedTaxUseCase,
	    final UpdateFixedTaxUseCase updateFixedTaxUseCase,
	    final DeleteFixedTaxUseCase deleteFixedTaxUseCase,
	    final GetFixedTaxByIdUseCase getRoleByIdUseCase,
	    final ListFixedTaxUseCase listFixedTaxUseCase) {
	this.createFixedTaxUseCase = Objects
		.requireNonNull(createFixedTaxUseCase);
	this.updateFixedTaxUseCase = Objects
		.requireNonNull(updateFixedTaxUseCase);
	this.deleteFixedTaxUseCase = Objects
		.requireNonNull(deleteFixedTaxUseCase);
	this.getRoleByIdUseCase = Objects
		.requireNonNull(getRoleByIdUseCase);
	this.listFixedTaxUseCase = Objects
		.requireNonNull(listFixedTaxUseCase);
    }

    @Override
    public ResponseEntity<?> createFixedTax(
	    @Valid CreateFixedTaxRequest input)
	    throws URISyntaxException {
	final var userId = UserID.from(input.userId());

	final var aCommand = CreateFixedTaxCommand.with(
		input.regionalCouncil(), input.taxOverWork(),
		input.incomeTax(), input.accountant(),
		input.dentalShop(), input.transport(), input.food(),
		input.education(), input.otherFixedCosts(), userId);

	final var output = this.createFixedTaxUseCase
		.execute(aCommand);

	return ResponseEntity
		.created(new URI("/api/fixedtaxes" + output.id()))
		.body(output);
    }

    @Override
    public Pagination<FixedTaxListResponse> listFixedTaxs(
	    String search, int page, int perPage, String sort,
	    String direction) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ResponseEntity<?> getById(String id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ResponseEntity<?> updateById(String id,
	    UpdateFixedTaxRequest input) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
	// TODO Auto-generated method stub
	return null;
    }

}
