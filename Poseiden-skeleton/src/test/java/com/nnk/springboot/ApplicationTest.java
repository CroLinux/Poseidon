package com.nnk.springboot;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {
	
	@Test
	void contextLoads() {
	}

	@Test
	void main() {
		// Test case logic goes here
		// For example, you can test if the application starts successfully
		assertDoesNotThrow(() -> Application.main(new String[] {}));
	}

}
