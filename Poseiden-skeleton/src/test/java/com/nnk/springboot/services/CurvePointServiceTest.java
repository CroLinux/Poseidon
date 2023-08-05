package com.nnk.springboot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@SpringBootTest
public class CurvePointServiceTest {

	@Mock
	private CurvePointRepository curvePointRepository;

	@InjectMocks
	private CurvePointService curvePointService;

	private CurvePoint testCurvePoint;

	@BeforeEach
	public void setup() {
		testCurvePoint = new CurvePoint(1, 10.0, 5.0);
		testCurvePoint.setId(1);
		testCurvePoint.setAsOfDate(LocalDate.now());
	}

	@Test
	public void testGetCurvePointsList() {
		// Given
		when(curvePointRepository.findAll()).thenReturn(java.util.Collections.singletonList(testCurvePoint));

		// When
		Iterable<CurvePoint> result = curvePointService.getCurvePointsList();

		// Then
		assertEquals(1, ((java.util.Collection<?>) result).size());
	}

	@Test
	public void testSaveCurvePoint() {
		// Given
		when(curvePointRepository.save(testCurvePoint)).thenReturn(testCurvePoint);

		// When
		CurvePoint savedCurvePoint = curvePointService.saveCurvePoint(testCurvePoint);

		// Then
		assertEquals(testCurvePoint, savedCurvePoint);
	}

	@Test
	public void testGetCurvePointById() {
		// Given
		when(curvePointRepository.findById(1)).thenReturn(Optional.of(testCurvePoint));

		// When
		Optional<CurvePoint> result = curvePointService.getCurvePointById(1);

		// Then
		assertEquals(testCurvePoint, result.orElse(null));
	}

	@Test
	public void testDeleteCurvePoint() {
		// When
		curvePointService.deleteCurvePoint(testCurvePoint);

		// Then
		verify(curvePointRepository, times(1)).delete(testCurvePoint);
	}
}
