package com.taxes.calculator.infrastructure.configuration.usecases;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxes.calculator.application.variabletax.create.CreateVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.create.DefaultCreateVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.delete.DefaultDeleteVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.delete.DeleteVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.retrieve.get.DefaultGetVariableTaxByIdUseCase;
import com.taxes.calculator.application.variabletax.retrieve.get.GetVariableTaxByIdUseCase;
import com.taxes.calculator.application.variabletax.retrieve.list.DefaultListVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.retrieve.list.ListVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.update.DefaultUpdateVariableTaxUseCase;
import com.taxes.calculator.application.variabletax.update.UpdateVariableTaxUseCase;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;

@Configuration
public class VariableTaxUseCaseConfig {

    private final VariableTaxGateway variableTaxGateway;
    private final UserGateway userGateway;

    public VariableTaxUseCaseConfig(
	    final VariableTaxGateway variableTaxGateway,
	    final UserGateway userGateway) {
	this.variableTaxGateway = Objects
		.requireNonNull(variableTaxGateway);
	this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Bean
    GetVariableTaxByIdUseCase getVariableTaxByIdUseCase() {
	return new DefaultGetVariableTaxByIdUseCase(
		variableTaxGateway);
    }

    @Bean
    CreateVariableTaxUseCase createVariableTaxUseCase() {
	return new DefaultCreateVariableTaxUseCase(variableTaxGateway,
		userGateway);
    }

    @Bean
    UpdateVariableTaxUseCase updateVariableTaxUseCase() {
	return new DefaultUpdateVariableTaxUseCase(variableTaxGateway,
		userGateway);
    }

    @Bean
    ListVariableTaxUseCase listVariableTaxUseCase() {
	return new DefaultListVariableTaxUseCase(variableTaxGateway);
    }

    @Bean
    DeleteVariableTaxUseCase deleteVariableTaxUseCase() {
	return new DefaultDeleteVariableTaxUseCase(
		variableTaxGateway);
    }
}
