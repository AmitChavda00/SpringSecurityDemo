package com.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.entity.User;
import com.springsecurity.service.UserService;

@RestController("/")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String home() {
		return "<h3>Welcome home! Permitted to all users...</h3>"+
	
				"<a href='/admin/GetAllUsers'>Admin</a>";
		
		
	}

	@GetMapping("/admin/GetAllUsers")
	public List<User> getAllUSers() {
		return userService.getAllUsers();
	}

	@GetMapping("/admin/GetUser/{id}")
	public User getUser(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@GetMapping("/user/GetUser/{email}")
	public User getUser(@PathVariable String email) {
		return userService.getUserByEmail(email);
	}
	
	@GetMapping("/customer/GetCustomer")
	public String getUser() {
		return "Customer Accessed!";
	}

//	@GetMapping("/logout")
//	public String logout() {
//		return "redirect://";
//	}
	
	@PostMapping("/user/SaveUser")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

}
