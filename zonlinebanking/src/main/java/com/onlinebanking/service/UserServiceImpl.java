package com.onlinebanking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlinebanking.domain.User;
import com.onlinebanking.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User findUserByUsername(String username) {
		var result = userRepository.findByUsername(username);

		if (result.isPresent()) {
			return result.get();
		}

		return null;
	}

	@Override
	public void deleteUserByUsername(String username) {
		userRepository.deleteByUsername(username);

	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
