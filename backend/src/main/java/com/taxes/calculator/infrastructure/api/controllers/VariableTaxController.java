package com.taxes.calculator.infrastructure.api.controllers;

import java.net.URISyntaxException;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.calculator.application.variabletax.create.CreateVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.delete.DeleteVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.retrieve.get.GetVariableTaxByIdUseCase;
import com.taxes.calculator.application.variabletax.retrieve.list.ListVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.update.UpdateVariableTaxUseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.infrastructure.api.VariableTaxAPI;
import com.taxes.calculator.infrastructure.variabletax.models.CreateVariableTaxRequest;
import com.taxes.calculator.infrastructure.variabletax.models.UpdateVariableTaxRequest;
import com.taxes.calculator.infrastructure.variabletax.models.VariableTaxListResponse;

@RestController
public class VariableTaxController implements VariableTaxAPI {

    private final CreateVariableTaxUseCase createVariableTaxUseCase;
    private final UpdateVariableTaxUseCase updateVariableTaxUseCase;
    private final ListVariableTaxUseCase listVariableTaxUseCase;
    private final GetVariableTaxByIdUseCase getVariableTaxByIdUseCase;
    private final DeleteVariableTaxUseCase deleteVariableTaxUseCase;

    public VariableTaxController(
	    CreateVariableTaxUseCase createVariableTaxUseCase,
	    UpdateVariableTaxUseCase updateVariableTaxUseCase,
	    ListVariableTaxUseCase listVariableTaxUseCase,
	    GetVariableTaxByIdUseCase getVariableTaxByIdUseCase,
	    DeleteVariableTaxUseCase deleteVariableTaxUseCase) {
	this.createVariableTaxUseCase = Objects
		.requireNonNull(createVariableTaxUseCase);
	this.updateVariableTaxUseCase = Objects
		.requireNonNull(updateVariableTaxUseCase);
	this.listVariableTaxUseCase = Objects
		.requireNonNull(listVariableTaxUseCase);
	this.getVariableTaxByIdUseCase = Objects
		.requireNonNull(getVariableTaxByIdUseCase);
	this.deleteVariableTaxUseCase = Objects
		.requireNonNull(deleteVariableTaxUseCase);
    }

    @Override
    public ResponseEntity<?> createVariableTax(
	    @Valid CreateVariableTaxRequest input)
	    throws URISyntaxException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Pagination<VariableTaxListResponse> listVariableTaxs(
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
	    UpdateVariableTaxRequest input) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
	// TODO Auto-generated method stub
	return null;
    }

}
