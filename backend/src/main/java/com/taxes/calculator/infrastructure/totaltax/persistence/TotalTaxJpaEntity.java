package com.taxes.calculator.infrastructure.totaltax.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "TotalTax")
@Table(name = "total_taxes")
public class TotalTaxJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_id", nullable = false)
    private String taxId;

    @Column(name = "fixed_tax_id", length = (32))
    private String fixedTaxId;

    @Column(name = "variable_tax_id", length = (32))
    private String variableTaxId;

    @Column(name = "hour_value_id", length = (32))
    private String hourValueId;

    @Column(name = "user_id", length = (32), nullable = false)
    private String userId;

    public TotalTaxJpaEntity() {
    }

    private TotalTaxJpaEntity(final String taxId,
	    final String fixedTaxId, final String variableTaxId,
	    final String hourValueId, final String userId) {
	this.fixedTaxId = fixedTaxId;
	this.variableTaxId = variableTaxId;
	this.hourValueId = hourValueId;
	this.userId = userId;
    }

    public static TotalTaxJpaEntity with(final String taxId,
	    final String fixedTaxId, final String variableTaxId,
	    final String hourValueId, final String userId) {
	return new TotalTaxJpaEntity(taxId, fixedTaxId,
		variableTaxId, hourValueId, userId);
    }

    public static TotalTaxJpaEntity update(
	    final TotalTaxJpaEntity aTax) {
	return new TotalTaxJpaEntity(aTax.taxId, aTax.fixedTaxId,
		aTax.variableTaxId, aTax.hourValueId,
		aTax.userId);
    }

    public String getTaxId() {
	return taxId;
    }

    public void setTaxId(String taxId) {
	this.taxId = taxId;
    }

    public String getFixedTaxId() {
	return fixedTaxId;
    }

    public void setFixedTaxId(String fixedTaxId) {
	this.fixedTaxId = fixedTaxId;
    }

    public String getVariableTaxId() {
	return variableTaxId;
    }

    public void setVariableTaxId(String variableTaxId) {
	this.variableTaxId = variableTaxId;
    }

    public String getHourValueId() {
	return hourValueId;
    }

    public void setHourValueId(String hourValueId) {
	this.hourValueId = hourValueId;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

}
