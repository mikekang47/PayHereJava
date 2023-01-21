package com.sihoo.me.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sihoo.me.application.UserService;
import com.sihoo.me.domain.User;
import com.sihoo.me.dto.UserCreateRequest;
import com.sihoo.me.dto.UserResponse;
import com.sihoo.me.dto.UserUpdateRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@GetMapping
	public List<UserResponse> list(@RequestParam int offset, @RequestParam int limit) {
		List<User> users = userService.getUsers(offset, limit);
		return UserResponse.listOf(users);
	}

	@GetMapping("/{id}")
	public UserResponse detail(@PathVariable Long id) {
		User user = userService.getUser(id);
		return user.toResponse();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse create(@RequestBody UserCreateRequest userCreateRequest) {
		User user = userService.createUser(userCreateRequest);
		return user.toResponse();
	}

	@PutMapping("/{id}")
	public UserResponse update(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
		User user = userService.updateUser(id, userUpdateRequest);
		return user.toResponse();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
