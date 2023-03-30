package com.taxes.calculator.infrastructure.api.controllers;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CustomErrorResponse> handleError(
	    final HttpServletRequest request,
	    final HttpServletResponse response) throws Throwable {
	if (request.getAttribute(
		"javax.servlet.error.exception") != null) {
	    throw new DomainException(
		    "There was an error, if see this message ofter, contact an admin.",
		    List.of(new Error((String) request
			    .getAttribute(
				    "javax.servlet.error.exception")
			    .toString())));
//	    throw (Throwable) request
//		    .getAttribute("javax.servlet.error.exception");
	}
	return new ResponseEntity<>(CustomErrorResponse.create("Somenthing is wrong with your authentication, please login again", "Authentication Error"),
		HttpStatus.FORBIDDEN);
    }

    public String getErrorPath() {
	return PATH;
    }
}

class CustomErrorResponse {

    private String message;
    private String error;

    private CustomErrorResponse(final String message,
	    final String error) {
	this.message = Objects.requireNonNull(message);
	this.error = Objects.requireNonNull(error);
    }

    public static CustomErrorResponse create(String message,
	    String error) {
	return new CustomErrorResponse(message, error);
    }

    public String getMessage() {
	return message;
    }

    public String getError() {
	return error;
    }

}