package com.chunsik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.chunsik.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PostController extends BaseController {

	@Autowired
	private PostService postService;

//	@PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
	@GetMapping(value = "/post")
	public String post(Model model) {
		model.addAttribute(LAYOUT_MAIN_CONTENT, "/post/post.html");
		return layout;
	}

	@GetMapping(value = "/article/{id}")
	public String article(@PathVariable String id, Model model) {
		log.info("{}", id);
		model.addAttribute("post", postService.getArticle(Long.valueOf(id)));
		model.addAttribute(LAYOUT_MAIN_CONTENT, "/post/article.html");
		return layout;
	}

}
