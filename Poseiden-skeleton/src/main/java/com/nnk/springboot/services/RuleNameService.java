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
	
	/**
	 * Get all the Rules from the database
	 * 
	 * @return
	 */
    public Iterable<RuleName> getRuleNamesList() {
        return ruleNameRepository.findAll();
    }

    /**
     * Save/Modify a rule into the database
     * 
     * @param ruleName
     * @return
     */
    public RuleName saveRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Get a specific rule from the database
     * 
     * @param id
     * @return
     */
    public Optional<RuleName> getRuleNameById(Integer id) {
        return ruleNameRepository.findById(id);
    }

    /**
     * Delete a specific rule from the database
     * 
     * @param ruleName
     */
    public void deleteRuleName(RuleName ruleName) {
        ruleNameRepository.delete(ruleName);
    }

}
