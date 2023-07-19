package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameService {
	
	@Autowired
	private RuleNameRepository ruleNameRepository;
	

    public Iterable<RuleName> getRuleNamesList() {
        return ruleNameRepository.findAll();
    }


    public RuleName saveRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }


    public Optional<RuleName> getRuleNameById(Integer id) {
        return ruleNameRepository.findById(id);
    }


    public void deleteRuleName(RuleName ruleName) {
        ruleNameRepository.delete(ruleName);
    }

}
