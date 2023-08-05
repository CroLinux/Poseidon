package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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

class CurveControllerTest {

    @Mock
    private CurvePointService curvePointService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private CurveController curveController;

    private List<CurvePoint> curvePoints;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        curvePoints = new ArrayList<>();
        curvePoints.add(new CurvePoint(1, 10.0, 20.0));
        curvePoints.add(new CurvePoint(2, 20.0, 30.0));
    }

    @Test
    public void testHome() {
        // Given
        when(curvePointService.getCurvePointsList()).thenReturn(curvePoints);
        when(userService.getCurrentUser(any())).thenReturn("user");

        // When
        String viewName = curveController.home(model, null);

        // Then
        assertEquals("curvePoint/list", viewName);
        verify(model).addAttribute("curvePoints", curvePoints);
        verify(model).addAttribute("remoteUser", "user");
    }

    @Test
    public void testAddBidForm() {
        // When
        String viewName = curveController.addBidForm(new CurvePoint());

        // Then
        assertEquals("curvePoint/add", viewName);
    }

    @Test
    public void testValidateCurvePointWithValidCurvePoint() {
        // Given
        CurvePoint curvePoint = new CurvePoint(1, 10.0, 20.0);
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        // When
        String viewName = curveController.validate(curvePoint, result, model);

        // Then
        assertEquals("redirect:/curvePoint/list", viewName);
        verify(curvePointService).saveCurvePoint(curvePoint);
        verify(model).addAttribute("curvePoints", curvePointService.getCurvePointsList());
    }

    @Test
    public void testValidateCurvePointWithInvalidCurvePoint() {
        // Given
        CurvePoint curvePoint = new CurvePoint();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // When
        String viewName = curveController.validate(curvePoint, result, model);

        // Then
        assertEquals("curvePoint/add", viewName);
        verify(curvePointService, never()).saveCurvePoint(any());
    }

    @Test
    public void testShowUpdateForm() {
        // Given
        Integer curvePointId = 1;
        CurvePoint curvePointToUpdate = new CurvePoint(1, 10.0, 20.0);
        when(curvePointService.getCurvePointById(curvePointId)).thenReturn(Optional.of(curvePointToUpdate));

        // When
        String viewName = curveController.showUpdateForm(curvePointId, model);

        // Then
        assertEquals("curvePoint/update", viewName);
        verify(model).addAttribute("curvePoint", curvePointToUpdate);
    }

    @Test
    public void testUpdateCurvePointWithValidCurvePoint() {
        // Given
        Integer curvePointId = 1;
        CurvePoint curvePoint = new CurvePoint(1, 10.0, 20.0);
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(curvePointService.saveCurvePoint(curvePoint)).thenReturn(curvePoint);

        // When
        String viewName = curveController.updateBid(curvePointId, curvePoint, result, model);

        // Then
        assertEquals("redirect:/curvePoint/list", viewName);
        verify(curvePointService).saveCurvePoint(curvePoint);
        verify(model).addAttribute("curvePoints", curvePointService.getCurvePointsList());
    }

    @Test
    public void testUpdateCurvePointWithInvalidCurvePoint() {
        // Given
        Integer curvePointId = 1;
        CurvePoint curvePoint = new CurvePoint();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // When
        String viewName = curveController.updateBid(curvePointId, curvePoint, result, model);

        // Then
        assertEquals("curvePoint/update", viewName);
        verify(curvePointService, never()).saveCurvePoint(any());
    }

    @Test
    public void testDeleteCurvePoint() {
        // Given
        Integer curvePointId = 1;
        CurvePoint curvePointToDelete = new CurvePoint(1, 10.0, 20.0);
        when(curvePointService.getCurvePointById(curvePointId)).thenReturn(Optional.of(curvePointToDelete));

        // When
        String viewName = curveController.deleteBid(curvePointId, model);

        // Then
        assertEquals("redirect:/curvePoint/list", viewName);
        verify(curvePointService).deleteCurvePoint(curvePointToDelete);
    }
}
