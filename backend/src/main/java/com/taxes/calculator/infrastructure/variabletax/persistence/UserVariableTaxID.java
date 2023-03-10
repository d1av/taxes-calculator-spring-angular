package com.taxes.calculator.infrastructure.variabletax.persistence;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserVariableTaxID implements Serializable {

    private static final long serialVersionUID = -42465404279452297L;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "variabletax_id", nullable = false)
    private String variableTaxId;

    public UserVariableTaxID() {
    }

    private UserVariableTaxID(final String userId,
	    final String variableTaxId) {
	this.userId = userId;
	this.variableTaxId = variableTaxId;
    }

    public static UserVariableTaxID from(final String userId,
	    final String variableTaxId) {
	return new UserVariableTaxID(userId, variableTaxId);
    }

    @Override
    public int hashCode() {
	return Objects.hash(userId, variableTaxId);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	UserVariableTaxID other = (UserVariableTaxID) obj;
	return Objects.equals(userId, other.userId)
		&& Objects.equals(variableTaxId, other.variableTaxId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVariableTaxId() {
        return variableTaxId;
    }

    public void setVariableTaxId(String variableTaxId) {
        this.variableTaxId = variableTaxId;
    }
}
