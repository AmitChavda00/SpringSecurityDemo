package com.springsecurity.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurity.entity.User;
import com.springsecurity.exception.ResourceNotFound;
import com.springsecurity.respository.UserRepository;
import com.springsecurity.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		//user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User", "id", id));
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFound("User", "email", email));
	}

}
