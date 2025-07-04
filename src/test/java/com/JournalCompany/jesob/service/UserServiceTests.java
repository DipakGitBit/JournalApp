package com.JournalCompany.jesob.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.JournalCompany.jesob.Entity.User;
import com.JournalCompany.jesob.Repository.UserInterface;

@SpringBootTest
public class UserServiceTests {
	
	@Autowired
	private UserInterface userInterface;
	
	@Disabled
	@Test
	public void testMethodFindByUsername() {
		assertNotNull(userInterface.findByUserName("yyy"));
	}
	
	@Test
	public void testMethodCheckempty() {
		User user=userInterface.findByUserName("dipak");
		assertTrue(!user.getJournalEntities().isEmpty());
		
	}
	
	
	@ParameterizedTest
	@CsvSource({
		"ram","dipak","xxx"
	})
	public void testMethodParameterized(String name) {
		
		assertNotNull(userInterface.findByUserName(name), "faled for "+name);
		
	}

}
