package com.JournalCompany.jesob.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JournalCompany.jesob.Entity.User;
import com.JournalCompany.jesob.Service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		List<User> allList = userService.showUser();
		if(allList!=null && !allList.isEmpty()) {
			return new ResponseEntity<>(allList,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@PostMapping("createAdmin")
	public ResponseEntity<?> createAdmin(@RequestBody User user) {
		userService.saveAdmin(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
