package com.taxes.calculator.infrastructure.fixedtax.persistence;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserFixedTaxID implements Serializable {

    private static final long serialVersionUID = -4379493336183022199L;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "fixed_tax_id", nullable = false)
    private String fixedTaxId;

    public UserFixedTaxID() {
    }

    private UserFixedTaxID(final String userId,
	    final String fixedTaxId) {
	this.userId = userId;
	this.fixedTaxId = fixedTaxId;
    }

    public static UserFixedTaxID from(final String userId,
	    final String roleId) {
	return new UserFixedTaxID(userId, roleId);
    }

    @Override
    public int hashCode() {
	return Objects.hash(fixedTaxId, userId);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	UserFixedTaxID other = (UserFixedTaxID) obj;
	return Objects.equals(fixedTaxId, other.fixedTaxId)
		&& Objects.equals(userId, other.userId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFixedTaxId() {
        return fixedTaxId;
    }

    public void setFixedTaxId(String fixedTaxId) {
        this.fixedTaxId = fixedTaxId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
