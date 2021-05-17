package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.domain.BankTransaction;

public interface BankTransactionService {

	public BankTransaction findBranch(long bankTransactionId);

	public List<BankTransaction> getAllBankTransactions();

	public BankTransaction save(BankTransaction bankTransaction);

}
