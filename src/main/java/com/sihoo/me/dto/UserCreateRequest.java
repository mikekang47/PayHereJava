package com.sihoo.me.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserCreateRequest extends UserBaseRequest {
	private String retypePassword;
}
