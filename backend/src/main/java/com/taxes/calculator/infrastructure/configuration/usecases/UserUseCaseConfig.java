package com.taxes.calculator.infrastructure.configuration.usecases;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxes.calculator.application.user.create.CreateUserUseCase;
import com.taxes.calculator.application.user.create.DefaultCreateUserUseCase;
import com.taxes.calculator.application.user.delete.DefaultDeleteUserUseCase;
import com.taxes.calculator.application.user.delete.DeleteUserUseCase;
import com.taxes.calculator.application.user.retrieve.get.DefaultGetUserByIdUseCase;
import com.taxes.calculator.application.user.retrieve.get.GetUserByIdUseCase;
import com.taxes.calculator.application.user.retrieve.list.DefaultListUserUseCase;
import com.taxes.calculator.application.user.retrieve.list.ListUserUseCase;
import com.taxes.calculator.application.user.update.DefaultUpdateUserUseCase;
import com.taxes.calculator.application.user.update.UpdateUserUseCase;
import com.taxes.calculator.domain.role.RoleGateway;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.infrastructure.user.UserMySQLGateway;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;

@Configuration
public class UserUseCaseConfig {
    
    private final UserGateway userGateway;

    private final RoleGateway roleGateway;
    
    public UserUseCaseConfig(final UserGateway userGateway,
	    final RoleGateway roleGateway) {
	this.userGateway = Objects.requireNonNull(userGateway);
	this.roleGateway = Objects.requireNonNull(roleGateway);
    }
 
    @Bean
    GetUserByIdUseCase getUserByIdUseCase() {
	return new DefaultGetUserByIdUseCase(userGateway);
    }

    @Bean
    CreateUserUseCase createUserUseCase() {
	return new DefaultCreateUserUseCase(userGateway, roleGateway);
    }

    @Bean
    UpdateUserUseCase updateUserUseCase() {
	return new DefaultUpdateUserUseCase(userGateway, roleGateway);
    }

    @Bean
    DeleteUserUseCase deleteUserUseCase() {
	return new DefaultDeleteUserUseCase(userGateway);
    }

    @Bean
    ListUserUseCase listUserUseCase() {
	return new DefaultListUserUseCase(userGateway);
    }

}
