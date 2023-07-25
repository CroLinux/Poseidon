package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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
public class BidListController {

	@Autowired
	BidListService bidListService;

	@Autowired
	UserService userService;

	@RequestMapping("/bidList/list")
	public String home(Model model, Authentication authentication) {
		Iterable<BidList> bidLists = bidListService.getBidListsList();
		String remoteUser = userService.getCurrentUser(authentication);
		model.addAttribute("bidLists", bidLists);
		model.addAttribute("remoteUser", remoteUser);
		log.info("Page bidList/list called by user : " + remoteUser + " list displayed: " + bidLists);
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		log.info("Page bidList/add called");
		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			log.info("Page bidList/validate called without error");
			bidListService.saveBidList(bid);
			log.info("New bidList added: " + bid);
			model.addAttribute("bidLists", bidListService.getBidListsList());
			return "redirect:/bidList/list";
		}
		log.info("Page bidList/validate called but error");
		return "bidList/add";
	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Optional<BidList> bidListOptional = bidListService.getBidListById(id);
		BidList bidListToUpdate = bidListOptional.orElse(new BidList());
		model.addAttribute("bidList", bidListToUpdate);
		log.info("Page bidList/update called for: " + bidListToUpdate);
		return "bidList/update";
	}

	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			bidList.setBidListId(id);
			bidListService.saveBidList(bidList);
			model.addAttribute("bidLists", bidListService.getBidListsList());
			log.info("bidList updated without error " + bidList);
			return "redirect:/bidList/list";
		}
		log.info("Can't update bidList due to error");
		return "redirect:/bidList/list";
	}

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		Optional<BidList> bidListOptional = bidListService.getBidListById(id);
		BidList bidListToDelete = bidListOptional.orElse(new BidList());
		bidListService.deleteBidList(bidListToDelete);
		log.info("Page bidList/delete called for: " + bidListToDelete);
		log.info("bidList deleted");
		return "redirect:/bidList/list";
	}
}
