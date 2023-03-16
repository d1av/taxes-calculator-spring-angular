package com.taxes.calculator.infrastructure.api.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.calculator.application.role.create.CreateRoleCommand;
import com.taxes.calculator.application.fixedtax.create.CreateFixedTaxCommand;
import com.taxes.calculator.application.fixedtax.create.CreateFixedTaxUseCase;
import com.taxes.calculator.application.fixedtax.delete.DeleteFixedTaxUseCase;
import com.taxes.calculator.application.fixedtax.retrieve.get.GetFixedTaxByIdUseCase;
import com.taxes.calculator.application.fixedtax.retrieve.list.ListFixedTaxUseCase;
import com.taxes.calculator.application.fixedtax.update.UpdateFixedTaxCommand;
import com.taxes.calculator.application.fixedtax.update.UpdateFixedTaxUseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.infrastructure.api.FixedTaxAPI;
import com.taxes.calculator.infrastructure.fixedtax.models.CreateFixedTaxRequest;
import com.taxes.calculator.infrastructure.fixedtax.models.FixedTaxListResponse;
import com.taxes.calculator.infrastructure.fixedtax.models.FixedTaxResponse;
import com.taxes.calculator.infrastructure.fixedtax.models.UpdateFixedTaxRequest;

@RestController
public class FixedTaxController implements FixedTaxAPI {

    private final CreateFixedTaxUseCase createFixedTaxUseCase;
    private final UpdateFixedTaxUseCase updateFixedTaxUseCase;
    private final DeleteFixedTaxUseCase deleteFixedTaxUseCase;
    private final GetFixedTaxByIdUseCase getFixedTaxByIdUseCase;
    private final ListFixedTaxUseCase listFixedTaxUseCase;

    public FixedTaxController(
	    final CreateFixedTaxUseCase createFixedTaxUseCase,
	    final UpdateFixedTaxUseCase updateFixedTaxUseCase,
	    final DeleteFixedTaxUseCase deleteFixedTaxUseCase,
	    final GetFixedTaxByIdUseCase getFixedTaxByIdUseCase,
	    final ListFixedTaxUseCase listFixedTaxUseCase) {
	this.createFixedTaxUseCase = Objects
		.requireNonNull(createFixedTaxUseCase);
	this.updateFixedTaxUseCase = Objects
		.requireNonNull(updateFixedTaxUseCase);
	this.deleteFixedTaxUseCase = Objects
		.requireNonNull(deleteFixedTaxUseCase);
	this.getFixedTaxByIdUseCase = Objects
		.requireNonNull(getFixedTaxByIdUseCase);
	this.listFixedTaxUseCase = Objects
		.requireNonNull(listFixedTaxUseCase);
    }

    @CacheEvict(value = {"fixedTaxGetById","calculateHourValue"}, allEntries = true)
    @Override
    public ResponseEntity<?> createFixedTax(
	    @Valid CreateFixedTaxRequest input)
	    throws URISyntaxException {
	final var userId = UserID.from(input.userId());

	final var aCommand = CreateFixedTaxCommand.with(
		input.regionalCouncil(), input.taxOverWork(),
		input.incomeTax(), input.accountant(),
		input.dentalShop(), input.transport(),
		input.food(), input.education(),
		input.otherFixedCosts(), userId);

	final var output = this.createFixedTaxUseCase
		.execute(aCommand);

	return ResponseEntity
		.created(
			new URI("/api/fixedtaxes" + output.id()))
		.body(output);
    }

    @Override
    public Pagination<FixedTaxListResponse> listFixedTaxs(
	    String search, int page, int perPage, String sort,
	    String direction) {
	return this.listFixedTaxUseCase
		.execute(new SearchQuery(page, perPage, search,
			sort, direction))
		.map(FixedTaxListResponse::present);
    }

    @Cacheable(value = "fixedTaxGetById")
    @Override
    public ResponseEntity<?> getById(String id) {
	final var output = this.getFixedTaxByIdUseCase
		.execute(id);
	return ResponseEntity.ok().body(output);
    }

    @CacheEvict(value = {"fixedTaxGetById","calculateHourValue"}, allEntries = true)
    @Override
    public ResponseEntity<?> updateById(String id,
	    UpdateFixedTaxRequest input) {
	final var aCommand = UpdateFixedTaxCommand.with(id,
		input.regionalCouncil(), input.taxOverWork(),
		input.incomeTax(), input.accountant(),
		input.dentalShop(), input.transport(),
		input.food(), input.education(),
		input.otherFixedCosts(),
		UserID.from(input.userId()));

	final var output = this.updateFixedTaxUseCase
		.execute(aCommand);

	return ResponseEntity.ok()
		.body(FixedTaxResponse.from(output));
    }

    @CacheEvict(value = {"fixedTaxGetById","calculateHourValue"}, allEntries = true)
    @Override
    public ResponseEntity<?> deleteById(String id) {
	this.deleteFixedTaxUseCase.execute(id);
	return ResponseEntity.noContent().build();
    }

}
