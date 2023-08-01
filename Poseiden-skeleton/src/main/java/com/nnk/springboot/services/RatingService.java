package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;
	
	/**
	 * Get all the ratings from the database
	 * 
	 * @return
	 */
    public Iterable<Rating> getRatingsList() {
        return ratingRepository.findAll();
    }

    /**
     * Save/Modify a rating in the database
     * 
     * @param rating
     * @return
     */
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Get a specific rating from the database
     * 
     * @param id
     * @return
     */
    public Optional<Rating> getRatingById(Integer id) {
        return ratingRepository.findById(id);
    }

    /**
     * Delete a specific rating from the database
     * 
     * @param rating
     */
    public void deleteRating(Rating rating) {
        ratingRepository.delete(rating);
    }

}
