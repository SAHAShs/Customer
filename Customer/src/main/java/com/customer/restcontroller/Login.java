package com.customer.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.config.UserInfo;
import com.customer.entity.Admin;
import com.customer.service.JwtService;
import com.customer.service.LoginService;

@RestController
public class Login {
	private final LoginService loginService;

	private AuthenticationManager authenticationManager;

	private JwtService jwtService;
	
	@Value("${default.username}")
	private String defaultUsername;

	@Value("${default.password}")
	private String defaultPassword;

	@Autowired
	public Login(LoginService loginService, AuthenticationManager authenticationManager, JwtService jwtService) {
		this.loginService = loginService;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	
	/**
	 * Handles the HTTP POST request for user login.
	 *
	 * @param loginRequest The UserInfo object containing username and password.
	 * @return ResponseEntity containing a JWT token if authentication is successful.
	 */
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody UserInfo loginRequest) {
		try {
			if(loginRequest.getUsername().equals(defaultUsername) && loginRequest.getPassword().equals(defaultPassword)) {
				String token = jwtService.generateToken(loginRequest.getUsername());//generateing token with username
				Map<String, String> response = new HashMap<>();
				response.put("token", token);//adding token to map
				//when processing future request user can add token to header
				return ResponseEntity.ok(response);
			}
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));//authenticates that the user in valid by checking db
			//dont want to use DB then got to SecurityConfig->detailsService enable commented lines

			if (authentication.isAuthenticated()) {
				String token = jwtService.generateToken(loginRequest.getUsername());//generateing token with username
				Map<String, String> response = new HashMap<>();
				response.put("token", token);//adding token to map
				//when processing future request user can add token to header
				return ResponseEntity.ok(response);
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}
	
	/**
	 * create admin user to access application
	 * these credentials are used to login into to the application
	 * 
	 * @param admin 
	 * @return String
	 */
	@PostMapping("/new")
	public String addUser(@RequestBody Admin admin) {
		return loginService.addUser(admin);
	}
}
