package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.domain.Customer;

public interface CustomerService {

	Customer save(Customer customer);

	Customer findById(Long customerId);

	List<Customer> findAll();
	
	Customer findByUsername(String username);

}
