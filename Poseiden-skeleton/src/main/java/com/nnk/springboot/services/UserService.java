package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Get all the users from the database.
	 * 
	 * @return
	 */
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    /**
     * Save/Update user in the database.
     * 
     * @param user
     * @return
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Get the info from a specified user from the database.
     * 
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {   	
        return userRepository.findByUsername(username);
    }

    /**
     * Delete a specified user from the database.
     * 
     * @param user
     */
    public void deleteUser(User user) {
    	userRepository.delete(user);
    }
    
    /**
     * Get the logged username
     * 
     * @param authentication
     * @return
     */
    public String getCurrentUser(Authentication authentication){
        String userName = authentication.getName();
        return userName;
    }

}
