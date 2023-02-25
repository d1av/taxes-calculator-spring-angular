package com.taxes.calculator.infrastructure.configuration.usecases;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxes.calculator.application.hourvalue.calculate.CalculateHourValueUseCase;
import com.taxes.calculator.application.hourvalue.calculate.DefaultCalculateHourValueUseCase;
import com.taxes.calculator.application.hourvalue.create.CreateHourValueUseCase;
import com.taxes.calculator.application.hourvalue.create.DefaultCreateHourValueUseCase;
import com.taxes.calculator.application.hourvalue.delete.DefaultDeleteHourValueUseCase;
import com.taxes.calculator.application.hourvalue.delete.DeleteHourValueUseCase;
import com.taxes.calculator.application.hourvalue.retrieve.get.DefaultGetHourValueByIdUseCase;
import com.taxes.calculator.application.hourvalue.retrieve.get.GetHourValueByIdUseCase;
import com.taxes.calculator.application.hourvalue.retrieve.list.DefaultListHourValueUseCase;
import com.taxes.calculator.application.hourvalue.retrieve.list.ListHourValueUseCase;
import com.taxes.calculator.application.hourvalue.update.DefaultUpdateHourValueUseCase;
import com.taxes.calculator.application.hourvalue.update.UpdateHourValueUseCase;
import com.taxes.calculator.domain.fixedtax.FixedTaxGateway;
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.variabletax.VariableTaxGateway;

@Configuration
public class HourValueUseCaseConfig {

    private final FixedTaxGateway fixedTaxGateway;
    private final VariableTaxGateway variableTaxGateway;
    private final HourValueGateway hourValueGateway;
    private final UserGateway userGateway;

    public HourValueUseCaseConfig(final FixedTaxGateway fixedTaxGateway,
	    final HourValueGateway hourValueGateway,
	    final UserGateway userGateway,
	    final VariableTaxGateway variableTaxGateway) {
	this.fixedTaxGateway = Objects.requireNonNull(fixedTaxGateway);
	this.variableTaxGateway = Objects
		.requireNonNull(variableTaxGateway);
	this.hourValueGateway = Objects.requireNonNull(hourValueGateway);
	this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Bean
    CalculateHourValueUseCase calculateHourValueUseCase() {
	return new DefaultCalculateHourValueUseCase(hourValueGateway,
		fixedTaxGateway, variableTaxGateway);
    }

    @Bean
    CreateHourValueUseCase createHourValueUseCase() {
	return new DefaultCreateHourValueUseCase(hourValueGateway,
		userGateway);
    }

    @Bean
    UpdateHourValueUseCase updateHourValueUseCase() {
	return new DefaultUpdateHourValueUseCase(hourValueGateway,
		userGateway);
    }

    @Bean
    GetHourValueByIdUseCase getHourValueByIdUseCase() {
	return new DefaultGetHourValueByIdUseCase(hourValueGateway);
    }

    @Bean
    DeleteHourValueUseCase deleteHourValueUseCase() {
	return new DefaultDeleteHourValueUseCase(hourValueGateway);
    }

    @Bean
    ListHourValueUseCase listHourValueUseCase() {
	return new DefaultListHourValueUseCase(hourValueGateway);
    }

}
