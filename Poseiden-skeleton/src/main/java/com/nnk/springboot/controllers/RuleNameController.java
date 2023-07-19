package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
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
public class RuleNameController {
    
	@Autowired
	RuleNameService ruleNameService;
	
	@Autowired
	UserService userService;

    @RequestMapping("/ruleName/list")
    public String home(Model model, Authentication authentication)
    {
    	Iterable<RuleName> ruleNames = ruleNameService.getRuleNamesList();
    	System.out.println(ruleNames);
		String remoteUser = userService.getCurrentUser(authentication);
		model.addAttribute("ruleNames", ruleNames);
		model.addAttribute("remoteUser", remoteUser);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
    	if (!result.hasErrors()) {
			ruleNameService.saveRuleName(ruleName);
			model.addAttribute("ruleNames", ruleNameService.getRuleNamesList());
			return "redirect:/ruleName/list";
		}
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	Optional<RuleName> ruleNameOptional = ruleNameService.getRuleNameById(id);
		RuleName ruleNameToUpdate = ruleNameOptional.orElse(new RuleName());
		model.addAttribute("ruleName", ruleNameToUpdate);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
    	if (!result.hasErrors()) {
			ruleName.setId(id);
			ruleNameService.saveRuleName(ruleName);
			model.addAttribute("ruleNames", ruleNameService.getRuleNamesList());
			return "redirect:/ruleName/list";
    	}
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    	Optional<RuleName> ruleNameOptional = ruleNameService.getRuleNameById(id);
		RuleName ruleNameToDelete = ruleNameOptional.orElse(new RuleName());
		ruleNameService.deleteRuleName(ruleNameToDelete);
        return "redirect:/ruleName/list";
    }
}
