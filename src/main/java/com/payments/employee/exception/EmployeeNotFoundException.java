package com.payments.employee.exception;

public class EmployeeNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
