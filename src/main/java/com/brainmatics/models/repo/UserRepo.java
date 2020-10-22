package com.brainmatics.models.repo;

import org.springframework.data.repository.CrudRepository;

import com.brainmatics.models.entity.User;

public interface UserRepo extends CrudRepository<User, Integer>{

	public User findByEmail(String email);
	
}
