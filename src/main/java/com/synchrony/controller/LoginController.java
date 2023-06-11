package com.synchrony.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.synchrony.model.CustomUser;
import com.synchrony.model.UserData;
import com.synchrony.service.CustomUserDetailsService;
import com.synchrony.service.LoginService;
import com.synchrony.util.JwtTokenUtil;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	@ResponseBody
	public String registerUser(@RequestBody UserData userData) {
		if (ObjectUtils.isNotEmpty(userData)) {
			userData.setPassword(passwordEncoder.encode(userData.getPassword()));
		}
		return loginService.registerUser(userData);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody UserData userData) throws Exception {
		authenticate(userData.getUserName(), userData.getPassword());

		final CustomUser userDetails = (CustomUser) userDetailsService
				.loadUserByUsername(userData.getUserName());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(token);
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
