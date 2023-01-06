package com.prudential.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.prudential.domain.entity.User;
import com.prudential.exception.EntityNotFoundException;

/**
 * 
 * @author Hu Cai
 *
 */
public class UserControllerTest extends CarRentalBase {
	
	@Test
    public void canRegisterUser() throws EntityNotFoundException {
		String NAME = "Tiger";
		String EMAIL = "tiger@gmail.com";
		
		long userId = registerUser(NAME, EMAIL);
		// get user from db
		User user = userDomainService.getById(userId);
		
		assertEquals(NAME, user.getName());
		assertEquals(EMAIL, user.getEmail());
	}
	
	@Test
    public void registerUserWithEmailAlreadyRegistered() {
		String NAME = "Tiger";
		String EMAIL = "tiger@gmail.com";
		
		registerUser(NAME, EMAIL);
		try {
			registerUser("New name", EMAIL);
			fail();
		}catch (Exception e) {
			assertTrue(e.getMessage().contains("Entity Already Exists"));
		}
	}
	
	@Test
    public void registerUserWithInvalidEmail() {
		String NAME = "Tiger";
		String EMAIL = "invalid";
		
		try {
			registerUser(NAME, EMAIL);
			fail();
		}catch (Exception e) {
			assertTrue(e.getMessage().contains("Validation failed"));
		}
	}

}
