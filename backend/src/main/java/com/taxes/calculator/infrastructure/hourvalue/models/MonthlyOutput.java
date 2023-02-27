package com.taxes.calculator.infrastructure.hourvalue.models;

import java.io.Serializable;
import java.math.BigDecimal;

import com.taxes.calculator.application.hourvalue.calculate.CalculateHourValueOutput;

public class MonthlyOutput implements Serializable{

    private static final long serialVersionUID = 283118479930642919L;
    
    private BigDecimal hourValue;
    private Integer hoursWorked;
    private BigDecimal expectedSalary;
    private BigDecimal totalMonthlyCosts;
    private String hourValueId;
    private String variableTaxId;
    private String fixedTaxId;

    public MonthlyOutput() {
	super();
    }

    public MonthlyOutput(BigDecimal hourValue, Integer hoursWorked,
	    BigDecimal expectedSalary, BigDecimal totalMonthlyCosts,
	    String hourValueId, String variableTaxId, String fixedTaxId) {
	super();
	this.hourValue = hourValue;
	this.hoursWorked = hoursWorked;
	this.expectedSalary = expectedSalary;
	this.totalMonthlyCosts = totalMonthlyCosts;
	this.hourValueId = hourValueId;
	this.variableTaxId = variableTaxId;
	this.fixedTaxId = fixedTaxId;
    }

    public static MonthlyOutput from(CalculateHourValueOutput output) {
	return new MonthlyOutput(output.hourValue(), output.hoursWorked(),
		output.expectedSalary(), output.totalMonthlyCosts(),
		output.hourValueId(), output.variableTaxId(),
		output.fixedTaxId());
    }

    public BigDecimal getHourValue() {
	return hourValue;
    }

    public void setHourValue(BigDecimal hourValue) {
	this.hourValue = hourValue;
    }

    public Integer getHoursWorked() {
	return hoursWorked;
    }

    public void setHoursWorked(Integer hoursWorked) {
	this.hoursWorked = hoursWorked;
    }

    public BigDecimal getExpectedSalary() {
	return expectedSalary;
    }

    public void setExpectedSalary(BigDecimal expectedSalary) {
	this.expectedSalary = expectedSalary;
    }

    public BigDecimal getTotalMonthlyCosts() {
	return totalMonthlyCosts;
    }

    public void setTotalMonthlyCosts(BigDecimal totalMonthlyCosts) {
	this.totalMonthlyCosts = totalMonthlyCosts;
    }

    public String getHourValueId() {
	return hourValueId;
    }

    public void setHourValueId(String hourValueId) {
	this.hourValueId = hourValueId;
    }

    public String getVariableTaxId() {
	return variableTaxId;
    }

    public void setVariableTaxId(String variableTaxId) {
	this.variableTaxId = variableTaxId;
    }

    public String getFixedTaxId() {
	return fixedTaxId;
    }

    public void setFixedTaxId(String fixedTaxId) {
	this.fixedTaxId = fixedTaxId;
    }
}
