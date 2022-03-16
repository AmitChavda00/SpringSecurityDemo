package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.entity.User;
import com.springsecurity.exception.ResourceNotFound;
import com.springsecurity.respository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFound("User", "email", username));

		MyUserDetails userDetails = new MyUserDetails(user);
		return userDetails;
	}

}
