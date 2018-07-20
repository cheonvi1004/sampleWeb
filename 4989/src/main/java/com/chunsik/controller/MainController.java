package com.chunsik.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController extends BaseController {

	@GetMapping(value = { "/", "/hello" })
	public String hello(HttpServletRequest request, Model model) {
		model.addAttribute(LAYOUT_MAIN_CONTENT, "hello.html");
		log.info("session id : {}", request.getSession().getId());
		return layout;
	}

}
