package com.nnk.springboot.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {


	@GetMapping("/")
	public String slach(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Verification if the user is already connected
		if (authentication != null && authentication.isAuthenticated()) {
		//if (authentication != null) {
			log.info("/ called and User is already authenticated, redirecting to /bidList/list");
			return "redirect:/bidList/list";
		}
			log.info("/ called and User is not authenticated, redirecting to /login");
			return "redirect:/login";
		
	}

	@RequestMapping("/home")
	public String home(Model model) {

		log.info("/home called");
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		log.info("/admin/home called and redirect from /admin/home to /user/list");
		return "redirect:/user/list";
	}

	@GetMapping(value = "/login")
	public String getLogin() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// Verification if the user is already connected
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			log.info("/login called and User is already authenticated, redirecting to /bidList/list");
			return "redirect:/bidList/list";
		}
		log.info("/login called and User is not authenticated, redirecting to /login");
		return "login";
	}

}
