package com.taxes.calculator.infrastructure.configuration.usecases;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxes.calculator.application.role.create.CreateRoleUseCase;
import com.taxes.calculator.application.role.create.DefaultCreateRoleUseCase;
import com.taxes.calculator.application.role.delete.DefaultDeleteRoleUseCase;
import com.taxes.calculator.application.role.delete.DeleteRoleUseCase;
import com.taxes.calculator.application.role.retrieve.get.DefaultGetRoleByIdUseCase;
import com.taxes.calculator.application.role.retrieve.get.GetRoleByIdUseCase;
import com.taxes.calculator.application.role.retrieve.list.DefaultListRoleUseCase;
import com.taxes.calculator.application.role.retrieve.list.ListRoleUseCase;
import com.taxes.calculator.application.role.update.DefaultUpdateRoleUseCase;
import com.taxes.calculator.application.role.update.UpdateRoleUseCase;
import com.taxes.calculator.domain.role.RoleGateway;

@Configuration
public class RoleUseCaseConfig {

    private final RoleGateway roleGateway;

    public RoleUseCaseConfig(final RoleGateway roleGateway) {
	this.roleGateway = Objects.requireNonNull(roleGateway);
    }

    @Bean
    GetRoleByIdUseCase getRoleByIdUseCase() {
	return new DefaultGetRoleByIdUseCase(roleGateway);
    }

    @Bean
    CreateRoleUseCase createRoleUseCase() {
	return new DefaultCreateRoleUseCase(roleGateway);
    }

    @Bean
    UpdateRoleUseCase updateRoleUseCase() {
	return new DefaultUpdateRoleUseCase(roleGateway);
    }

    @Bean
    DeleteRoleUseCase deleteRoleUseCase() {
	return new DefaultDeleteRoleUseCase(roleGateway);
    }

    @Bean
    ListRoleUseCase listRoleUseCase() {
	return new DefaultListRoleUseCase(roleGateway);
    }

}
