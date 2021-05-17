package com.onlinebanking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	public Optional<Customer> findByUsername(String username);

}
 