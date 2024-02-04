package com.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.customer.dao.LoginDao;
import com.customer.entity.Admin;

@Service
public class LoginService {

	private final LoginDao dao;

	private PasswordEncoder encoder;

	@Autowired
	public LoginService(LoginDao dao, PasswordEncoder encoder) {
		this.dao = dao;
		this.encoder = encoder;
	}

	/**
	 * 
	 * @param admin
	 * @return save message
	 */
	public String addUser(Admin admin) {
		admin.setPassword(encoder.encode(admin.getPassword()));
		try {
			dao.save(admin);
		} catch (Exception e) {
			return "error creating admin";
		}
		return "saved";
	}

}
