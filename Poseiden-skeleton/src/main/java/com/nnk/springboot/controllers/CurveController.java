package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import com.nnk.springboot.services.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

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

@Slf4j
@Controller
public class CurveController {

	@Autowired
	CurvePointService curvePointService;

	@Autowired
	UserService userService;

	@RequestMapping("/curvePoint/list")
	public String home(Model model, Authentication authentication) {
		Iterable<CurvePoint> curvePoints = curvePointService.getCurvePointsList();
		String remoteUser = userService.getCurrentUser(authentication);
		model.addAttribute("curvePoints", curvePoints);
		model.addAttribute("remoteUser", remoteUser);
		log.info("Page curvePoint/list called by user : " + remoteUser + " list displayed: " + curvePoints);
		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		log.info("Page curvePoint/add called");
		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			log.info("Page curvePoint/validate called without error");
			curvePointService.saveCurvePoint(curvePoint);
			log.info("New curvePoint added: " + curvePoint);
			model.addAttribute("curvePoints", curvePointService.getCurvePointsList());
			return "redirect:/curvePoint/list";
		}
		log.info("Page curvePoint/validate called but error");
		return "curvePoint/add";
	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Optional<CurvePoint> curvePointOptional = curvePointService.getCurvePointById(id);
		CurvePoint curvePointToUpdate = curvePointOptional.orElse(new CurvePoint());
		model.addAttribute("curvePoint", curvePointToUpdate);
		log.info("Page curvePoint/update called for: " + curvePointToUpdate);
		return "curvePoint/update";
	}

	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		if (!result.hasErrors()) {
			curvePoint.setId(id);
			curvePointService.saveCurvePoint(curvePoint);
			model.addAttribute("curvePoints", curvePointService.getCurvePointsList());
			log.info("bidList updated without error " + curvePoint);
			return "redirect:/curvePoint/list";
		}
		log.info("Can't update curvePoint due to error");
		return "curvePoint/update";

	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		Optional<CurvePoint> curvePointOptional = curvePointService.getCurvePointById(id);
		CurvePoint curvePointToDelete = curvePointOptional.orElse(new CurvePoint());
		curvePointService.deleteCurvePoint(curvePointToDelete);
		log.info("Page curvePoint/delete called for: " + curvePointToDelete);
		log.info("curvePoint deleted");
		return "redirect:/curvePoint/list";
	}
}
