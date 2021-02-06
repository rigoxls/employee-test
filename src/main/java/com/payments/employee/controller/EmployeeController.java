package com.payments.employee.controller;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.payments.employee.dto.EmployeeDTO;
import com.payments.employee.repository.EmployeeEntity;
import com.payments.employee.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee/{EmployeeId}")
	public Optional<EmployeeEntity> getEmployeeById(@PathVariable long EmployeeId) {
		Optional<EmployeeEntity> Employee = employeeService.getEmployeeById(EmployeeId);
		return Employee;
	}

	@GetMapping("/employee/list")
	public List<EmployeeEntity> getAllEmployees() {
		List<EmployeeEntity> Employees = employeeService.getAllEmployees();
		return Employees;
	}

	@PostMapping("/employee/create")
	public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeDTO newEmployee) throws IOException {
		EmployeeEntity Employee = employeeService.createEmployee(newEmployee);
		if (Employee == null) {
			return ResponseEntity.noContent().build();
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Employee.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/employee/update")
	public HashMap<String, Object> updateEmployee(@Valid @RequestBody EmployeeDTO EmployeeToUpdate) throws Exception {
		EmployeeEntity Employee = employeeService.updateEmployee(EmployeeToUpdate);
		return Employee.getPublicData();
	}

	@DeleteMapping("/employee/{EmployeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable long EmployeeId) {
		employeeService.deleteEmployee(EmployeeId);
		return ResponseEntity.noContent().build();
	}
}
