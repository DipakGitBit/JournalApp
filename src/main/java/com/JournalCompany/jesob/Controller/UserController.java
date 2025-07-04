package com.JournalCompany.jesob.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JournalCompany.jesob.Entity.JournalEntity;
import com.JournalCompany.jesob.Entity.User;
import com.JournalCompany.jesob.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUser() {
		return new ResponseEntity<>(uService.showUser(),HttpStatus.OK);
	}
	
//	@PostMapping
//	public ResponseEntity<User> postUser(@RequestBody User user) {
//		uService.saveRepository(user);
//		return new ResponseEntity<>(HttpStatus.CREATED);
//		
//	}
//	
	
//	@PutMapping("id/{myid}")
//	public ResponseEntity<User> updateUser(@PathVariable ObjectId myid, @RequestBody User userNew) {
//		
//		Optional<JournalEntity> uEntity=Optional.of(uService.updateRepository(myid, userNew));
//		if(uEntity.isPresent()) {
//			return new ResponseEntity<>(uEntity.get(),HttpStatus.OK);
//		}
//		
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		
//	}
//	
//	@DeleteMapping("id/{myid}")
//	public ResponseEntity<User> deleteUser(@PathVariable ObjectId myid) {
//		Optional<User> uEntity= uService.showSingleRepository(myid);
//		if(uEntity.isPresent()) {
//			uService.deleteRepository(myid);
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//	}
	
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User userNew) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName(); 
		
		User userInDBUser=uService.findByUserName(userName);
		
		if(userInDBUser!=null) {
			//System.out.print(userNew+" "+ userInDBUser);
			userInDBUser.setPassword(userNew.getPassword());
			uService.saveUserafterUpdate(userInDBUser);
			
			return new ResponseEntity<>(userInDBUser,HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@DeleteMapping
	public ResponseEntity<User> DeleteUser() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName(); 
		
		User userInDBUser=uService.findByUserName(userName);
		
		if(userInDBUser!=null) {
			//System.out.print(userNew+" "+ userInDBUser);
			uService.deleteByUserName(userName);
			
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}

}
