package com.onlinebanking.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.onlinebanking.domain.Role;
import com.onlinebanking.domain.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User userFromDB = userService.findUserByUsername(username);
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (Role role : userFromDB.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(userFromDB.getUsername(), userFromDB.getPassword(),
				authorities);
	}

}
