package com.sihoo.me.error;

public class PasswordNotMatchException extends RuntimeException {
	public PasswordNotMatchException(String message) {
		super("PasswordNotMathException: " + message);
	}
}
