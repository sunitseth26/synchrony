package com.synchrony.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synchrony.constant.ApplicationConstants;
import com.synchrony.exception.UserRegistrationFailedException;
import com.synchrony.model.UserData;
import com.synchrony.repository.UserDetailsRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginService {

	@Autowired
	private UserDetailsRepo userDetailsRepo;
	
	public String registerUser(UserData userData) {
		log.info("Regesting user : " + userData.getUserName());
		UserData userDataDb = userDetailsRepo.findByUserName(userData.getUserName());
		if (ObjectUtils.isNotEmpty(userDataDb)) {
			throw new UserRegistrationFailedException(ApplicationConstants.USER_EXISTS);
		}
		try {
			userDetailsRepo.save(userData);
		} catch (Exception e) {
			throw new UserRegistrationFailedException(ApplicationConstants.REGISTER_FAILURE);
		}
		log.info(ApplicationConstants.REGISTER_SUCCESS);
		return ApplicationConstants.REGISTER_SUCCESS;
	}
}
