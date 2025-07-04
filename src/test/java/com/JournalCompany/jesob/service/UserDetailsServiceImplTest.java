package com.JournalCompany.jesob.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.assertj.core.internal.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import com.JournalCompany.jesob.Entity.User;
import com.JournalCompany.jesob.Repository.UserInterface;
import com.JournalCompany.jesob.Service.UserDetailsServiceImpl;
import com.mongodb.assertions.Assertions;

//Mocking

class UserDetailsServiceImplTest {
	
	@InjectMocks
	private UserDetailsServiceImpl uServiceImpl;
	
	@Mock
	private UserInterface userInterface;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void loadUserByUserNameTest() {
		
		when(userInterface.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("dipak").Password("sssss").roles(new ArrayList<>()).build());
		UserDetails userDetails= uServiceImpl.loadUserByUsername("dipak");
		Assertions.assertNotNull(userDetails);
		
		
		
	}

}
