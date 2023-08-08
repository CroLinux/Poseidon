package com.nnk.springboot.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

@SpringBootTest
public class HomeControllerTest {

	private MockMvc mockMvc;

	@Mock
	private Authentication authentication;

	@Mock
	private Model mockModel;

	@InjectMocks
	private HomeController homeController;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(homeController).build();

		// Mocking authentication with an authenticated user
		authentication = new UsernamePasswordAuthenticationToken("testuser", "testpassword");
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);

	}

	@Test
	void testHomeAuthenticated() throws Exception {
		// Given
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// When
		mockMvc.perform(get("/"))

				// Then
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/bidList/list"));
	}

	@Test
	void testHomeNotAuthenticated() throws Exception {
		// Given
		// Setting authentication to null to simulate a non-authenticated user
		SecurityContextHolder.getContext().setAuthentication(null);

		// When
		mockMvc.perform(get("/"))

				// Then
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/login"));
	}

	@Test
	public void testHome() {
		// Given

		// When
		String result = homeController.home(mockModel);

		// Then
		assertEquals("home", result);

	}

	@Test
	void testAdminHome() throws Exception {
		// Given
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// When
		mockMvc.perform(get("/admin/home"))

				// Then
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/user/list"));
	}
	
	@Test
	void testLoginAuthenticated() throws Exception {
	    // Given
	    SecurityContextHolder.getContext().setAuthentication(authentication);

	    // When
	    mockMvc.perform(get("/login"))

	            // Then
	            .andExpect(status().is3xxRedirection())
	            .andExpect(redirectedUrl("/bidList/list"));
	}
	
}
