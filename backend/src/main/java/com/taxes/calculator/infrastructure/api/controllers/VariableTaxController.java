package com.taxes.calculator.infrastructure.api.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.calculator.application.variabletax.create.CreateVariableTaxCommand;
import com.taxes.calculator.application.variabletax.create.CreateVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.delete.DeleteVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.retrieve.get.GetVariableTaxByIdUseCase;
import com.taxes.calculator.application.variabletax.retrieve.list.ListVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.update.UpdateVariableTaxCommand;
import com.taxes.calculator.application.variabletax.update.UpdateVariableTaxUseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
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

    @CacheEvict(value = "VariableTaxGetById", allEntries = true)
    @Override
    public ResponseEntity<?> createVariableTax(
	    @Valid CreateVariableTaxRequest input)
	    throws URISyntaxException {
	final var aCommand = CreateVariableTaxCommand.with(
		input.dentalShop(), input.prosthetist(),
		input.travel(), input.creditCard(),
		input.weekend(), input.userId());

	final var output = createVariableTaxUseCase
		.execute(aCommand);

	final URI uri = new URI(
		"api/variabletaxes/" + output.id());

	return ResponseEntity.created(uri).body(output);
    }

    @Override
    public ResponseEntity<Pagination<VariableTaxListResponse>> list(
	    String search, int page, int perPage, String sort,
	    String direction) {
	final var output = listVariableTaxUseCase
		.execute(new SearchQuery(page, perPage, search,
			sort, direction));

	return ResponseEntity.ok().body(
		output.map(VariableTaxListResponse::present));
    }

    @Cacheable(value = "VariableTaxGetById")
    @Override
    public ResponseEntity<?> getById(String id) {

	final var output = getVariableTaxByIdUseCase.execute(id);

	return ResponseEntity.ok().body(output);
    }

    @CacheEvict(value = "VariableTaxGetById", allEntries = true)
    @Override
    public ResponseEntity<?> updateById(String id,
	    UpdateVariableTaxRequest input) {
	final var aCommand = UpdateVariableTaxCommand.with(id,
		input.dentalShop(), input.prosthetist(),
		input.travel(), input.creditCard(),
		input.weekend(), input.userId());

	final var output = updateVariableTaxUseCase
		.execute(aCommand);

	return ResponseEntity.ok().body(output);
    }

    @CacheEvict(value = "VariableTaxGetById", allEntries = true)
    @Override
    public ResponseEntity<?> deleteById(String id) {
	this.deleteVariableTaxUseCase.execute(id);
	return ResponseEntity.noContent().build();
    }

}
