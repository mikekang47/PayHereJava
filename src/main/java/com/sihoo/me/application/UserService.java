package com.sihoo.me.application;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sihoo.me.domain.User;
import com.sihoo.me.dto.UserCreateRequest;
import com.sihoo.me.dto.UserUpdateRequest;
import com.sihoo.me.error.PasswordNotMatchException;
import com.sihoo.me.error.UserNotFoundException;
import com.sihoo.me.infra.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public List<User> getUsers(int offset, int limit) {
		Pageable pageable = PageRequest.of(offset, limit);
		return userRepository.findAll(pageable).getContent();
	}

	@Transactional
	public User createUser(UserCreateRequest userCreateRequest) {
		if (!userCreateRequest.getPassword().equals(userCreateRequest.getRetypePassword())) {
			throw new PasswordNotMatchException("[ERROR] 입력한 비밀번호가 일치하지 않습니다.");
		}

		User user = User.createUser(userCreateRequest.getNickName(), userCreateRequest.getEmail(),
			userCreateRequest.getPassword(),
			passwordEncoder);

		return userRepository.save(user);
	}

	public User getUser(Long id) {
		return findUser(id);
	}

	@Transactional
	public User updateUser(Long id, UserUpdateRequest userUpdateRequest) {
		User user = findUser(id);

		user.updateUser(userUpdateRequest.getNickName(), userUpdateRequest.getEmail(), userUpdateRequest.getPassword(),
			passwordEncoder);

		return user;
	}

	@Transactional
	public void deleteUser(Long id) {
		User user = findUser(id);

		user.deleteUser();
	}

	private User findUser(Long id) {
		return userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException(id));
	}
}
