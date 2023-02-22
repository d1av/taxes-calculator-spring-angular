package com.taxes.calculator.infrastructure.hourvalue.persistence;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserHourValueID implements Serializable {
    private static final long serialVersionUID = -4455628932865097304L;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "hour_id", nullable = false)
    private String hourValueId;

    public UserHourValueID() {
    }

    private UserHourValueID(final String userId,
	    final String hourValueId) {
	this.userId = userId;
	this.hourValueId = hourValueId;
    }

    public static UserHourValueID from(final String userId,
	    final String hourValueId) {
	return new UserHourValueID(userId, hourValueId);
    }

    @Override
    public int hashCode() {
	return Objects.hash(hourValueId, userId);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	UserHourValueID other = (UserHourValueID) obj;
	return Objects.equals(hourValueId, other.hourValueId)
		&& Objects.equals(userId, other.userId);
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getHourValueId() {
	return hourValueId;
    }

    public void setHourValueId(String hourValueId) {
	this.hourValueId = hourValueId;
    }
}
