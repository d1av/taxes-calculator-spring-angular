package com.taxes.calculator.infrastructure.user.persistence;

import java.time.Instant;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.domain.utils.InstantUtils;
import com.taxes.calculator.infrastructure.role.persistence.RoleJpaEntity;

@Entity(name = "User")
@Table(name = "users")
public class UserJpaEntity {

    @Id
    @Column()
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<UserRoleJpaEntity> roles;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    public UserJpaEntity() {
    }

    private UserJpaEntity(String id, String name, String password,
	    Boolean active, Instant createdAt, Instant updatedAt,
	    Instant deletedAt) {
	this.id = id;
	this.name = name;
	this.password = password;
	this.active = active;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
	this.deletedAt = deletedAt;
	this.roles = new HashSet<>();
    }

    public static UserJpaEntity from(final User aUser) {
	final var anEntity = new UserJpaEntity(
		aUser.getId().getValue(), aUser.getName(),
		aUser.getPassword(), aUser.getActive(),
		aUser.getCreatedAt(), aUser.getUpdatedAt(),
		aUser.getDeletedAt());
	aUser.getRoles().forEach(anEntity::addRole);

	return anEntity;
    }

    public User toAggregate() {
	final var roleIdList = getRoles() != null
		? getRoles().stream()
			.map(x -> RoleID.from(x.getId().getRoleId()))
			.collect(Collectors.toSet())
		: null;
	return User.with(UserID.from(getId()), getName(),
		getPassword(), getActive(), roleIdList,
		getCreatedAt(), getUpdatedAt(), getDeletedAt());
    }

    public void addRole(final RoleID roleId) {
	this.roles.add(UserRoleJpaEntity.from(roleId, this));
	this.updatedAt = InstantUtils.now();
    }

    private void removeRole(final RoleID anId) {
	this.roles.remove(UserRoleJpaEntity.from(anId, this));
	this.updatedAt = InstantUtils.now();
    }

    public List<RoleID> getCategoriesIDs() {
	return getRoles().stream()
		.map(x -> RoleID.from(x.getId().getRoleId()))
		.toList();
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Boolean getActive() {
	return active;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

    public Set<UserRoleJpaEntity> getRoles() {
	return roles;
    }

    public void setRoles(Set<UserRoleJpaEntity> roles) {
	this.roles = roles;
    }

    public Instant getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
	this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
	return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
	this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
	return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
	this.deletedAt = deletedAt;
    }
}
