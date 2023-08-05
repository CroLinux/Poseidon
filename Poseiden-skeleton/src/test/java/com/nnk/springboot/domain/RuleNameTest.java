package com.nnk.springboot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class RuleNameTest {

    private RuleName ruleName;

    @BeforeEach
    public void setUp() {
        ruleName = new RuleName("Rule Name", "Description", "JSON", "Template", "SQL String", "SQL Part");
    }

    @Test
    public void testDefaultConstructor() {
        RuleName defaultRuleName = new RuleName();
        assertNull(defaultRuleName.getId());
        assertNull(defaultRuleName.getName());
        assertNull(defaultRuleName.getDescription());
        assertNull(defaultRuleName.getJson());
        assertNull(defaultRuleName.getTemplate());
        assertNull(defaultRuleName.getSqlStr());
        assertNull(defaultRuleName.getSqlPart());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("Rule Name", ruleName.getName());
        assertEquals("Description", ruleName.getDescription());
        assertEquals("JSON", ruleName.getJson());
        assertEquals("Template", ruleName.getTemplate());
        assertEquals("SQL String", ruleName.getSqlStr());
        assertEquals("SQL Part", ruleName.getSqlPart());
    }

    @Test
    public void testSettersAndGetters() {
        ruleName.setId(1);
        assertEquals(1, ruleName.getId());

        ruleName.setName("RuleName");
        assertEquals("RuleName", ruleName.getName());

        ruleName.setDescription("Description");
        assertEquals("Description", ruleName.getDescription());

        ruleName.setJson("JSON");
        assertEquals("JSON", ruleName.getJson());

        ruleName.setTemplate("Template");
        assertEquals("Template", ruleName.getTemplate());

        ruleName.setSqlStr("SQLStr");
        assertEquals("SQLStr", ruleName.getSqlStr());

        ruleName.setSqlPart("SQLPart");
        assertEquals("SQLPart", ruleName.getSqlPart());
    }

}

