package com.chunsik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chunsik.entity.CurrentUser;
import com.chunsik.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurrentUserDetailService implements UserDetailsService {
	@Autowired
	private UserService userService;

	@Override
	public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.getUserByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new CurrentUser(user);
	}

}
