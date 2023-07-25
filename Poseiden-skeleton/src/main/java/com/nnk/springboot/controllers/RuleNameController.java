package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
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
public class RuleNameController {
    
	@Autowired
	RuleNameService ruleNameService;
	
	@Autowired
	UserService userService;

    @RequestMapping("/ruleName/list")
    public String home(Model model, Authentication authentication)
    {
    	Iterable<RuleName> ruleNames = ruleNameService.getRuleNamesList();
		String remoteUser = userService.getCurrentUser(authentication);
		model.addAttribute("ruleNames", ruleNames);
		model.addAttribute("remoteUser", remoteUser);
		log.info("Page ruleName/list called by user : " + remoteUser + " list displayed: " + ruleNames);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
    	log.info("Page ruleName/add called");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
    	if (!result.hasErrors()) {
    		log.info("Page ruleName/validate called without error");
			ruleNameService.saveRuleName(ruleName);
			log.info("New ruleName added: " + ruleName);
			model.addAttribute("ruleNames", ruleNameService.getRuleNamesList());
			return "redirect:/ruleName/list";
		}
    	log.info("Page ruleName/validate called but error");
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	Optional<RuleName> ruleNameOptional = ruleNameService.getRuleNameById(id);
		RuleName ruleNameToUpdate = ruleNameOptional.orElse(new RuleName());
		model.addAttribute("ruleName", ruleNameToUpdate);
		log.info("Page ruleName/update called for: " + ruleNameToUpdate);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
    	if (!result.hasErrors()) {
			ruleName.setId(id);
			ruleNameService.saveRuleName(ruleName);
			model.addAttribute("ruleNames", ruleNameService.getRuleNamesList());
			log.info("ruleName updated without error " + ruleName);
			return "redirect:/ruleName/list";
    	}
    	log.info("Can't update ruleName due to error");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    	Optional<RuleName> ruleNameOptional = ruleNameService.getRuleNameById(id);
		RuleName ruleNameToDelete = ruleNameOptional.orElse(new RuleName());
		ruleNameService.deleteRuleName(ruleNameToDelete);
		log.info("Page ruleName/delete called for: " + ruleNameToDelete);
		log.info("ruleName deleted");
        return "redirect:/ruleName/list";
    }
}
