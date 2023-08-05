package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private UserController userController;

    private List<User> users;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        users = new ArrayList<>();
        users.add(new User("user1", "Password1", "Full Name 1", "ROLE_USER"));
        users.add(new User("user2", "Password2", "Full Name 2", "ROLE_ADMIN"));
    }

    @Test
    public void testHome() {
        // Given
        when(userRepository.findAll()).thenReturn(users);

        // When
        String viewName = userController.home(model);

        // Then
        assertEquals("user/list", viewName);
        verify(model).addAttribute("users", users);
    }

    @Test
    public void testAddUser() {
    	// Given
    	
        // When
        String viewName = userController.addUser(new User());

        // Then
        assertEquals("user/add", viewName);
    }

    @Test
    public void testValidateWithInvalidUser() {
        // Given
        User user = new User();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // When
        String viewName = userController.validate(user, result, model);

        // Then
        assertEquals("user/add", viewName);
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testShowUpdateForm() {
        // Given
        Integer userId = 1;
        User userToUpdate = new User("user1", "Password1", "Full Name 1", "ROLE_USER");
        userToUpdate.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userToUpdate));

        // When
        String viewName = userController.showUpdateForm(userId, model);

        // Then
        assertEquals("user/update", viewName);
        verify(model).addAttribute("user", userToUpdate);
    }

    @Test
    public void testUpdateUserWithInvalidUser() {
        // Given
        Integer userId = 1;
        User user = new User();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        // When
        String viewName = userController.updateUser(userId, user, result, model);

        // Then
        assertEquals("user/update", viewName);
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testDeleteUser() {
        // Given
        Integer userId = 1;
        User userToDelete = new User("user1", "Password1", "Full Name 1", "ROLE_USER");
        userToDelete.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userToDelete));

        // When
        String viewName = userController.deleteUser(userId, model);

        // Then
        assertEquals("redirect:/user/list", viewName);
        verify(userRepository).delete(userToDelete);
    }
}
