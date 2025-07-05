package com.JournalCompany.jesob.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JournalCompany.jesob.Entity.User;
import com.JournalCompany.jesob.Repository.UserInterface;
import com.JournalCompany.jesob.Service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/healthcheck")
	public String healthcheck() {return "ok";}



	@PostMapping
	public void createuser(@RequestBody User user) {
		userService.saveNewUser(user);
	}

}
