package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {

	@Autowired
	private BidListRepository bidListRepository;

	/**
	 * Get all the Bid list from the database
	 * 
	 * @return
	 */
	public Iterable<BidList> getBidListsList() {
		return bidListRepository.findAll();
	}

	/**
	 * Save/Modify a bid list into the database
	 * 
	 * @param bidList
	 * @return
	 */
	public BidList saveBidList(BidList bidList) {
		return bidListRepository.save(bidList);
	}

	/**
	 * Get a specific bid list from the database
	 * 
	 * @param id
	 * @return
	 */
	public Optional<BidList> getBidListById(Integer id) {
		return bidListRepository.findById(id);
	}

	/**
	 * Delete a specific bid list from the database
	 * @param bidList
	 */
	public void deleteBidList(BidList bidList) {
		bidListRepository.delete(bidList);
	}

}
