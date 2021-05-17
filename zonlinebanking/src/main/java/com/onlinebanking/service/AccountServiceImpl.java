package com.onlinebanking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.domain.Account;
import com.onlinebanking.domain.Customer;
import com.onlinebanking.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public List<Account> getCustomerAccounts(Customer customer) {
		return accountRepository.findByAccountCustomer(customer);
	}

	@Override
	public Account findById(long accountId) {

		var result = accountRepository.findById(accountId);

		return result.isPresent() ? result.get() : null;
	}

	@Override
	public List<Account> getAllAccounts() {

		var result = accountRepository.findAll();

		return result;

	}

}
