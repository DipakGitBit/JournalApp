package com.JournalCompany.jesob.Service;

import java.lang.module.ModuleDescriptor.Builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.JournalCompany.jesob.Entity.User;
import com.JournalCompany.jesob.Repository.UserInterface;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserInterface userInterface;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user= userInterface.findByUserName(username);
		if(user!=null) {
			return org.springframework.security.core.userdetails.User.builder()
					.username(user.getUserName())
					.password(user.getPassword())
					.roles(user.getRoles().toArray(new String[0])).build();
		}
		return (UserDetails) new UsernameNotFoundException("User not found! ");
	}

}
