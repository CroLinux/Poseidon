package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import com.nnk.springboot.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RuleNameControllerTest {

    @Mock
    private RuleNameService ruleNameService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private RuleNameController ruleNameController;

    private List<RuleName> ruleNames;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ruleNames = new ArrayList<>();
        ruleNames.add(new RuleName("Name1", "Description1", "Json1", "Template1", "SqlStr1", "SqlPart1"));
        ruleNames.add(new RuleName("Name2", "Description2", "Json2", "Template2", "SqlStr2", "SqlPart2"));
    }

    @Test
    public void testHome() {
        // Given
        when(ruleNameService.getRuleNamesList()).thenReturn(ruleNames);
        when(userService.getCurrentUser(any())).thenReturn("user");

        // When
        String viewName = ruleNameController.home(model, null);

        // Then
        assertEquals("ruleName/list", viewName);
        verify(model).addAttribute("ruleNames", ruleNames);
        verify(model).addAttribute("remoteUser", "user");
    }

    @Test
    public void testAddRuleForm() {
    	// Given
    	
        // When
        String viewName = ruleNameController.addRuleForm(new RuleName());

        // Then
        assertEquals("ruleName/add", viewName);
    }

    @Test
    public void testValidateWithValidRuleName() {
        // Given
        RuleName ruleName = new RuleName("Name", "Description", "Json", "Template", "SqlStr", "SqlPart");
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        // When
        String viewName = ruleNameController.validate(ruleName, result, model);

        // Then
        assertEquals("redirect:/ruleName/list", viewName);
        verify(ruleNameService).saveRuleName(ruleName);
        verify(model).addAttribute("ruleNames", ruleNameService.getRuleNamesList());
    }

    @Test
    public void testValidateWithInvalidRuleName() {
        // Given
        RuleName ruleName = new RuleName();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // When
        String viewName = ruleNameController.validate(ruleName, result, model);

        // Then
        assertEquals("ruleName/add", viewName);
        verify(ruleNameService, never()).saveRuleName(any());
    }

    @Test
    public void testShowUpdateForm() {
        // Given
        Integer ruleNameId = 1;
        RuleName ruleNameToUpdate = new RuleName("Name1", "Description1", "Json1", "Template1", "SqlStr1", "SqlPart1");
        when(ruleNameService.getRuleNameById(ruleNameId)).thenReturn(Optional.of(ruleNameToUpdate));

        // When
        String viewName = ruleNameController.showUpdateForm(ruleNameId, model);

        // Then
        assertEquals("ruleName/update", viewName);
        verify(model).addAttribute("ruleName", ruleNameToUpdate);
    }

    @Test
    public void testUpdateRuleNameWithValidRuleName() {
        // Given
        Integer ruleNameId = 1;
        RuleName ruleName = new RuleName("Name", "Description", "Json", "Template", "SqlStr", "SqlPart");
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(ruleNameService.saveRuleName(ruleName)).thenReturn(ruleName);

        // When
        String viewName = ruleNameController.updateRuleName(ruleNameId, ruleName, result, model);

        // Then
        assertEquals("redirect:/ruleName/list", viewName);
        verify(ruleNameService).saveRuleName(ruleName);
        verify(model).addAttribute("ruleNames", ruleNameService.getRuleNamesList());
    }

    @Test
    public void testUpdateRuleNameWithInvalidRuleName() {
        // Given
        Integer ruleNameId = 1;
        RuleName ruleName = new RuleName();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // When
        String viewName = ruleNameController.updateRuleName(ruleNameId, ruleName, result, model);

        // Then
        assertEquals("redirect:/ruleName/list", viewName);
        verify(ruleNameService, never()).saveRuleName(any());
    }

    @Test
    public void testDeleteRuleName() {
        // Given
        Integer ruleNameId = 1;
        RuleName ruleNameToDelete = new RuleName("Name1", "Description1", "Json1", "Template1", "SqlStr1", "SqlPart1");
        when(ruleNameService.getRuleNameById(ruleNameId)).thenReturn(Optional.of(ruleNameToDelete));

        // When
        String viewName = ruleNameController.deleteRuleName(ruleNameId, model);

        // Then
        assertEquals("redirect:/ruleName/list", viewName);
        verify(ruleNameService).deleteRuleName(ruleNameToDelete);
    }
}
