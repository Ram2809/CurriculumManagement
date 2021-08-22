package com.curriculum.exception;

public class DatabaseException extends Exception {
	public DatabaseException(String message,Throwable e) {
		super(message,e);
	}
	public DatabaseException(String message) {
		super(message);
	}
}
