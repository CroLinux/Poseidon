package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@RequestMapping("/")
	public String home(Model model) {

		log.info("redirect from / to /home");
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		log.info("redirect from /admin/home to /user/list");
		return "redirect:/user/list";
	}

}
