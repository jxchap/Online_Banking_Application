package com.onlinebanking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.domain.Account;
import com.onlinebanking.domain.Customer;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	List<Account> findByAccountCustomer(Customer customer);

}
