package com.taxes.calculator.infrastructure.configuration.usecases;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxes.calculator.application.fixedtax.create.CreateFixedTaxUseCase;
import com.taxes.calculator.application.fixedtax.create.DefaultCreateFixedTaxUseCase;
import com.taxes.calculator.application.fixedtax.delete.DefaultDeleteFixedTaxUseCase;
import com.taxes.calculator.application.fixedtax.delete.DeleteFixedTaxUseCase;
import com.taxes.calculator.application.fixedtax.retrieve.get.DefaultGetFixedTaxByIdUseCase;
import com.taxes.calculator.application.fixedtax.retrieve.get.GetFixedTaxByIdUseCase;
import com.taxes.calculator.application.fixedtax.retrieve.list.DefaultListFixedTaxUseCase;
import com.taxes.calculator.application.fixedtax.retrieve.list.ListFixedTaxUseCase;
import com.taxes.calculator.application.fixedtax.update.DefaultUpdateFixedTaxUseCase;
import com.taxes.calculator.application.fixedtax.update.UpdateFixedTaxUseCase;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.user.UserGateway;

@Configuration
public class FixedTaxUseCaseConfig {
    private final FixedTaxGateway fixedTaxGateway;
    private final UserGateway userGateway;

    public FixedTaxUseCaseConfig(final FixedTaxGateway fixedTaxGateway,
	    final UserGateway userGateway) {
	super();
	this.fixedTaxGateway = Objects.requireNonNull(fixedTaxGateway);
	this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Bean
    GetFixedTaxByIdUseCase getFixedTaxByIdUseCase() {
	return new DefaultGetFixedTaxByIdUseCase(fixedTaxGateway);
    }

    @Bean
    CreateFixedTaxUseCase createFixedTaxUseCase() {
	return new DefaultCreateFixedTaxUseCase(fixedTaxGateway,
		userGateway);
    }

    @Bean
    UpdateFixedTaxUseCase updateFixedTaxUseCase() {
	return new DefaultUpdateFixedTaxUseCase(fixedTaxGateway,
		userGateway);
    }

    @Bean
    DeleteFixedTaxUseCase deleteFixedTaxUseCase() {
	return new DefaultDeleteFixedTaxUseCase(fixedTaxGateway);
    }

    @Bean
    ListFixedTaxUseCase listFixedTaxUseCase() {
	return new DefaultListFixedTaxUseCase(fixedTaxGateway);
    }

}
