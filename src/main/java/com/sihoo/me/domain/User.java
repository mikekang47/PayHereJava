package com.sihoo.me.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sihoo.me.error.PasswordNotMathException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
public class User {
	@Id
	@GeneratedValue
	private Long id;

	private String nickName;

	private String email;

	private String password;

	private boolean isDeleted;

	public static User createUser(String nickName, String email, String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);

		return User.builder()
			.nickName(nickName)
			.email(email)
			.password(encodedPassword)
			.build();
	}

	public void updateUser(String nickName, String currentPassword, String newPassword) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		boolean matches = passwordEncoder.matches(currentPassword, this.password);

		if (!matches) {
			throw new PasswordNotMathException("[Error] 비밀번호가 일치하지 않습니다. (UserId: " + this.id + ")");
		}

		String encodedPassword = passwordEncoder.encode(newPassword);

		this.nickName = nickName;
		this.password = encodedPassword;
	}

	public void deleteUser() {
		this.isDeleted = true;
	}
}
