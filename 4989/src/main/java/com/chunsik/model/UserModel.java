package com.chunsik.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserModel {
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	@Size(min = 6, max = 30, message = "이메일은 6자에서 30자 사이로 입력해주세요.")
	private String email;

	@Size(min = 6, max = 15, message = "패스워드는 6자에서 15자 사이로 입력해주세요.")
	private String password;

	@NotEmpty(message = "패스워드를 한번 더 입력 해주세요.")
	private String confirmPassword;

	@Size(min = 2, max = 30, message = "이름은 2자에서 30자 사이로 입력해주세요.")
	private String name;

}
