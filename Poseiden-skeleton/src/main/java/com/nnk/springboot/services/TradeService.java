package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {
	
	@Autowired
	private TradeRepository tradeRepository;
	
	/**
	 * Get all the trades list from the database
	 * 
	 * @return
	 */
    public Iterable<Trade> getTradesList() {
        return tradeRepository.findAll();
    }

    /**
     * Save/Modify a trade into the database
     * 
     * @param trade
     * @return
     */
    public Trade saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    /**
     * Get a specific trade from the database
     * 
     * @param id
     * @return
     */
    public Optional<Trade> getTradeById(Integer id) {
        return tradeRepository.findById(id);
    }

    /**
     * Delete a specific trade from the database
     * 
     * @param trade
     */
    public void deleteTrade(Trade trade) {
        tradeRepository.delete(trade);
    }

}
