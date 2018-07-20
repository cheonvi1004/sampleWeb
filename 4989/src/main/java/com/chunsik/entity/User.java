package com.chunsik.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
@Document(collection = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String email;

	private String name;

	private String passwordHash;

	private Role role;

	public static User build(long id, String email, String passwd, String name, Role role) {
		return builder().id(id).email(email).name(name).role(role)
				.passwordHash(new BCryptPasswordEncoder().encode(passwd)).build();
	}

}
