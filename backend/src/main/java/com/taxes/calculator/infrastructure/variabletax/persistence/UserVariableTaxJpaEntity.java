package com.taxes.calculator.infrastructure.variabletax.persistence;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.taxes.calculator.domain.user.UserID;

@Entity(name = "UserVariableTax")
@Table(name = "users_variabletaxes")
public class UserVariableTaxJpaEntity {

    @EmbeddedId
    private UserVariableTaxID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("variableTaxId")
    private VariableTaxJpaEntity variableTax;

    public UserVariableTaxJpaEntity() {
    }

    private UserVariableTaxJpaEntity(UserID userId,
	    VariableTaxJpaEntity variableTaxJpaEntity) {
	this.id = UserVariableTaxID.from(userId.getValue(),
		variableTaxJpaEntity.getId());
	this.variableTax = variableTaxJpaEntity;
    }

    public static UserVariableTaxJpaEntity from(UserID id,
	    VariableTaxJpaEntity variableTaxJpaEntity) {
	return new UserVariableTaxJpaEntity(id, variableTaxJpaEntity);
    }

    public UserVariableTaxID getId() {
	return id;
    }

    public void setId(UserVariableTaxID id) {
	this.id = id;
    }

    public VariableTaxJpaEntity getVariableTax() {
	return variableTax;
    }

    public void setVariableTax(VariableTaxJpaEntity variableTax) {
	this.variableTax = variableTax;
    }
}
