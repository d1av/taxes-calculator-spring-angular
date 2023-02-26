package com.taxes.calculator.infrastructure.auth;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.infrastructure.auth.models.AuthOutput;
import com.taxes.calculator.infrastructure.auth.models.AuthRequest;
import com.taxes.calculator.infrastructure.configuration.security.JwtTokenProvider;
import com.taxes.calculator.infrastructure.role.persistence.RoleRepository;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;

@Service
public class AuthMySQLGateway {

    private final PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private JwtTokenProvider jwtTokenProvider;

    public AuthMySQLGateway(final PasswordEncoder passwordEncoder,
	    final AuthenticationManager authenticationManager,
	    final UserRepository userRepository,
	    final RoleRepository roleRepository,
	    final JwtTokenProvider jwtTokenProvider) {
	this.passwordEncoder = passwordEncoder;
	this.authenticationManager = authenticationManager;
	this.userRepository = userRepository;
	this.roleRepository = roleRepository;
	this.jwtTokenProvider = jwtTokenProvider;
    }

    public AuthOutput login(AuthRequest authRequest) {
	Authentication authentication = authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(authRequest.name(),
			authRequest.password()));

	SecurityContextHolder.getContext()
		.setAuthentication(authentication);

	String token = jwtTokenProvider.generateToken(authentication);

	List<String> roles = authentication.getAuthorities().stream()
		.map(x -> x.getAuthority()).collect(Collectors.toList());

	return AuthOutput.with(token,roles);
    }

    @Transactional
    public UserJpaEntity authenticated() {
	try {
	    String username = SecurityContextHolder.getContext()
		    .getAuthentication().getName();

	    return userRepository.findByName(username).get();
	} catch (Exception e) {
	    throw new Error("Invalid user");
	}

    }

//    public void validateSelfOrAdmin(String userId) {
//	UserJpaEntity user = authenticated();
//	if (user.getId() != userId && !user.hasRole("ADMIN")) {
//	    throw new Error("Access denied");
//	}
//    }

}
