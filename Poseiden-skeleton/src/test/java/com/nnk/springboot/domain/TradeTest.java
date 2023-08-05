package com.nnk.springboot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
public class TradeTest {

    private Trade trade;

    @BeforeEach
    public void setUp() {
        trade = new Trade("Account123", "Type1");
    }

    @Test
    public void testDefaultConstructor() {
        Trade defaultTrade = new Trade();
        assertNull(defaultTrade.getTradeId());
        assertNull(defaultTrade.getAccount());
        assertNull(defaultTrade.getType());
        assertNull(defaultTrade.getBuyQuantity());
        assertNull(defaultTrade.getSellQuantity());
        assertNull(defaultTrade.getBuyPrice());
        assertNull(defaultTrade.getSellPrice());
        assertNull(defaultTrade.getBenchmark());
        assertNull(defaultTrade.getTradeDate());
        assertNull(defaultTrade.getSecurity());
        assertNull(defaultTrade.getStatus());
        assertNull(defaultTrade.getTrader());
        assertNull(defaultTrade.getBook());
        assertNull(defaultTrade.getCreationName());
        assertNull(defaultTrade.getCreationDate());
        assertNull(defaultTrade.getRevisionName());
        assertNull(defaultTrade.getRevisionDate());
        assertNull(defaultTrade.getDealName());
        assertNull(defaultTrade.getDealType());
        assertNull(defaultTrade.getSourceListId());
        assertNull(defaultTrade.getSide());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("Account123", trade.getAccount());
        assertEquals("Type1", trade.getType());
    }

    @Test
    public void testSettersAndGetters() {
        trade.setTradeId(1);
        assertEquals(1, trade.getTradeId());

        trade.setAccount("Account");
        assertEquals("Account", trade.getAccount());

        trade.setType("Type");
        assertEquals("Type", trade.getType());

        trade.setBuyQuantity(100.0);
        assertEquals(100.0, trade.getBuyQuantity());

        trade.setSellQuantity(50.0);
        assertEquals(50.0, trade.getSellQuantity());

        trade.setBuyPrice(10.0);
        assertEquals(10.0, trade.getBuyPrice());

        trade.setSellPrice(20.0);
        assertEquals(20.0, trade.getSellPrice());

        trade.setBenchmark("Benchmark");
        assertEquals("Benchmark", trade.getBenchmark());

        LocalDate tradeDate = LocalDate.of(2023, 8, 1);
        trade.setTradeDate(tradeDate);
        assertEquals(tradeDate, trade.getTradeDate());

        trade.setSecurity("Security");
        assertEquals("Security", trade.getSecurity());

        trade.setStatus("Status");
        assertEquals("Status", trade.getStatus());

        trade.setTrader("Trader");
        assertEquals("Trader", trade.getTrader());

        trade.setBook("Book");
        assertEquals("Book", trade.getBook());

        trade.setCreationName("CreationName");
        assertEquals("CreationName", trade.getCreationName());

        LocalDate creationDate = LocalDate.of(2023, 8, 1);
        trade.setCreationDate(creationDate);
        assertEquals(creationDate, trade.getCreationDate());

        trade.setRevisionName("RevisionName");
        assertEquals("RevisionName", trade.getRevisionName());

        LocalDate revisionDate = LocalDate.of(2023, 8, 1);
        trade.setRevisionDate(revisionDate);
        assertEquals(revisionDate, trade.getRevisionDate());

        trade.setDealName("DealName");
        assertEquals("DealName", trade.getDealName());

        trade.setDealType("DealType");
        assertEquals("DealType", trade.getDealType());

        trade.setSourceListId("SourceListId");
        assertEquals("SourceListId", trade.getSourceListId());

        trade.setSide("Side");
        assertEquals("Side", trade.getSide());
    }

}

