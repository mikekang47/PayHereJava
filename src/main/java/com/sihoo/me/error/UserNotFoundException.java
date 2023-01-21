package com.sihoo.me.error;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(Long id) {
		super("[ERROR] 사용자를 찾을 수 없습니다.(id: " + id + ")");
	}
}
