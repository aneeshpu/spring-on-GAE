package com.aneeshpu.gae.domain.repository;

public class QuickThoughtPersistenceException extends RuntimeException {

	public QuickThoughtPersistenceException(String message, Throwable exception) {
		super(message, exception);
		exception.printStackTrace();
	}
	
}
