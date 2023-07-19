package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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
public class BidListController {
   
	@Autowired
	BidListService bidListService;
	
	@Autowired
    UserService userService;

    @RequestMapping("/bidList/list")
    public String home(Model model, Authentication authentication)
    {
    	Iterable<BidList> bidLists = bidListService.getBidListsList();
		String remoteUser = userService.getCurrentUser(authentication);
		model.addAttribute("bidLists", bidLists);
		model.addAttribute("remoteUser", remoteUser);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
    	if (!result.hasErrors()) {
			bidListService.saveBidList(bid);
			model.addAttribute("bidLists", bidListService.getBidListsList());
			return "redirect:/bidList/list";
		}
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	Optional<BidList> bidListOptional = bidListService.getBidListById(id);
		BidList bidListToUpdate = bidListOptional.orElse(new BidList());
		model.addAttribute("bidList", bidListToUpdate);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
    	if (!result.hasErrors()) {
			bidList.setBidListId(id);
			bidListService.saveBidList(bidList);
			model.addAttribute("bidLists", bidListService.getBidListsList());
			return "redirect:/bidList/list";
    	}
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	Optional<BidList> bidListOptional = bidListService.getBidListById(id);
		BidList bidListToDelete = bidListOptional.orElse(new BidList());
		bidListService.deleteBidList(bidListToDelete);
        return "redirect:/bidList/list";
    }
}
