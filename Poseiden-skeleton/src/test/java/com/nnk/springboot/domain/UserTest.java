package com.nnk.springboot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Objects;
import java.util.Set;

@SpringBootTest
public class UserTest {

	private User user1;
	private User user2;
	private User user3;
	private Validator validator;

	@BeforeEach
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		user1 = new User();
		user1.setId(1);
		user1.setUsername("username123");
		user1.setPassword("Password@123");
		user1.setFullname("Test User");
		user1.setRole("ROLE_USER");

		user2 = new User();
		user2.setId(1);
		user2.setUsername("username123");
		user2.setPassword("Password@123");
		user2.setFullname("Test User");
		user2.setRole("ROLE_USER");

		user3 = new User();
		user3.setId(3);
		user3.setUsername("username456");
		user3.setPassword("Password@456");
		user3.setFullname("User Test");
		user3.setRole("ROLE_ADMIN");
	}

	@Test
	public void testHashCode() {
		assertEquals(
				Objects.hash(user1.getId(), user1.getUsername(), user1.getPassword(), user1.getFullname(),
						user1.getRole()),
				Objects.hash(user2.getId(), user2.getUsername(), user2.getPassword(), user2.getFullname(),
						user2.getRole()));

		assertNotEquals(
				Objects.hash(user1.getId(), user1.getUsername(), user1.getPassword(), user1.getFullname(),
						user1.getRole()),
				Objects.hash(user3.getId(), user3.getUsername(), user3.getPassword(), user3.getFullname(),
						user3.getRole()));
	}

	@Test
	public void testEquals() {

		assertEquals(user1.getId(), user2.getId());
		assertEquals(user1.getUsername(), user2.getUsername());
		assertEquals(user1.getPassword(), user2.getPassword());
		assertEquals(user1.getFullname(), user2.getFullname());
		assertEquals(user1.getRole(), user2.getRole());

		assertNotEquals(user1.getId(), user3.getId());
		assertNotEquals(user1.getUsername(), user3.getUsername());
		assertNotEquals(user1.getPassword(), user3.getPassword());
		assertNotEquals(user1.getFullname(), user3.getFullname());
		assertNotEquals(user1.getRole(), user3.getRole());
	}

	@Test
	public void testToString() {
		String expected = "User [" + "id=1" + ", username=username123" + ", password=Password@123"
				+ ", fullname=Test User" + ", role=ROLE_USER" + "]";

		assertEquals(expected, user1.toString());
		assertNotEquals(expected, user3.toString());
	}

	@Test
	public void testInvalidUserInvalidPassword() {
		User user = new User();
		user.setId(1);
		user.setUsername("username");
		user.setPassword("invalidpassword");
		user.setFullname("Wrong Test User");
		user.setRole("ROLE_USER");

		Set<ConstraintViolation<User>> violations = validator.validate(user);
		assertEquals(1, violations.size());
	}
}
