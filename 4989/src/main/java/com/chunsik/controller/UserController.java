package com.chunsik.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.chunsik.entity.User;
import com.chunsik.model.UserModel;
import com.chunsik.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/user/register")
	public String register(Model model) {
		model.addAttribute(LAYOUT_MAIN_CONTENT, "/user/register.html");
		model.addAttribute("userModel", new UserModel());

		return layout;
	}

	@PostMapping(value = "/user/register")
	public String register(@Valid UserModel userModel, BindingResult result, Model model) {
		if (!userModel.getPassword().equals(userModel.getConfirmPassword())) {
			result.rejectValue("password", null, "패스워드를 확인 해주세요.");
			result.rejectValue("confirmPassword", null, "패스워드를 확인 해주세요.");
		}

		if (!result.hasErrors()) {
			try {
				User user = userService.create(userModel);
				if (user != null) {
					log.info("user info : {}", user);
					return "redirect:/login";
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				if (e.getMessage().contains("E11000 duplicate key")) {
					result.rejectValue("email", null, "이미 존재하는 이메일 입니다.");
				} else {
					throw e;
				}
			}
		}

		model.addAttribute("result", result);
		model.addAttribute("userModel", userModel);
		model.addAttribute(LAYOUT_MAIN_CONTENT, "/user/register.html");

		return layout;
	}

}
