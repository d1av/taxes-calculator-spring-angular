package com.taxes.calculator.infrastructure.auth;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taxes.calculator.domain.exceptions.DomainException;
import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.infrastructure.auth.models.AuthOutput;
import com.taxes.calculator.infrastructure.auth.models.AuthRequest;
import com.taxes.calculator.infrastructure.auth.models.RegisterOutput;
import com.taxes.calculator.infrastructure.auth.models.RegisterRequest;
import com.taxes.calculator.infrastructure.configuration.security.JwtTokenProvider;
import com.taxes.calculator.infrastructure.role.persistence.RoleRepository;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;
import com.taxes.calculator.infrastructure.utils.EncoderUtils;

@Service
public class AuthMySQLGateway {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthMySQLGateway(
	    final AuthenticationManager authenticationManager,
	    final UserRepository userRepository,
	    final RoleRepository roleRepository,
	    final JwtTokenProvider jwtTokenProvider,
	    final PasswordEncoder passwordEncoder) {
	this.authenticationManager = Objects
		.requireNonNull(authenticationManager);
	this.userRepository = Objects.requireNonNull(userRepository);
	this.roleRepository = Objects.requireNonNull(roleRepository);
	this.jwtTokenProvider = Objects
		.requireNonNull(jwtTokenProvider);
	this.passwordEncoder = Objects
		.requireNonNull(passwordEncoder);
    }

    public AuthOutput login(AuthRequest authRequest) {
	String errorMessage = "Error logging the user.";
	if (userRepository.findByName(authRequest.name())
		.isPresent()) {
	    Optional<UserJpaEntity> user = userRepository
		    .findByName(authRequest.name());
	    if (user.isPresent() && !authRequest.name()
		    .equals(user.get().getName())) {
		throw new DomainException(errorMessage,
			List.of(new com.taxes.calculator.domain.validation.Error(
				"Please check the username or password.")));
	    }
	    if (user.isPresent()
		    && !passwordEncoder.matches(authRequest.password(),
			    user.get().getPassword())) {
		throw new DomainException(errorMessage,
			List.of(new com.taxes.calculator.domain.validation.Error(
				"Please check the username or password.")));
	    }

	    Authentication authentication = authenticationManager
		    .authenticate(
			    new UsernamePasswordAuthenticationToken(
				    authRequest.name(),
				    authRequest.password()));

	    SecurityContextHolder.getContext()
		    .setAuthentication(authentication);

	    String token = jwtTokenProvider
		    .generateToken(authentication);

	    List<String> roles = authentication.getAuthorities()
		    .stream().map(x -> x.getAuthority())
		    .collect(Collectors.toList());

	    if (user.isPresent()) {
		return AuthOutput.with(token, roles,
			user.get().getId());
	    }

	    return AuthOutput.with(token, roles, null);
	} else {
	    throw new DomainException(errorMessage, List
		    .of(new com.taxes.calculator.domain.validation.Error(
			    "Wrong user or password")));
	}
    }

    public RegisterOutput register(RegisterRequest input) {
	if (input != null && userRepository.findByName(input.name())
		.isEmpty()) {
	    final var encodedPassword = EncoderUtils
		    .encode(input.password());
	    final var memberRole = roleRepository
		    .findByAuthority("ROLE_MEMBER");

	    final var user = User.newUser(input.name(),
		    encodedPassword, true);

	    if (memberRole.isPresent()) {
		user.addRoleID(RoleID.from(memberRole.get().getId()));
	    }

	    final UserJpaEntity savedUser = userRepository
		    .saveAndFlush(UserJpaEntity.from(user));

	    return RegisterOutput.from(savedUser);
	} else if (input == null) {
	    throw new DomainException("Error registering the user.",
		    List.of(new com.taxes.calculator.domain.validation.Error(
			    "Please check all fields.")));
	} else {
	    throw new DomainException("Error registering the user.",
		    List.of(new com.taxes.calculator.domain.validation.Error(
			    "name %s already exists, please choose another."
				    .formatted(input.name()))));
	}
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

    public void checkIfUserExists(String name) {
	Optional<UserJpaEntity> foundUser = userRepository
		.findByName(name);
	if (foundUser.isPresent()) {
	    throw new DomainException("Error creating the user.", List
		    .of(new com.taxes.calculator.domain.validation.Error(
			    "Please select another username.")));
	}
    }

//    public void validateSelfOrAdmin(String userId) {
//	UserJpaEntity user = authenticated();
//	if (user.getId() != userId && !user.hasRole("ADMIN")) {
//	    throw new Error("Access denied");
//	}
//    }

}
