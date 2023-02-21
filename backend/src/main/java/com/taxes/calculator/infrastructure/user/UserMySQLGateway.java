package com.taxes.calculator.infrastructure.user;

import static com.taxes.calculator.infrastructure.utils.SpecificationUtils.like;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.taxes.calculator.domain.exceptions.DomainException;
import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleGateway;
import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserGateway;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;

public class UserMySQLGateway implements UserGateway {

    private final UserRepository userRepository;
    private final RoleGateway roleGateway;

    public UserMySQLGateway(final UserRepository userRepository,
	    final RoleGateway roleGateway) {
	this.userRepository = Objects.requireNonNull(userRepository);
	this.roleGateway = Objects.requireNonNull(roleGateway);
    }

    @Override
    public User create(User aUser) {
	validateRoles(aUser);
	return save(aUser);
    }

    private User save(User aUser) {
	return this.userRepository.save(UserJpaEntity.from(aUser))
		.toAggregate();
    }

    private void validateRoles(User user) {
	List<String> authoritiesList = user.getRoles().stream()
		.map(Role::getAuthority).collect(Collectors.toList());

	List<RoleID> ids = user.getRoles().stream().map(Role::getId)
		.collect(Collectors.toList());

	Set<RoleID> validRoles = roleGateway
		.existsByAuthority(authoritiesList);

	if (!validRoles.containsAll(ids)) {
	    throw new DomainException("Failed to validate roles",
		    List.of(new Error(
			    "One or more roles are not valid")));
	}
    }

    @Override
    public Optional<User> findById(UserID anId) {
	return this.userRepository.findById(anId.getValue())
		.map(UserJpaEntity::toAggregate);
    }

    @Override
    public Optional<User> findByName(String name) {
	return this.userRepository.findByName(name)
		.map(UserJpaEntity::toAggregate);
    }

    @Override
    public Pagination<User> findAll(SearchQuery aQuery) {
	final var page = PageRequest.of(aQuery.page(),
		aQuery.perPage(),
		Sort.by(Sort.Direction.fromString(aQuery.direction()),
			aQuery.sort()));

	// Dynamic Search
	final var specifications = Optional.ofNullable(aQuery.terms())
		.filter(str -> !str.isBlank())
		.map(this::assembleSpecification).orElse(null);

	final var pageResult = this.userRepository
		.findAll(Specification.where(specifications), page);

	return new Pagination<>(pageResult.getNumber(),
		pageResult.getSize(), pageResult.getTotalElements(),
		pageResult.map(UserJpaEntity::toAggregate).toList());
    }

    @Override
    public User update(User aUser) {
	return save(aUser);
    }

    @Override
    public void deleteById(UserID anId) {
	this.userRepository.deleteById(anId.getValue());
    }

    @Override
    public List<UserID> existsByIds(Iterable<UserID> userIds) {
	final var ids = StreamSupport
		.stream(userIds.spliterator(), false)
		.map(UserID::getValue).toList();
	return this.userRepository.existsByIds(ids).stream()
		.map(UserID::from).collect(Collectors.toList());
    }

    private Specification<UserJpaEntity> assembleSpecification(
	    final String str) {
	final Specification<UserJpaEntity> nameLike = like("name",
		str);
	final Specification<UserJpaEntity> descriptionLike = like(
		"description", str);
	return nameLike.or(like("description", str));
    }
}
