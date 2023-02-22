package com.taxes.calculator.infrastructure.api.controllers;

import java.net.URISyntaxException;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.calculator.application.hourvalue.create.CreateHourValueUseCase;
import com.taxes.calculator.application.hourvalue.delete.DeleteHourValueUseCase;
import com.taxes.calculator.application.hourvalue.retrieve.get.GetHourValueByIdUseCase;
import com.taxes.calculator.application.hourvalue.retrieve.list.ListHourValueUseCase;
import com.taxes.calculator.application.hourvalue.update.UpdateHourValueUseCase;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.infrastructure.api.HourValueAPI;
import com.taxes.calculator.infrastructure.hourvalue.models.CreateHourValueRequest;
import com.taxes.calculator.infrastructure.hourvalue.models.HourValueListResponse;
import com.taxes.calculator.infrastructure.hourvalue.models.UpdateHourValueRequest;

@RestController
public class HourValueController implements HourValueAPI {

    private final CreateHourValueUseCase createHourValueUseCase;
    private final UpdateHourValueUseCase updateHourValueUseCase;
    private final GetHourValueByIdUseCase getHourValueByIdUseCase;
    private final ListHourValueUseCase listHourValueUseCase;
    private final DeleteHourValueUseCase deleteHourValueUseCase;

    public HourValueController(
	    final CreateHourValueUseCase createHourValueUseCase,
	    final UpdateHourValueUseCase updateHourValueUseCase,
	    final GetHourValueByIdUseCase getHourValueByIdUseCase,
	    final ListHourValueUseCase listHourValueUseCase,
	    final DeleteHourValueUseCase deleteHourValueUseCase) {
	this.createHourValueUseCase = Objects
		.requireNonNull(createHourValueUseCase);
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
    public ResponseEntity<?> create(
	    @Valid CreateHourValueRequest input)
	    throws URISyntaxException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Pagination<HourValueListResponse> list(String search,
	    int page, int perPage, String sort, String direction) {
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
	    UpdateHourValueRequest input) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
	// TODO Auto-generated method stub
	return null;
    }

}
