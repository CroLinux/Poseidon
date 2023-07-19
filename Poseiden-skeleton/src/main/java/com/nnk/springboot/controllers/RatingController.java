package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.UserService;

import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	
	@Autowired
	UserService userService;

    @RequestMapping("/rating/list")
    public String home(Model model, Authentication authentication)
    {
    	Iterable<Rating> ratings = ratingService.getRatingsList();
		String remoteUser = userService.getCurrentUser(authentication);
		model.addAttribute("ratings", ratings);
		model.addAttribute("remoteUser", remoteUser);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
    	if (!result.hasErrors()) {
			ratingService.saveRating(rating);
			model.addAttribute("ratings", ratingService.getRatingsList());
			return "redirect:/rating/list";
		}
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	Optional<Rating> ratingOptional = ratingService.getRatingById(id);
		Rating ratingToUpdate = ratingOptional.orElse(new Rating());
		model.addAttribute("rating", ratingToUpdate);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
    	if (!result.hasErrors()) {
			rating.setId(id);
			ratingService.saveRating(rating);
			model.addAttribute("ratings", ratingService.getRatingsList());
			return "redirect:/rating/list";
    	}
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
    	Optional<Rating> ratingOptional = ratingService.getRatingById(id);
		Rating ratingToDelete = ratingOptional.orElse(new Rating());
		ratingService.deleteRating(ratingToDelete);
        return "redirect:/rating/list";
    }
}
