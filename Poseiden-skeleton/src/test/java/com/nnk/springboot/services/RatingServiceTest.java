package com.nnk.springboot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@SpringBootTest
public class RatingServiceTest {

	@Mock
	private RatingRepository ratingRepository;

	@InjectMocks
	private RatingService ratingService;

	private Rating testRating;

	@BeforeEach
	public void setup() {
		testRating = new Rating("MoodysRating", "SandPRating", "FitchRating", 1);
		testRating.setId(1);
	}

	@Test
	public void testGetRatingsList() {
		// Given
		when(ratingRepository.findAll()).thenReturn(java.util.Collections.singletonList(testRating));

		// When
		Iterable<Rating> result = ratingService.getRatingsList();

		// Then
		assertEquals(1, ((java.util.Collection<?>) result).size());
	}

	@Test
	public void testSaveRating() {
		// Given
		when(ratingRepository.save(testRating)).thenReturn(testRating);

		// When
		Rating savedRating = ratingService.saveRating(testRating);

		// Then
		assertEquals(testRating, savedRating);
	}

	@Test
	public void testGetRatingById() {
		// Given
		when(ratingRepository.findById(1)).thenReturn(Optional.of(testRating));

		// When
		Optional<Rating> result = ratingService.getRatingById(1);

		// Then
		assertEquals(testRating, result.orElse(null));
	}

	@Test
	public void testDeleteRating() {
		// When
		ratingService.deleteRating(testRating);

		// Then
		verify(ratingRepository, times(1)).delete(testRating);
	}
}
