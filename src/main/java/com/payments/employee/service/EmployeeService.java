package com.payments.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payments.employee.dto.EmployeeDTO;
import com.payments.employee.exception.EmployeeNotFoundException;
import com.payments.employee.repository.EmployeeEntity;
import com.payments.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	protected EmployeeService() {
		
	}

	public EmployeeEntity createEmployee(EmployeeDTO newEmployee) {
		EmployeeEntity employee = new EmployeeEntity(newEmployee);
		System.out.println(employee);
		employeeRepository.save(employee);
		return employee;
	}

	public EmployeeEntity updateEmployee(EmployeeDTO employeeToUpdate) throws EmployeeNotFoundException {
		Long employeeId = employeeToUpdate.getId();
		try {
			EmployeeEntity employee = employeeRepository.getOne(employeeId);
			employee.setAll(employeeToUpdate);
			employeeRepository.save(employee);
			return employee;
		} catch (Exception err) {
			throw new EmployeeNotFoundException("Employee not found : " + employeeId, err);
		}
	}

	public Optional<EmployeeEntity> getEmployeeById(long employeeId) {
		Optional<EmployeeEntity> employee = employeeRepository.findById(employeeId);
		return employee;
	}

	public List<EmployeeEntity> getAllEmployees() {
		List<EmployeeEntity> employees = employeeRepository.findAll();
		return employees;
	}

	public Void deleteEmployee(long employeeId) {
		employeeRepository.deleteById(employeeId);
		return null;
	}
}
