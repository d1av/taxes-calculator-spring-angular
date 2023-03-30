package com.taxes.calculator.infrastructure.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/")
public class RedirectController {

    @GetMapping
    public RedirectView redirectWithUsingRedirectView(
	    RedirectAttributes attributes) {
	return new RedirectView("https://taxes-calc.vercel.app/");
    }

}
