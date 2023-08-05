package com.nnk.springboot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class BidListTest {

	private BidList bidList;

	@BeforeEach
	public void setUp() {
		bidList = new BidList("Account123", "Type1", 100.0);
	}

	@Test
	public void testDefaultConstructor() {
		BidList defaultBidList = new BidList();
		assertNull(defaultBidList.getBidListId());
		assertNull(defaultBidList.getAccount());
		assertNull(defaultBidList.getType());
		assertNull(defaultBidList.getBidQuantity());
		assertNull(defaultBidList.getAskQuantity());
		assertNull(defaultBidList.getBid());
		assertNull(defaultBidList.getAsk());
		assertNull(defaultBidList.getBenchmark());
		assertNull(defaultBidList.getBidListDate());
		assertNull(defaultBidList.getCommentary());
		assertNull(defaultBidList.getSecurity());
		assertNull(defaultBidList.getStatus());
		assertNull(defaultBidList.getTrader());
		assertNull(defaultBidList.getBook());
		assertNull(defaultBidList.getCreationName());
		assertNull(defaultBidList.getCreationDate());
		assertNull(defaultBidList.getRevisionName());
		assertNull(defaultBidList.getRevisionDate());
		assertNull(defaultBidList.getDealName());
		assertNull(defaultBidList.getDealType());
		assertNull(defaultBidList.getSourceListId());
		assertNull(defaultBidList.getSide());
	}

	@Test
	public void testParameterizedConstructor() {
		assertEquals("Account123", bidList.getAccount());
		assertEquals("Type1", bidList.getType());
		assertEquals(100.0, bidList.getBidQuantity());
	}

	@Test
	public void testSettersAndGetters() {
		bidList.setBidListId(1);
		assertEquals(1, bidList.getBidListId());

		bidList.setAccount("Account");
		assertEquals("Account", bidList.getAccount());

		bidList.setType("Type");
		assertEquals("Type", bidList.getType());

		bidList.setBidQuantity(200.0);
		assertEquals(200.0, bidList.getBidQuantity());

		bidList.setAskQuantity(50.0);
		assertEquals(50.0, bidList.getAskQuantity());

		bidList.setBid(10.0);
		assertEquals(10.0, bidList.getBid());

		bidList.setAsk(20.0);
		assertEquals(20.0, bidList.getAsk());

		bidList.setBenchmark("Benchmark");
		assertEquals("Benchmark", bidList.getBenchmark());

		LocalDate bidListDate = LocalDate.of(2023, 8, 1);
		bidList.setBidListDate(bidListDate);
		assertEquals(bidListDate, bidList.getBidListDate());

		bidList.setCommentary("Commentary");
		assertEquals("Commentary", bidList.getCommentary());

		bidList.setSecurity("Security");
		assertEquals("Security", bidList.getSecurity());

		bidList.setStatus("Status");
		assertEquals("Status", bidList.getStatus());

		bidList.setTrader("Trader");
		assertEquals("Trader", bidList.getTrader());

		bidList.setBook("Updated Book");
		assertEquals("Updated Book", bidList.getBook());

		bidList.setCreationName("CreationName");
		assertEquals("CreationName", bidList.getCreationName());

		LocalDate creationDate = LocalDate.of(2023, 8, 1);
		bidList.setCreationDate(creationDate);
		assertEquals(creationDate, bidList.getCreationDate());

		bidList.setRevisionName("RevisionName");
		assertEquals("RevisionName", bidList.getRevisionName());

		LocalDate revisionDate = LocalDate.of(2023, 8, 1);
		bidList.setRevisionDate(revisionDate);
		assertEquals(revisionDate, bidList.getRevisionDate());

		bidList.setDealName("DealName");
		assertEquals("DealName", bidList.getDealName());

		bidList.setDealType("DealType");
		assertEquals("DealType", bidList.getDealType());

		bidList.setSourceListId("SourceListId");
		assertEquals("SourceListId", bidList.getSourceListId());

		bidList.setSide("Side");
		assertEquals("Side", bidList.getSide());
	}

}
