package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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

class TradeControllerTest {

    @Mock
    private TradeService tradeService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private TradeController tradeController;

    private List<Trade> trades;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        trades = new ArrayList<>();
        trades.add(new Trade("Account1", "Type1"));
        trades.add(new Trade("Account2", "Type2"));
    }

    @Test
    public void testHome() {
        // Given
        when(tradeService.getTradesList()).thenReturn(trades);
        when(userService.getCurrentUser(any())).thenReturn("user");

        // When
        String viewName = tradeController.home(model, null);

        // Then
        assertEquals("trade/list", viewName);
        verify(model).addAttribute("trades", trades);
        verify(model).addAttribute("remoteUser", "user");
    }

    @Test
    public void testAddTrade() {
    	// Given
    	
        // When
        String viewName = tradeController.addTrade(new Trade());

        // Then
        assertEquals("trade/add", viewName);
    }

    @Test
    public void testValidateWithValidTrade() {
        // Given
        Trade trade = new Trade("Account", "Type");
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        // When
        String viewName = tradeController.validate(trade, result, model);

        // Then
        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService).saveTrade(trade);
        verify(model).addAttribute("trades", tradeService.getTradesList());
    }

    @Test
    public void testValidateWithInvalidTrade() {
        // Given
        Trade trade = new Trade();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // When
        String viewName = tradeController.validate(trade, result, model);

        // Then
        assertEquals("trade/add", viewName);
        verify(tradeService, never()).saveTrade(any());
    }

    @Test
    public void testShowUpdateForm() {
        // Given
        Integer tradeId = 1;
        Trade tradeToUpdate = new Trade("Account1", "Type1");
        tradeToUpdate.setTradeId(tradeId);
        when(tradeService.getTradeById(tradeId)).thenReturn(Optional.of(tradeToUpdate));

        // When
        String viewName = tradeController.showUpdateForm(tradeId, model);

        // Then
        assertEquals("trade/update", viewName);
        verify(model).addAttribute("trade", tradeToUpdate);
    }

    @Test
    public void testUpdateTradeWithValidTrade() {
        // Given
        Integer tradeId = 1;
        Trade trade = new Trade("Account", "Type");
        trade.setTradeId(tradeId);
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(tradeService.saveTrade(trade)).thenReturn(trade);

        // When
        String viewName = tradeController.updateTrade(tradeId, trade, result, model);

        // Then
        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService).saveTrade(trade);
        verify(model).addAttribute("trades", tradeService.getTradesList());
    }

    @Test
    public void testUpdateTradeWithInvalidTrade() {
        // Given
        Integer tradeId = 1;
        Trade trade = new Trade();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // When
        String viewName = tradeController.updateTrade(tradeId, trade, result, model);

        // Then
        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService, never()).saveTrade(any());
    }

    @Test
    public void testDeleteTrade() {
        // Given
        Integer tradeId = 1;
        Trade tradeToDelete = new Trade("Account1", "Type1");
        tradeToDelete.setTradeId(tradeId);
        when(tradeService.getTradeById(tradeId)).thenReturn(Optional.of(tradeToDelete));

        // When
        String viewName = tradeController.deleteTrade(tradeId, model);

        // Then
        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService).deleteTrade(tradeToDelete);
    }
}
