package com.nnk.springboot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@RequestMapping("/")
	public String slach(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Verification if the user is already connected
		// if (authentication != null && authentication.isAuthenticated()) {
		if (authentication != null) {
			log.info("User is already authenticated, redirecting to /home");
			return "redirect:/bidList/list";
		} else {
			log.info("User is not authenticated, redirecting to /login");
			return "redirect:/login";
		}
	}

	@RequestMapping("/home")
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
