package com.taxes.calculator.infrastructure.api.controllers;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.calculator.application.totaltax.TotalTaxUseCase;
import com.taxes.calculator.infrastructure.api.TotalTaxAPI;
import com.taxes.calculator.infrastructure.totaltax.models.TotalTaxResponse;

@RestController
public class TotalTaxController implements TotalTaxAPI {

    private final TotalTaxUseCase totalTaxUseCase;

    public TotalTaxController(
	    final TotalTaxUseCase totalTaxUseCase) {
	this.totalTaxUseCase = Objects
		.requireNonNull(totalTaxUseCase);
    }

    @Override
    public ResponseEntity<?> getByUser(String id) {
	return new ResponseEntity<>(
		TotalTaxResponse
			.with(totalTaxUseCase.execute(id)),
		HttpStatus.OK);
    }

}
