package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;


@Service
public class CustomUserDatailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * Needed for the Security Configuration for the Login
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					List.of(new SimpleGrantedAuthority(user.getRole())));
		} else {
			throw new UsernameNotFoundException("Invalid email or password");
		}
	}

}