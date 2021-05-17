package com.onlinebanking.hibernate;

import java.util.List;

import com.onlinebanking.domain.BankTransaction;

public interface BankTransactionDao {

	public BankTransaction save(BankTransaction employee);

	public BankTransaction findById(long id);
	
	public List<BankTransaction> findAll();

}
