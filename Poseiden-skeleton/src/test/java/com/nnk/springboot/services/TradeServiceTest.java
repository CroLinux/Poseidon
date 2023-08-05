package com.nnk.springboot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@SpringBootTest
public class TradeServiceTest {

	@Mock
	private TradeRepository tradeRepository;

	@InjectMocks
	private TradeService tradeService;

	private Trade testTrade;

	@BeforeEach
	public void setup() {
		testTrade = new Trade("AccountTest", "TypeTest");
		testTrade.setTradeId(1);
	}

	@Test
	public void testGetTradesList() {
		// Given
		when(tradeRepository.findAll()).thenReturn(java.util.Collections.singletonList(testTrade));

		// When
		Iterable<Trade> result = tradeService.getTradesList();

		// Then
		assertEquals(1, ((java.util.Collection<?>) result).size());
	}

	@Test
	public void testSaveTrade() {
		// Given
		when(tradeRepository.save(testTrade)).thenReturn(testTrade);

		// When
		Trade savedTrade = tradeService.saveTrade(testTrade);

		// Then
		assertEquals(testTrade, savedTrade);
	}

	@Test
	public void testGetTradeById() {
		// Given
		when(tradeRepository.findById(1)).thenReturn(Optional.of(testTrade));

		// When
		Optional<Trade> result = tradeService.getTradeById(1);

		// Then
		assertEquals(testTrade, result.orElse(null));
	}

	@Test
	public void testDeleteTrade() {
		// When
		tradeService.deleteTrade(testTrade);

		// Then
		verify(tradeRepository, times(1)).delete(testTrade);
	}
}
