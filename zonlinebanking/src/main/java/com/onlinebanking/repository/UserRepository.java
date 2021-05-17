package com.onlinebanking.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByUsername(String username);

	@Transactional
	public void deleteByUsername(String username);
}
