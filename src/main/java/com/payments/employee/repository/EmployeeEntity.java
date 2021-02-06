package com.payments.employee.repository;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.lang.NonNull;

import com.payments.employee.dto.EmployeeDTO;


@Entity
@Table(name = "`employee`")
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NonNull
	private String name;

	@NonNull
	private String lastname;

	@NonNull
	@Temporal(TemporalType.DATE)
	private Calendar initDate;

	@Temporal(TemporalType.DATE)
	private Calendar endDate;

	@NonNull
	@Column(name = "salary", precision = 10, scale = 2)
	private BigDecimal salary;

	protected EmployeeEntity() {
	}

	public EmployeeEntity(EmployeeDTO newEmployee) {
		super();		
		this.name = newEmployee.name;
		this.lastname = newEmployee.lastname;
		this.initDate = newEmployee.initDate;
		this.endDate = newEmployee.endDate;
		this.salary = newEmployee.salary;
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

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	public void setAll(EmployeeDTO employeeToUpdate) {
		this.name = employeeToUpdate.name;
		this.lastname = employeeToUpdate.lastname;
		this.initDate = employeeToUpdate.initDate;
		this.endDate = employeeToUpdate.endDate;
		this.salary = employeeToUpdate.salary;			
	}
	
	public HashMap<String, Object> getPublicData() {
		HashMap<String, Object> employee = new HashMap<>();
		employee.put("name", getName());
		employee.put("lastname", getLastname());
		employee.put("initDate", getInitDate());
		employee.put("endDate", getEndDate());
		employee.put("salary", getSalary());		   
	    return employee;
	}	

}
