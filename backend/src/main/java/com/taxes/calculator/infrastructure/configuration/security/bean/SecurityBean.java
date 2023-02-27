package com.taxes.calculator.infrastructure.configuration.security.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxes.calculator.infrastructure.configuration.security.CustomUserDetailsService;
import com.taxes.calculator.infrastructure.configuration.security.JwtAuthenticationFilter;
import com.taxes.calculator.infrastructure.configuration.security.JwtTokenProvider;

@Configuration
public class SecurityBean {
    private JwtTokenProvider jwtTokenProvider;
    private CustomUserDetailsService customUserDetailsService;

    public SecurityBean(JwtTokenProvider jwtTokenProvider,
	    CustomUserDetailsService customUserDetailsService) {
	super();
	this.jwtTokenProvider = jwtTokenProvider;
	this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    JwtAuthenticationFilter authenticationFilter() {
	return new JwtAuthenticationFilter(jwtTokenProvider,
		customUserDetailsService);
    }
}
