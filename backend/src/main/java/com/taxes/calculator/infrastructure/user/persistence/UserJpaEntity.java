package com.taxes.calculator.infrastructure.user.persistence;

import static com.taxes.calculator.infrastructure.utils.EntityMapper.mapToEntity;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.taxes.calculator.domain.user.User;
import com.taxes.calculator.domain.user.UserID;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleJpaEntity> roles;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    public UserJpaEntity() {
    }

    private UserJpaEntity(String id, String name, String password,
	    Boolean active, Set<RoleJpaEntity> roles,
	    Instant createdAt, Instant updatedAt, Instant deletedAt) {
	this.id = id;
	this.name = name;
	this.password = password;
	this.active = active;
	this.roles = roles;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
	this.deletedAt = deletedAt;
    }

    public static UserJpaEntity from(final User aUser) {
	return new UserJpaEntity(aUser.getId().getValue(),
		aUser.getName(), aUser.getPassword(),
		aUser.getActive(),
		mapToEntity(aUser.getRoles(), RoleJpaEntity::from),
		aUser.getCreatedAt(), aUser.getUpdatedAt(),
		aUser.getDeletedAt());
    }

    public User toAggregate() {
	return User.with(UserID.from(getId()), getName(),
		getPassword(), getActive(), mapToEntity(getRoles(), RoleJpaEntity::toAggregate),
		getCreatedAt(), getUpdatedAt(), getDeletedAt());
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

    public Set<RoleJpaEntity> getRoles() {
	return roles;
    }

    public void setRoles(Set<RoleJpaEntity> roles) {
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
