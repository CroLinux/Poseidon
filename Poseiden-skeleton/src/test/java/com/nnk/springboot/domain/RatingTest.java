package com.nnk.springboot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class RatingTest {

	private Rating rating;

	@BeforeEach
	public void setUp() {
		rating = new Rating("Moody's Rating", "S&P Rating", "Fitch Rating", 1);
	}

	@Test
	public void testDefaultConstructor() {
		Rating defaultRating = new Rating();
		assertNull(defaultRating.getId());
		assertNull(defaultRating.getMoodysRating());
		assertNull(defaultRating.getSandPRating());
		assertNull(defaultRating.getFitchRating());
		assertNull(defaultRating.getOrderNumber());
	}

	@Test
	public void testParameterizedConstructor() {
		assertEquals("Moody's Rating", rating.getMoodysRating());
		assertEquals("S&P Rating", rating.getSandPRating());
		assertEquals("Fitch Rating", rating.getFitchRating());
		assertEquals(1, rating.getOrderNumber());
	}

	@Test
	public void testSettersAndGetters() {
		rating.setId(10);
		assertEquals(10, rating.getId());

		rating.setMoodysRating("Updated Moody's Rating");
		assertEquals("Updated Moody's Rating", rating.getMoodysRating());

		rating.setSandPRating("Updated S&P Rating");
		assertEquals("Updated S&P Rating", rating.getSandPRating());

		rating.setFitchRating("Updated Fitch Rating");
		assertEquals("Updated Fitch Rating", rating.getFitchRating());

		rating.setOrderNumber(2);
		assertEquals(2, rating.getOrderNumber());
	}

}
