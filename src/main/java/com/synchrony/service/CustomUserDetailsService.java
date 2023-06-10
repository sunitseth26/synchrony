package com.synchrony.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.synchrony.model.CustomUser;
import com.synchrony.model.UserData;
import com.synchrony.repository.UserDetailsRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDetailsRepo userDetailsRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserData userData = null;
		CustomUser customUser = null;
		try {
			userData = userDetailsRepo.findByUserName(userName);
			customUser = new CustomUser(userData);
		} catch(Exception ex) {
			log.error("Username " + userName + " not found", ex);
			throw new UsernameNotFoundException("User " + userName + " not found in the database.");
		}
		return customUser;
	}

}
