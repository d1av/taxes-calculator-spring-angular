package com.taxes.calculator.infrastructure.role;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleGateway;
import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.infrastructure.role.persistence.RoleJpaEntity;
import com.taxes.calculator.infrastructure.role.persistence.RoleRepository;

@Service
public class RoleMySQLGateway implements RoleGateway {

    private final RoleRepository repository;

    public RoleMySQLGateway(final RoleRepository repository) {
	this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Role create(Role aRole) {
	return save(aRole);
    }

    @Override
    public Optional<Role> findById(RoleID anId) {
	return this.repository.findById(anId.getValue())
		.map(RoleJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Role> findAll(SearchQuery aQuery) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Role update(Role aRole) {
	return save(aRole);
    }

    @Override
    public void deleteById(RoleID anId) {
	String anRoleId = anId.getValue();
	if (this.repository.existsById(anRoleId)) {
	    this.repository.deleteById(anRoleId);
	}
    }

    @Override
    public Set<RoleID> existsByIds(final Iterable<RoleID> rolesIds) {
	final var ids = StreamSupport
		.stream(rolesIds.spliterator(), false)
		.map(RoleID::getValue).toList();
	return this.repository.existsByIds(ids).stream()
		.map(RoleID::from).collect(Collectors.toSet());
    }

    @Override
    public Optional<Role> findByAuthority(String anAuthority) {
	return (this.repository.findByAuthority(anAuthority))
		.map(RoleJpaEntity::toAggregate);
    }

    private Role save(Role aRole) {
	return this.repository.save(RoleJpaEntity.from(aRole))
		.toAggregate();
    }

    @Override
    public Boolean existById(String id) {
	return this.repository.existsById(id);
    }

}
