package com.taxes.calculator.infrastructure.hourvalue.persistence;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.taxes.calculator.domain.hourvalue.HourValueID;
import com.taxes.calculator.domain.user.UserID;

@Entity(name = "UserHourValue")
@Table(name = "users_hourvalues")
public class UserHourValueJpaEntity {

    @EmbeddedId
    private UserHourValueID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("hourValueId")
    private HourValueJpaEntity hourValue;

    public UserHourValueJpaEntity() {
    }

    private UserHourValueJpaEntity(final UserID userId,
	    HourValueJpaEntity hourValue) {
	this.id = UserHourValueID.from(userId.getValue(),
		hourValue.getId());
	this.hourValue = hourValue;
    }

    public static UserHourValueJpaEntity from(final UserID userId,
	    final HourValueJpaEntity hourValue) {
	return new UserHourValueJpaEntity(userId, hourValue);
    }

    @Override
    public int hashCode() {
	return Objects.hash(hourValue, id);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	UserHourValueJpaEntity other = (UserHourValueJpaEntity) obj;
	return Objects.equals(hourValue, other.hourValue)
		&& Objects.equals(id, other.id);
    }

    public UserHourValueID getId() {
	return id;
    }

    public void setId(UserHourValueID id) {
	this.id = id;
    }

    public HourValueJpaEntity getHourValue() {
	return hourValue;
    }

    public void setHourValue(HourValueJpaEntity hourValue) {
	this.hourValue = hourValue;
    }
}
