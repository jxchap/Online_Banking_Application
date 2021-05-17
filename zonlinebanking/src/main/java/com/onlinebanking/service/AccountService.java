package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.domain.Account;
import com.onlinebanking.domain.Customer;

public interface AccountService {
	
	Account save(Account account);
	
	List<Account> getCustomerAccounts(Customer customer);
	
	Account findById(long accountId);
	
	List<Account> getAllAccounts();
	

}
