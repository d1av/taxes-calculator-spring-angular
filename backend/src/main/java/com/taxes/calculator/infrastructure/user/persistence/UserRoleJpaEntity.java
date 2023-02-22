package com.taxes.calculator.infrastructure.user.persistence;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.taxes.calculator.domain.role.RoleID;

@Entity(name = "UserRole")
@Table(name = "users_roles")
public class UserRoleJpaEntity {

    @EmbeddedId
    private UserRoleID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private UserJpaEntity user;

    public UserRoleJpaEntity() {
    }

    private UserRoleJpaEntity(final RoleID roleId,
	    final UserJpaEntity user) {
	this.id = UserRoleID.from(user.getId(), roleId.getValue());
	this.user = user;
    }

    public static UserRoleJpaEntity from(final RoleID role,
	    final UserJpaEntity user) {
	return new UserRoleJpaEntity(role, user);
    }

    @Override
    public int hashCode() {
	return Objects.hash(id, user);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	UserRoleJpaEntity other = (UserRoleJpaEntity) obj;
	return Objects.equals(id, other.id)
		&& Objects.equals(user, other.user);
    }

    public UserRoleID getId() {
	return id;
    }

    public void setId(UserRoleID id) {
	this.id = id;
    }

    public UserJpaEntity getUser() {
	return user;
    }

    public void setUser(UserJpaEntity user) {
	this.user = user;
    }

}
