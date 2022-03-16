package com.springsecurity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springsecurity.entity.User;

@Service
public interface UserService {
	List<User> getAllUsers();
	User saveUser(User user);
	User getUserById(Long id);
	User getUserByEmail(String email);
}
