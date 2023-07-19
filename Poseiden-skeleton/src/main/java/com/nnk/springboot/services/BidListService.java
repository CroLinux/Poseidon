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
	

    public Iterable<BidList> getBidListsList() {
        return bidListRepository.findAll();
    }


    public BidList saveBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }


    public Optional<BidList> getBidListById(Integer id) {
        return bidListRepository.findById(id);
    }


    public void deleteBidList(BidList bidList) {
        bidListRepository.delete(bidList);
    }

}
