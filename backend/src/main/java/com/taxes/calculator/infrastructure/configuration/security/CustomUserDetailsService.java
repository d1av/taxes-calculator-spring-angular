package com.taxes.calculator.infrastructure.configuration.security;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taxes.calculator.infrastructure.role.persistence.RoleRepository;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public CustomUserDetailsService(final UserRepository userRepository,
	    final RoleRepository roleRepository) {
	this.userRepository = Objects.requireNonNull(userRepository);
	this.roleRepository = Objects.requireNonNull(roleRepository);
    }

    @Override
    public UserDetails loadUserByUsername(String name)
	    throws UsernameNotFoundException {
	UserJpaEntity user = userRepository.findByName(name)
		.orElseThrow(() -> new UsernameNotFoundException(
			"User not found with username or email: " + name));

	Set<String> rolesIds = user.getRoles().stream()
		.map(x -> x.getId().getRoleId())
		.collect(Collectors.toSet());

	Set<String> rolesNames = this.roleRepository.findAllById(rolesIds)
		.stream().map(x -> x.getAuthority())
		.collect(Collectors.toSet());

	Set<GrantedAuthority> authorities = rolesNames.stream()
		.map(SimpleGrantedAuthority::new)
		.collect(Collectors.toSet());

	return new org.springframework.security.core.userdetails.User(
		user.getName(), user.getPassword(), authorities);
    }
}