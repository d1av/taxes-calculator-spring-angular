package com.taxes.calculator.infrastructure.configuration.usecases;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
import com.taxes.calculator.domain.hourvalue.HourValueGateway;
import com.taxes.calculator.domain.user.UserGateway;

@Configuration
public class HourValueUseCaseConfig {

    private final HourValueGateway hourValueGateway;
    private final UserGateway userGateway;

    public HourValueUseCaseConfig(
	    final HourValueGateway hourValueGateway,
	    final UserGateway userGateway) {
	this.hourValueGateway = Objects
		.requireNonNull(hourValueGateway);
	this.userGateway = Objects.requireNonNull(userGateway);
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
