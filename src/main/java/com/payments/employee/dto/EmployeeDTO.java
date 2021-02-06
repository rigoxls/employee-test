package com.payments.employee.dto;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.validation.constraints.NotNull;

public class EmployeeDTO {
	@NotNull()
	private long id;
	
	@NotNull()
	public String name;
	
	@NotNull()
	public String lastname;
	
	@NotNull()
	public BigDecimal salary;
	
	@NotNull()
	public Calendar initDate;
	
	public Calendar endDate;

	public EmployeeDTO() {
	}

	public EmployeeDTO(@NotNull long id, @NotNull String name, @NotNull String lastname, @NotNull BigDecimal salary,
			@NotNull Calendar initDate, Calendar endDate) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.salary = salary;
		this.initDate = initDate;
		this.endDate = endDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Calendar getInitDate() {
		return initDate;
	}

	public void setInitDate(Calendar initDate) {
		this.initDate = initDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}	

}
