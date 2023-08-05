package com.nnk.springboot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@SpringBootTest
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	private User testUser;

	@BeforeEach
	public void setup() {
		testUser = new User();
		testUser.setId(1);
		testUser.setUsername("testuser");
		testUser.setPassword("Password123!");
		testUser.setFullname("Test User");
		testUser.setRole("USER");
	}

	@Test
	public void testGetUsersList() {
		// Given
		List<User> userList = new ArrayList<>();
		userList.add(testUser);
		when(userRepository.findAll()).thenReturn(userList);

		// When
		List<User> result = userService.getUsersList();

		// Then
		assertEquals(1, result.size());
		assertEquals(testUser, result.get(0));
	}

	@Test
	public void testSaveUser() {
		// Given
		when(userRepository.save(testUser)).thenReturn(testUser);

		// When
		User savedUser = userService.saveUser(testUser);

		// Then
		assertEquals(testUser, savedUser);
	}

	@Test
	public void testGetUserByUsername() {
		// Given
		when(userRepository.findByUsername(testUser.getUsername())).thenReturn(testUser);

		// When
		User result = userService.getUserByUsername(testUser.getUsername());

		// Then
		assertEquals(testUser, result);
	}

	@Test
	public void testDeleteUser() {
		// Given
		
		// When
		userService.deleteUser(testUser);

		// Then
		verify(userRepository, times(1)).delete(testUser);
	}

	@Test
	public void testGetCurrentUser() {
		// Given
		String expectedUsername = "testuser";
		Authentication authentication = new UsernamePasswordAuthenticationToken(expectedUsername, "password");

		// When
		String result = userService.getCurrentUser(authentication);

		// Then
		assertEquals(expectedUsername, result);
	}
}
