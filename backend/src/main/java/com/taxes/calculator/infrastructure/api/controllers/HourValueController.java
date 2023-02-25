package com.taxes.calculator.infrastructure.api.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.calculator.application.hourvalue.calculate.CalculateHourValueCommand;
import com.taxes.calculator.application.hourvalue.calculate.CalculateHourValueUseCase;
import com.taxes.calculator.application.hourvalue.create.CreateHourValueCommand;
import com.taxes.calculator.application.hourvalue.create.CreateHourValueUseCase;
import com.taxes.calculator.application.hourvalue.delete.DeleteHourValueUseCase;
import com.taxes.calculator.application.hourvalue.retrieve.get.GetHourValueByIdUseCase;
import com.taxes.calculator.application.hourvalue.retrieve.list.ListHourValueUseCase;
import com.taxes.calculator.application.hourvalue.update.UpdateHourValueCommand;
import com.taxes.calculator.application.hourvalue.update.UpdateHourValueUseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.infrastructure.api.HourValueAPI;
import com.taxes.calculator.infrastructure.hourvalue.models.CalculateHourValueRequest;
import com.taxes.calculator.infrastructure.hourvalue.models.CreateHourValueRequest;
import com.taxes.calculator.infrastructure.hourvalue.models.HourValueListResponse;
import com.taxes.calculator.infrastructure.hourvalue.models.HourValueResponse;
import com.taxes.calculator.infrastructure.hourvalue.models.UpdateHourValueRequest;

@RestController
public class HourValueController implements HourValueAPI {

    private final CreateHourValueUseCase createHourValueUseCase;
    private final UpdateHourValueUseCase updateHourValueUseCase;
    private final GetHourValueByIdUseCase getHourValueByIdUseCase;
    private final ListHourValueUseCase listHourValueUseCase;
    private final DeleteHourValueUseCase deleteHourValueUseCase;
    private final CalculateHourValueUseCase calculateHourValueUseCase;

    public HourValueController(
	    final CalculateHourValueUseCase calculateHourValueUseCase,
	    final CreateHourValueUseCase createHourValueUseCase,
	    final UpdateHourValueUseCase updateHourValueUseCase,
	    final GetHourValueByIdUseCase getHourValueByIdUseCase,
	    final ListHourValueUseCase listHourValueUseCase,
	    final DeleteHourValueUseCase deleteHourValueUseCase) {
	this.createHourValueUseCase = Objects
		.requireNonNull(createHourValueUseCase);
	this.calculateHourValueUseCase = Objects
		.requireNonNull(calculateHourValueUseCase);
	this.updateHourValueUseCase = Objects
		.requireNonNull(updateHourValueUseCase);
	this.getHourValueByIdUseCase = Objects
		.requireNonNull(getHourValueByIdUseCase);
	this.listHourValueUseCase = Objects
		.requireNonNull(listHourValueUseCase);
	this.deleteHourValueUseCase = Objects
		.requireNonNull(deleteHourValueUseCase);
    }

    @Override
    public ResponseEntity<?> create(@Valid CreateHourValueRequest input)
	    throws URISyntaxException {
	final var aCommand = CreateHourValueCommand.with(
		input.expectedSalary(), input.personalHourValue(),
		input.daysOfWork(), input.user());

	final var output = createHourValueUseCase.execute(aCommand);

	URI uri = new URI("/api/hourvalues/" + output.id());

	return ResponseEntity.created(uri).body(output);
    }

    @Override
    public Pagination<HourValueListResponse> list(String search, int page,
	    int perPage, String sort, String direction) {
	return this.listHourValueUseCase.execute(
		new SearchQuery(page, perPage, search, sort, direction))
		.map(HourValueListResponse::present);
    }

    @Override
    public ResponseEntity<?> getById(String id) {
	final var output = getHourValueByIdUseCase.execute(id);
	return ResponseEntity.ok().body(HourValueResponse.from(output));
    }

    @Override
    public ResponseEntity<?> updateById(String id,
	    UpdateHourValueRequest input) {
	final var aCommand = UpdateHourValueCommand.with(id,
		input.expectedSalary(), input.personalHourValue(),
		input.daysOfWork(), input.user());

	final var output = updateHourValueUseCase.execute(aCommand);

	return ResponseEntity.ok().body(output);
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
	this.deleteHourValueUseCase.execute(id);
	return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> calculateHourValue(
	    @Valid CalculateHourValueRequest input) {
	final var aCommand = CalculateHourValueCommand.with(
		input.fixedTaxId(), input.variableTaxId(),
		input.hourValueId(), input.userId());

	final var output = calculateHourValueUseCase.execute(aCommand);

	return ResponseEntity.ok().body(output);
    }

}
