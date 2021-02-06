package com.payments.employee.exception;

public class WrongRequestDateException extends Exception {
	private static final long serialVersionUID = 1L;

	public WrongRequestDateException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
