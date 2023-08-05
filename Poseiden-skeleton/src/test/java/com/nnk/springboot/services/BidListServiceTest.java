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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@SpringBootTest
public class BidListServiceTest {

	@Mock
	private BidListRepository bidListRepository;

	@InjectMocks
	private BidListService bidListService;

	private BidList testBidList;

	@BeforeEach
	public void setup() {
		testBidList = new BidList("TestAccount", "TestType", 100.0);
		testBidList.setBidListId(1);
		testBidList.setBidListDate(LocalDate.now());
	}

	@Test
	public void testGetBidListsList() {
		// Given
		when(bidListRepository.findAll()).thenReturn(java.util.Collections.singletonList(testBidList));

		// When
		Iterable<BidList> result = bidListService.getBidListsList();

		// Then
		assertEquals(1, ((java.util.Collection<?>) result).size());
	}

	@Test
	public void testSaveBidList() {
		// Given
		when(bidListRepository.save(testBidList)).thenReturn(testBidList);

		// When
		BidList savedBidList = bidListService.saveBidList(testBidList);

		// Then
		assertEquals(testBidList, savedBidList);
	}

	@Test
	public void testGetBidListById() {
		// Given
		when(bidListRepository.findById(1)).thenReturn(Optional.of(testBidList));

		// When
		Optional<BidList> result = bidListService.getBidListById(1);

		// Then
		assertEquals(testBidList, result.orElse(null));
	}

	@Test
	public void testDeleteBidList() {
		// When
		bidListService.deleteBidList(testBidList);

		// Then
		verify(bidListRepository, times(1)).delete(testBidList);
	}
}
