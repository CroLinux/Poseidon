package com.nnk.springboot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class CurvePointTest {

	private CurvePoint curvePoint;

	@BeforeEach
	public void setUp() {
		curvePoint = new CurvePoint(1, 10.0, 5.0);
	}

	@Test
	public void testDefaultConstructor() {
		CurvePoint defaultCurvePoint = new CurvePoint();
		assertNull(defaultCurvePoint.getId());
		assertNull(defaultCurvePoint.getCurveId());
		assertNull(defaultCurvePoint.getAsOfDate());
		assertNull(defaultCurvePoint.getTerm());
		assertNull(defaultCurvePoint.getValue());
		assertNull(defaultCurvePoint.getCreationDate());
	}

	@Test
	public void testParameterizedConstructor() {
		assertEquals(1, curvePoint.getCurveId());
		assertEquals(10.0, curvePoint.getTerm());
		assertEquals(5.0, curvePoint.getValue());
	}

	@Test
	public void testSettersAndGetters() {
		curvePoint.setId(1);
		assertEquals(1, curvePoint.getId());

		curvePoint.setCurveId(2);
		assertEquals(2, curvePoint.getCurveId());

		LocalDate asOfDate = LocalDate.of(2023, 8, 1);
		curvePoint.setAsOfDate(asOfDate);
		assertEquals(asOfDate, curvePoint.getAsOfDate());

		curvePoint.setTerm(15.0);
		assertEquals(15.0, curvePoint.getTerm());

		curvePoint.setValue(8.0);
		assertEquals(8.0, curvePoint.getValue());

		LocalDate creationDate = LocalDate.of(2023, 8, 1);
		curvePoint.setCreationDate(creationDate);
		assertEquals(creationDate, curvePoint.getCreationDate());
	}
}
