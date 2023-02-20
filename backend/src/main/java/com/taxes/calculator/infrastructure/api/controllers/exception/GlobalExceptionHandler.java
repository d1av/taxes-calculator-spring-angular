package com.taxes.calculator.infrastructure.api.controllers.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.taxes.calculator.domain.exceptions.DomainException;
import com.taxes.calculator.domain.exceptions.NotFoundException;
import com.taxes.calculator.domain.validation.Error;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { DomainException.class })
    public ResponseEntity<?> handleDomainException(
	    final DomainException ex, final HttpServletRequest req,
	    final HttpServletResponse res) {
	return ResponseEntity.unprocessableEntity()
		.body(ApiError.from(ex));
    }

    @ExceptionHandler(value = { NotFoundException.class })
    public ResponseEntity<?> handleNotFoundException(
	    final DomainException ex) {
	return ResponseEntity.status(HttpStatus.NOT_FOUND)
		.body(ApiError.from(ex));
    }

    record ApiError(String message, List<Error> errors) {
	static ApiError from(final DomainException ex) {
	    return new ApiError(ex.getMessage(), ex.getErrors());
	}
    }
}