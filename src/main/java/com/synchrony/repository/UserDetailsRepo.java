package com.synchrony.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.synchrony.model.UserData;

@Repository
public interface UserDetailsRepo extends CrudRepository<UserData, String>{

	public UserData findByUserName(String userName);
	
	public UserData save(UserData data);
}
