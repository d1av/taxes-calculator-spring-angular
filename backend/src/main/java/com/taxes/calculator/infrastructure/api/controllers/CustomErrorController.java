package com.taxes.calculator.infrastructure.api.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.flywaydb.core.api.ErrorDetails;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {
    
    private static final String PATH = "/error";
    
    @RequestMapping(PATH)
    public ResponseEntity<ErrorDetails> handleError(final HttpServletRequest request,
            final HttpServletResponse response) throws Throwable {
        throw (Throwable) request.getAttribute("javax.servlet.error.exception");
    }
    
    public String getErrorPath() {
        return PATH;
    }
}