package com.taxes.calculator.infrastructure.user.persistence;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRoleID implements Serializable {

    private static final long serialVersionUID = -8838516070234914437L;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "role_id", nullable = false)
    private String roleId;

    public UserRoleID() {
    }

    private UserRoleID(final String userId, final String roleId) {
	this.userId = userId;
	this.roleId = roleId;
    }

    public static UserRoleID from(final String userId,
	    final String roleId) {
	return new UserRoleID(userId, roleId);
    }

    @Override
    public int hashCode() {
	return Objects.hash(roleId, userId);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	UserRoleID other = (UserRoleID) obj;
	return Objects.equals(roleId, other.roleId)
		&& Objects.equals(userId, other.userId);
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getRoleId() {
	return roleId;
    }

    public void setRoleId(String roleId) {
	this.roleId = roleId;
    }

}
