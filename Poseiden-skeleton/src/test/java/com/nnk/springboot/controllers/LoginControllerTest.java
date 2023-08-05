package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LoginControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private LoginController loginController;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);

		// Setup the view resolver to handle view names properly
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");

		mockMvc = MockMvcBuilders.standaloneSetup(loginController).setViewResolvers(viewResolver).build();
	}

	@Test
	void testLogin() throws Exception {
		// Given

		// When
		mockMvc.perform(get("/app/login"))

				// Then
				.andExpect(status().isOk()).andExpect(view().name("login"));
	}

	@Test
	void testGetAllUserArticles() throws Exception {
		// Given
		// Mocking the data returned by the userRepository.findAll() method
		List<User> mockUsers = new ArrayList<>();
		mockUsers.add(new User("1", "John", "Doe", "john.doe@example.com"));
		mockUsers.add(new User("2", "Jane", "Smith", "jane.smith@example.com"));

		// Setting up the userRepository mock to return the mockUsers list
		when(userRepository.findAll()).thenReturn(mockUsers);

		// When
		mockMvc.perform(get("/app/secure/article-details"))

				// Then
				.andExpect(status().isOk()).andExpect(model().attributeExists("users"))
				.andExpect(view().name("user/list"));
	}

	@Test
	void testError() throws Exception {
		// Given

		// When
		mockMvc.perform(get("/app/error"))

				// Then
				.andExpect(status().isOk()).andExpect(model().attributeExists("errorMsg"))
				.andExpect(view().name("403"));
	}
}
