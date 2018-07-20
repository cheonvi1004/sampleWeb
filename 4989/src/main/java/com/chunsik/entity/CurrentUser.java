package com.chunsik.entity;

import org.springframework.security.core.authority.AuthorityUtils;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CurrentUser extends org.springframework.security.core.userdetails.User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	public CurrentUser(User user) {
		super(user.getEmail(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}

	public long getId() {
		return user.getId();
	}

	public String getName() {
		return user.getName();
	}

	public String getEmail() {
		return user.getEmail();
	}

	public Role getRole() {
		return user.getRole();
	}

}
