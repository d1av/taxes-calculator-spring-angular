package com.taxes.calculator.infrastructure.fixedtax.persistence;

import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.taxes.calculator.domain.fixedtax.FixedTaxID;
import com.taxes.calculator.domain.user.UserID;
import com.taxes.calculator.infrastructure.user.persistence.UserJpaEntity;

public class UserFixedTaxJpaEntity {
   
    @EmbeddedId
    private UserFixedTaxID id;

    @ManyToOne
    @MapsId("userId")
    private FixedTaxJpaEntity fixedTax;

    public UserFixedTaxJpaEntity() {
    }

    private UserFixedTaxJpaEntity(final UserID userId,
	    final FixedTaxJpaEntity aTax) {
	this.id = UserFixedTaxID.from(userId.getValue(), aTax.getId());
	this.fixedTax = aTax;
    }

    public static UserFixedTaxJpaEntity from(final UserID userId,
	    final FixedTaxJpaEntity aTax) {
	return new UserFixedTaxJpaEntity(userId, aTax);
    }

    public UserFixedTaxID getId() {
        return id;
    }

    public void setId(UserFixedTaxID id) {
        this.id = id;
    }

    public FixedTaxJpaEntity getFixedTax() {
        return fixedTax;
    }

    public void setFixedTax(FixedTaxJpaEntity fixedTax) {
        this.fixedTax = fixedTax;
    }
}
