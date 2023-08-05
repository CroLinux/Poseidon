package com.nnk.springboot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@SpringBootTest
public class RuleNameServiceTest {

	@Mock
	private RuleNameRepository ruleNameRepository;

	@InjectMocks
	private RuleNameService ruleNameService;

	private RuleName testRuleName;

	@BeforeEach
	public void setup() {
		testRuleName = new RuleName("TestName", "TestDescription", "TestJson", "TestTemplate", "TestSqlStr", "TestSqlPart");
		testRuleName.setId(1);
	}

	@Test
	public void testGetRuleNamesList() {
		// Given
		when(ruleNameRepository.findAll()).thenReturn(java.util.Collections.singletonList(testRuleName));

		// When
		Iterable<RuleName> result = ruleNameService.getRuleNamesList();

		// Then
		assertEquals(1, ((java.util.Collection<?>) result).size());
	}

	@Test
	public void testSaveRuleName() {
		// Given
		when(ruleNameRepository.save(testRuleName)).thenReturn(testRuleName);

		// When
		RuleName savedRuleName = ruleNameService.saveRuleName(testRuleName);

		// Then
		assertEquals(testRuleName, savedRuleName);
	}

	@Test
	public void testGetRuleNameById() {
		// Given
		when(ruleNameRepository.findById(1)).thenReturn(Optional.of(testRuleName));

		// When
		Optional<RuleName> result = ruleNameService.getRuleNameById(1);

		// Then
		assertEquals(testRuleName, result.orElse(null));
	}

	@Test
	public void testDeleteRuleName() {
		// When
		ruleNameService.deleteRuleName(testRuleName);

		// Then
		verify(ruleNameRepository, times(1)).delete(testRuleName);
	}
}
