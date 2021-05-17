package com.onlinebanking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.domain.BankTransaction;
import com.onlinebanking.hibernate.BankTransactionDao;
import com.onlinebanking.repository.BankTransactionRepository;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {

	@Autowired
	BankTransactionRepository bankTransactionRepository;
	
	@Autowired
	BankTransactionDao bankTransactionDao ;

	@Override
	public BankTransaction findBranch(long bankTransactionId) {
		
		var result = bankTransactionRepository.findById(bankTransactionId);

		return result.isPresent() ? result.get() : null;
		
	}

	@Override
	public List<BankTransaction> getAllBankTransactions() {
		
		//var result = bankTransactionRepository.findAll();
		
		var result = bankTransactionDao.findAll();
		
		return result;
	}

	@Override
	public BankTransaction save(BankTransaction bankTransaction) {
		return bankTransactionRepository.save(bankTransaction);
	}

}
