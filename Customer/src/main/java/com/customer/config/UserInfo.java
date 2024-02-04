package com.customer.config;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.customer.entity.Admin;

//used during login processs
@SuppressWarnings("serial")
public class UserInfo implements UserDetails {
	private String username;

	private String password;

	public UserInfo(Admin admin) {
		this.username = admin.getUserName();
		this.password = admin.getPassword();
		System.out.println(admin.getPassword() + " " + admin.getUserName());
	}
	public UserInfo() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new HashSet<GrantedAuthority>();
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
