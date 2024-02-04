package com.customer.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.config.UserInfo;
import com.customer.service.JwtService;

/**
 * 
 * this class is used to geneate authentication token
 * 
 */
@RestController
public class AuthenticateController {

	JwtService jwtService;

	private AuthenticationManager authenticationManager;

	@Autowired
	public AuthenticateController(JwtService jwtService, AuthenticationManager authenticationManager) {
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	
	/**
	 * creates token based on user data
	 * 
	 * @param user details (login credentials)
	 * @return token
	 */
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody UserInfo user) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if (authentication.isAuthenticated()) {
			String s = jwtService.generateToken(user.getUsername());
			System.out.println(s);
			return s;
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}

}
