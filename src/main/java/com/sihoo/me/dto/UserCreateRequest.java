package com.sihoo.me.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserCreateRequest extends UserBaseRequest {
	@NotEmpty
	private String retypePassword;
}
