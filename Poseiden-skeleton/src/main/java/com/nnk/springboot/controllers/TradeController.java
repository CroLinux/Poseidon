package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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

//import javax.validation.Valid;

@Controller
public class TradeController {
    @Autowired
    TradeService tradeService;
    
    @Autowired
    UserService userService;

    @RequestMapping("/trade/list")
    public String home(Model model, Authentication authentication)
    {
    	Iterable<Trade> trades = tradeService.getTradesList();
		String remoteUser = userService.getCurrentUser(authentication);
		model.addAttribute("trades", trades);
		model.addAttribute("remoteUser", remoteUser);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
    	if (!result.hasErrors()) {
			tradeService.saveTrade(trade);
			model.addAttribute("trades", tradeService.getTradesList());
			return "redirect:/trade/list";
		}
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	Optional<Trade> tradeOptional = tradeService.getTradeById(id);
		Trade tradeToUpdate = tradeOptional.orElse(new Trade());
		model.addAttribute("trade", tradeToUpdate);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
    	if (!result.hasErrors()) {
			trade.setTradeId(id);
			tradeService.saveTrade(trade);
			model.addAttribute("trades", tradeService.getTradesList());
			return "redirect:/trade/list";
    	}
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
    	Optional<Trade> tradeOptional = tradeService.getTradeById(id);
		Trade tradeToDelete = tradeOptional.orElse(new Trade());
		tradeService.deleteTrade(tradeToDelete);
        return "redirect:/trade/list";
    }
}
