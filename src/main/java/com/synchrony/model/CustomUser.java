package com.synchrony.model;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

	private static final long serialVersionUID = 1L;

	public CustomUser(UserData user) {
		super(user.getUserName(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
	}
}
