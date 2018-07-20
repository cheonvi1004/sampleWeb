package com.chunsik.controller;

import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController extends BaseController {

	@GetMapping(value = "/loginResult")
	public @ResponseBody String loginResult(@RequestParam Optional<String> error,
			@RequestParam Optional<String> success) {
		return success.isPresent() ? "SUCCESS" : "ERROR";
	}

	@PreAuthorize("!hasAuthority('USER') && !hasAuthority('ADMIN')")
	@GetMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute(LAYOUT_MAIN_CONTENT, "login.html");
		return layout;
	}

}
