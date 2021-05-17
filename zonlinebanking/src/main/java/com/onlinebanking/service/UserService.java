package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.domain.User;

public interface UserService {
	
	public User findUserByUsername(String username);

	public void deleteUserByUsername(String username);

	public List<User> getAllUsers();

	public User save(User user);

}
