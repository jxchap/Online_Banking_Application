package com.onlinebanking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.domain.Customer;
import com.onlinebanking.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer findById(Long customerId) {
		var result = customerRepository.findById(customerId);

		if (result.isPresent()) {
			return result.get();
		}

		return null;
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findByUsername(String username) {
		var result = customerRepository.findByUsername(username);

		
		if (result.isPresent()) {
			return result.get();
		}
		
		

		return null;
	}

}
