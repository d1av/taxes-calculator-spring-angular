package com.taxes.calculator.infrastructure.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    public SecurityConfig(
	    JwtAuthenticationEntryPoint authenticationEntryPoint,
	    JwtAuthenticationFilter authenticationFilter) {
	this.authenticationEntryPoint = authenticationEntryPoint;
	this.authenticationFilter = authenticationFilter;
    }

    @Bean
    static PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
	    AuthenticationConfiguration configuration)
	    throws Exception {
	return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
	    throws Exception {

	http.csrf().disable().cors().and()
		.authorizeHttpRequests((authorize) -> authorize
			.antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
			.antMatchers("/api/users/**").hasRole("ADMIN")
			.antMatchers("/api/roles/**").hasRole("ADMIN")
			.antMatchers("/api/fixedtaxes/**").hasRole("MEMBER")
			.antMatchers("/api/variabletaxes/**").hasRole("MEMBER")
			.antMatchers("/api/hourvalues/**").hasRole("MEMBER")
			.antMatchers("/api/totaltaxes/**").hasRole("MEMBER")
			.antMatchers("/api/auth/**").permitAll()
			.antMatchers("/actuator/**").permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/api/**")
			.permitAll())
		.exceptionHandling(exception -> exception
			.authenticationEntryPoint(
				authenticationEntryPoint))
		.sessionManagement(
			session -> session.sessionCreationPolicy(
				SessionCreationPolicy.STATELESS));

	http.addFilterBefore(authenticationFilter,
		UsernamePasswordAuthenticationFilter.class);

	return http.build();
    }
}