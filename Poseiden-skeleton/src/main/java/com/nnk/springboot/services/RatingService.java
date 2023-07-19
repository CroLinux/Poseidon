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
	

    public Iterable<Rating> getRatingsList() {
        return ratingRepository.findAll();
    }


    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }


    public Optional<Rating> getRatingById(Integer id) {
        return ratingRepository.findById(id);
    }


    public void deleteRating(Rating rating) {
        ratingRepository.delete(rating);
    }

}
