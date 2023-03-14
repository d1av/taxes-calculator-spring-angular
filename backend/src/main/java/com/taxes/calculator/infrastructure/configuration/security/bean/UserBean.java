package com.taxes.calculator.infrastructure.configuration.security.bean;

import org.springframework.context.annotation.Configuration;

import com.taxes.calculator.infrastructure.user.persistence.UserRepository;

@Configuration
public class UserBean {

    private UserRepository userRepository;

//    @Bean
//    UserRepository userMySQLGateway() {
//	return new UserRepository;
//    }
//    
}
