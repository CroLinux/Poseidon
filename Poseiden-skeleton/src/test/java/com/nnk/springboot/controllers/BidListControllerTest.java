package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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

class BidListControllerTest {

    @Mock
    private BidListService bidListService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private BidListController bidListController;

    private List<BidList> bidLists;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        bidLists = new ArrayList<>();
        bidLists.add(new BidList("account1", "type1", 10.0));
        bidLists.add(new BidList("account2", "type2", 20.0));
    }

    @Test
    public void testHome() {
        // Given
        when(bidListService.getBidListsList()).thenReturn(bidLists);
        when(userService.getCurrentUser(any())).thenReturn("user");

        // When
        String viewName = bidListController.home(model, null);

        // Then
        assertEquals("bidList/list", viewName);
        verify(model).addAttribute("bidLists", bidLists);
        verify(model).addAttribute("remoteUser", "user");
    }

    @Test
    public void testAddBidForm() {
    	// Given
    	
        // When
        String viewName = bidListController.addBidForm(new BidList());

        // Then
        assertEquals("bidList/add", viewName);
    }

    @Test
    public void testValidateBidListWithValidBid() {
        // Given
        BidList bid = new BidList("account1", "type1", 10.0);
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        // When
        String viewName = bidListController.validate(bid, result, model);

        // Then
        assertEquals("redirect:/bidList/list", viewName);
        verify(bidListService).saveBidList(bid);
        verify(model).addAttribute("bidLists", bidListService.getBidListsList());
    }

    @Test
    public void testValidateBidListWithInvalidBid() {
        // Given
        BidList bid = new BidList();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // When
        String viewName = bidListController.validate(bid, result, model);

        // Then
        assertEquals("bidList/add", viewName);
        verify(bidListService, never()).saveBidList(any());
    }

    @Test
    public void testShowUpdateForm() {
        // Given
        Integer bidListId = 1;
        BidList bidToUpdate = new BidList("account1", "type1", 10.0);
        when(bidListService.getBidListById(bidListId)).thenReturn(Optional.of(bidToUpdate));

        // When
        String viewName = bidListController.showUpdateForm(bidListId, model);

        // Then
        assertEquals("bidList/update", viewName);
        verify(model).addAttribute("bidList", bidToUpdate);
    }

    @Test
    public void testUpdateBidListWithValidBid() {
        // Given
        Integer bidListId = 1;
        BidList bid = new BidList("account1", "type1", 10.0);
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(bidListService.saveBidList(bid)).thenReturn(bid);

        // When
        String viewName = bidListController.updateBid(bidListId, bid, result, model);

        // Then
        assertEquals("redirect:/bidList/list", viewName);
        verify(bidListService).saveBidList(bid);
        verify(model).addAttribute("bidLists", bidListService.getBidListsList());
    }

    @Test
    public void testUpdateBidListWithInvalidBid() {
        // Given
        Integer bidListId = 1;
        BidList bid = new BidList();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // When
        String viewName = bidListController.updateBid(bidListId, bid, result, model);

        // Then
        assertEquals("redirect:/bidList/list", viewName);
        verify(bidListService, never()).saveBidList(any());
    }

    @Test
    public void testDeleteBidList() {
        // Given
        Integer bidListId = 1;
        BidList bidToDelete = new BidList("account1", "type1", 10.0);
        when(bidListService.getBidListById(bidListId)).thenReturn(Optional.of(bidToDelete));

        // When
        String viewName = bidListController.deleteBid(bidListId, model);

        // Then
        assertEquals("redirect:/bidList/list", viewName);
        verify(bidListService).deleteBidList(bidToDelete);
    }
}
