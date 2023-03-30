package com.taxes.calculator.infrastructure.api.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.flywaydb.core.api.ErrorCode;
import org.flywaydb.core.api.ErrorDetails;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taxes.calculator.domain.exceptions.DomainException;
import com.taxes.calculator.domain.validation.Error;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(PATH)
    public ResponseEntity<ErrorDetails> handleError(
	    final HttpServletRequest request,
	    final HttpServletResponse response) throws Throwable {
	if (request.getAttribute(
		"javax.servlet.error.exception") != null) {
	    throw new DomainException("There was an error, if see this message ofter, contact an admin.",
		    List.of(new Error((String) request
			    .getAttribute(
				    "javax.servlet.error.exception").toString())));
//	    throw (Throwable) request
//		    .getAttribute("javax.servlet.error.exception");
	}
	return ResponseEntity.badRequest().body(new ErrorDetails(
		ErrorCode.ERROR,
		(String) request
			.getAttribute("javax.servlet.error.exception")
			.toString()));
    }

    public String getErrorPath() {
	return PATH;
    }
}