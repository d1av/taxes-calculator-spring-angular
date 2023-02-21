package com.taxes.calculator.infrastructure.role.persistence;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.taxes.calculator.domain.role.Role;
import com.taxes.calculator.domain.role.RoleID;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;

@Entity(name = "Role")
@Table(name = "roles")
public class RoleJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "authority", nullable = false)
    private String authority;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;
       

    public RoleJpaEntity() {
    }

    private RoleJpaEntity(final String id, final String authority,
	    final Instant createdAt, final Instant updatedAt) {
	this.id = id;
	this.authority = authority;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
    }

    public static RoleJpaEntity from(final Role aRole) {
	return new RoleJpaEntity(aRole.getId().getValue(),
		aRole.getAuthority(), aRole.getCreatedAt(),
		aRole.getUpdatedAt());
    }

    public Role toAggregate() {
	return Role.with(RoleID.from(getId()), getAuthority(),
		getCreatedAt(), getUpdatedAt());
    }

    public static RoleJpaEntity with(final String id,
	    final String authority, final Instant createdAt,
	    final Instant updatedAt) {
	
	return new RoleJpaEntity(id, authority, createdAt, updatedAt);
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getAuthority() {
	return authority;
    }

    public void setAuthority(String authority) {
	this.authority = authority;
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
}
