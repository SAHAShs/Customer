package com.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.customer.config.UserInfo;
import com.customer.dao.LoginDao;
import com.customer.entity.Admin;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	@Autowired
	private LoginDao loginDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> info = java.util.Optional.empty();
		try {
			info = Optional.of(loginDao.findByUserName(username));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info.map(UserInfo::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	}

}
