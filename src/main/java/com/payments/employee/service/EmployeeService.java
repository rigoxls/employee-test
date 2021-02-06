package com.payments.employee.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payments.employee.dto.EmployeeDTO;
import com.payments.employee.dto.PaymentDTO;
import com.payments.employee.exception.EmployeeNotFoundException;
import com.payments.employee.exception.WrongRequestDateException;
import com.payments.employee.repository.EmployeeEntity;
import com.payments.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

	private int DAYS_OF_MONTH = 30;

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

	public PaymentDTO getEmployeePay(long employeeId, int month, int year) throws WrongRequestDateException {
		BigDecimal salary = new BigDecimal(0);
		int monthWorkedDays = 0;
		Calendar requestDate = Calendar.getInstance();
		EmployeeEntity employee = employeeRepository.getOne(employeeId);

		try {
			requestDate.set(year, month, 1);
		} catch (Exception err) {
			throw new WrongRequestDateException("Wrong date format : ", err);
		}

		if (requestDate.getTimeInMillis() < employee.getInitDate().getTimeInMillis()) {
			LOG.error("Request date should be lower than init Date");
		} else {
			int initDateDay = employee.getInitDate().get(Calendar.DAY_OF_MONTH);

			if (employee.getEndDate() == null) {
				monthWorkedDays = (DAYS_OF_MONTH - initDateDay) + 1;
				salary = payment(employee, monthWorkedDays);
			} else {
				long daysWorked = employee.getEndDate().getTimeInMillis() - employee.getInitDate().getTimeInMillis();
				daysWorked = (daysWorked / (1000 * 60 * 60 * 24)) + 1;

				if (daysWorked > DAYS_OF_MONTH) {
					monthWorkedDays = (DAYS_OF_MONTH - initDateDay) + 1;
					salary = payment(employee, monthWorkedDays);
				} else {
					int endDateDay = employee.getEndDate().get(Calendar.DAY_OF_MONTH);
					monthWorkedDays = (endDateDay - initDateDay) + 1;
					salary = payment(employee, monthWorkedDays);
				}
			}
		}
		
		PaymentDTO paymentRes = new PaymentDTO(employeeId, month, year, salary);

		return paymentRes;
	}

	public BigDecimal payment(EmployeeEntity employee, int monthWorkedDays) {
		return employee.getSalary().divide(new BigDecimal(DAYS_OF_MONTH), 2, RoundingMode.CEILING)
				.multiply(new BigDecimal(monthWorkedDays));
	}
}
