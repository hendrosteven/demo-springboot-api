package com.brainmatics.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brainmatics.models.entity.User;
import com.brainmatics.models.repo.UserRepo;

@Service
@Transactional
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repo.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}
	
	
	public User insert(User user) {
		return repo.save(user);
	}
	
	
	public User login(String email, String password) {
		User user = repo.findByEmail(email);
		if(user == null) {
			return null;
		}
		if(!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}

}
