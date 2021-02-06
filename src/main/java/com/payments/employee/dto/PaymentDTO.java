package com.payments.employee.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class PaymentDTO {
	@NotNull()
	private long employeeId;
	
	@NotNull()
	private int month;
	
	@NotNull()
	private int year;
	
	private BigDecimal salary;
	
	public PaymentDTO() {}

	public PaymentDTO(@NotNull long employeeId, @NotNull int month, @NotNull int year, BigDecimal salary) {
		super();
		this.employeeId = employeeId;
		this.month = month;
		this.year = year;
		this.salary = salary;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
}
