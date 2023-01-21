package com.sihoo.me.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserBaseRequest {
	@Email
	@NotEmpty
	private String email;

	private String nickName;

	private String password;
}
