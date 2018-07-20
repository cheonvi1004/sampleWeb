package com.chunsik.config.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.chunsik.entity.CurrentUser;
import com.chunsik.entity.User;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class InjectUserInfoAdvice {

	@After("execution(* com.chunsik.controller..*Controller*.*(..))")
	public void after(JoinPoint joinPoint) throws Throwable {
		Object objects[] = joinPoint.getArgs();

		User user;
		try {
			user = ((CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
			log.info("login user info : {}", user);
		} catch (Exception e) {
			user = null;
		}

		for (Object obj : objects) {
			if (obj instanceof Model) {
				Model model = (Model) obj;
				model.addAttribute("loginUser", user);
			}
		}
	}

}
