package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
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

class RatingControllerTest {

    @Mock
    private RatingService ratingService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private RatingController ratingController;

    private List<Rating> ratings;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ratings = new ArrayList<>();
        ratings.add(new Rating("Moody's1", "S and P1", "Fitch1", 1));
        ratings.add(new Rating("Moody's2", "S and P2", "Fitch2", 2));
    }

    @Test
    public void testHome() {
        // Given
        when(ratingService.getRatingsList()).thenReturn(ratings);
        when(userService.getCurrentUser(any())).thenReturn("user");

        // When
        String viewName = ratingController.home(model, null);

        // Then
        assertEquals("rating/list", viewName);
        verify(model).addAttribute("ratings", ratings);
        verify(model).addAttribute("remoteUser", "user");
    }

    @Test
    public void testAddRatingForm() {
    	// Given
    	
        // When
        String viewName = ratingController.addRatingForm(new Rating());

        // Then
        assertEquals("rating/add", viewName);
    }

    @Test
    public void testValidateWithValidRating() {
        // Given
        Rating rating = new Rating("Moody's", "S and P", "Fitch", 1);
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        // When
        String viewName = ratingController.validate(rating, result, model);

        // Then
        assertEquals("redirect:/rating/list", viewName);
        verify(ratingService).saveRating(rating);
        verify(model).addAttribute("ratings", ratingService.getRatingsList());
    }

    @Test
    public void testValidateWithInvalidRating() {
        // Given
        Rating rating = new Rating();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // When
        String viewName = ratingController.validate(rating, result, model);

        // Then
        assertEquals("rating/add", viewName);
        verify(ratingService, never()).saveRating(any());
    }

    @Test
    public void testShowUpdateForm() {
        // Given
        Integer ratingId = 1;
        Rating ratingToUpdate = new Rating("Moody's1", "S and P1", "Fitch1", 1);
        when(ratingService.getRatingById(ratingId)).thenReturn(Optional.of(ratingToUpdate));

        // When
        String viewName = ratingController.showUpdateForm(ratingId, model);

        // Then
        assertEquals("rating/update", viewName);
        verify(model).addAttribute("rating", ratingToUpdate);
    }

    @Test
    public void testUpdateRatingWithValidRating() {
        // Given
        Integer ratingId = 1;
        Rating rating = new Rating("Moody's", "S and P", "Fitch", 1);
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(ratingService.saveRating(rating)).thenReturn(rating);

        // When
        String viewName = ratingController.updateRating(ratingId, rating, result, model);

        // Then
        assertEquals("redirect:/rating/list", viewName);
        verify(ratingService).saveRating(rating);
        verify(model).addAttribute("ratings", ratingService.getRatingsList());
    }

    @Test
    public void testUpdateRatingWithInvalidRating() {
        // Given
        Integer ratingId = 1;
        Rating rating = new Rating();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // When
        String viewName = ratingController.updateRating(ratingId, rating, result, model);

        // Then
        assertEquals("redirect:/rating/list", viewName);
        verify(ratingService, never()).saveRating(any());
    }

    @Test
    public void testDeleteRating() {
        // Given
        Integer ratingId = 1;
        Rating ratingToDelete = new Rating("Moody's1", "S and P1", "Fitch1", 1);
        when(ratingService.getRatingById(ratingId)).thenReturn(Optional.of(ratingToDelete));

        // When
        String viewName = ratingController.deleteRating(ratingId, model);

        // Then
        assertEquals("redirect:/rating/list", viewName);
        verify(ratingService).deleteRating(ratingToDelete);
    }
}
