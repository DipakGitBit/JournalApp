package com.JournalCompany.jesob.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.JournalCompany.jesob.Entity.User;
import com.JournalCompany.jesob.Repository.UserInterface;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserInterface userInterface;
	
	//private static final Logger LOGGER=LoggerFactory.getLogger(UserService.class);
 
	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	public List<User> showUser() {
		log.info("ssjdjskj");
		log.warn("ssjdjskj");
		log.error("ssjdjskj");
		log.debug("ssjdjskj");
		log.trace("ssjdjskj");
		
		log.error("Error occured for {} :");

		return userInterface.findAll();

	}

	public void saveUser(User user) {

		userInterface.save(user);
		
		

	}

	public void saveNewUser(User user) {

		user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER"));
		userInterface.save(user);
		

	}
	
	public void saveAdmin(User user) {

		user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER","ADMIN"));
		userInterface.save(user);

	}
	
	public void saveUserafterUpdate(User user) {

		user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
		userInterface.save(user);

	}

//	public void deleteRepository(@PathVariable ObjectId objectId) {
//
//		userInterface.deleteById(objectId);
//
//	}

//	public JournalEntity updateRepository(@PathVariable ObjectId objectId, @RequestBody JournalEntity jEntityNew) {
//
//		JournalEntity jEntityOLD = jRepository.findById(objectId).orElse(null);
//
//		if (jEntityOLD != null) {
//			if (jEntityNew.getContent() != null && !jEntityNew.getContent().equals("")) {
//				jEntityOLD.setContent(jEntityNew.getContent());
//			}
//
//			if (jEntityNew.getDescription() != null && !jEntityNew.getDescription().equals("")) {
//				jEntityOLD.setDescription(jEntityNew.getDescription());
//			}
//
//			jEntityOLD.setCreatedDate(LocalDateTime.now());
//			jRepository.save(jEntityOLD);
//		}
//		
//		return jEntityOLD;
//
//	}

	public User findByUserName(String username) {
		return userInterface.findByUserName(username);
	}

	public void deleteByUserName(String username) {
		userInterface.deleteByUserName(username);
	}

}
