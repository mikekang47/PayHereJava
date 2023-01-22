package com.sihoo.me.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.sihoo.me.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponse {
	private Long id;

	private String nickName;

	private String email;

	public static List<UserResponse> listOf(List<User> users) {
		return users.stream()
			.map(User::toResponse)
			.collect(Collectors.toList());
	}
}
