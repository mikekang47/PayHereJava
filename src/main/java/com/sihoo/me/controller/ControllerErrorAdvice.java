package com.sihoo.me.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sihoo.me.dto.ErrorResponse;
import com.sihoo.me.error.PasswordNotMatchException;
import com.sihoo.me.error.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerErrorAdvice {
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException(Exception e) {
		log.error("Exception occurred: {}", e.getMessage(), e);
		return new ErrorResponse(e.getMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleUserNotFoundException(UserNotFoundException e) {
		log.error("UserNotFoundException occurred: {}", e.getMessage(), e);
		return new ErrorResponse(e.getMessage());
	}

	@ExceptionHandler(PasswordNotMatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handlePasswordNotMatchException(PasswordNotMatchException e) {
		log.error("PasswordNotMatchException occurred: {}", e.getMessage(), e);
		return new ErrorResponse(e.getMessage());
	}
}
