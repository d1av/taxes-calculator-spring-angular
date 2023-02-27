package com.taxes.calculator.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    Docket api() {
	return new Docket(DocumentationType.SWAGGER_2).select()
		.apis(RequestHandlerSelectors.basePackage(
			"com.taxes.calculator.infrastructure.api.controllers"))
		.paths(PathSelectors.any()).build().pathMapping("/")
		.tags(new Tag("User Controller",
			"the Users of the API and their use cases. Only for role ADMIN"))
		.tags(new Tag("Role Controller",
			"the Role of the API and their use cases. Only for role ADMIN"))
		.tags(new Tag("Auth Controller",
			"Authentication endpoint for user register, login(jwt token here). Public access"))
		.tags(new Tag("FixedTax Controller",
			"Fixed Tax of and it's use cases, requires MEMBER role."))
		.tags(new Tag("VariableTax Controller",
			"Variable Tax and it's use cases, requires MEMBER role."))
		.tags(new Tag("HourValue Controller",
			"Hour Value of the API and it's use cases. requires MEMBER role"));
    }
}
