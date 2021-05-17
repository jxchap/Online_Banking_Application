package com.onlinebanking.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.onlinebanking.domain.Account;
import com.onlinebanking.domain.BankTransaction;
import com.onlinebanking.service.AccountService;

@Component
public class BankTransactionValidator implements Validator {

	@Autowired
	AccountService accountService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(BankTransaction.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BankTransaction bankTransaction = (BankTransaction) target;
		double transactionAmount = bankTransaction.getTransactionAmount();
		Account account;

		switch (bankTransaction.getBankTransactionType()) {
		case DEPOSIT:
			if (bankTransaction.getBankTransactionToAccount() == null) {
				errors.rejectValue("bankTransactionToAccount", "bankTransaction.bankTransactionToAccount.value",
						"Account number for deposit must be provided");
			}
			if (transactionAmount < 1) {
				errors.rejectValue("transactionAmount", "bankTransaction.transactionAmount.value",
						"Deposit amount must be at least $1");
			}
			break;

		case TRANSFER:
			if (bankTransaction.getBankTransactionToAccount() == null) {
				errors.rejectValue("bankTransactionToAccount", "bankTransaction.bankTransactionToAccount.value",
						"Transfer-to account number must be provided");
			}
			if (bankTransaction.getBankTransactionFromAccount() == null) {
				errors.rejectValue("bankTransactionFromAccount", "bankTransaction.bankTransactionFromAccount.value",
						"Transfer-from account number must be provided");
			}
			if (transactionAmount < 1) {
				errors.rejectValue("transactionAmount", "bankTransaction.transactionAmount.value",
						"Transfer amount must be at least $1");
			}
			if(accountService.findById(bankTransaction.getBankTransactionToAccount()) == null) {
				errors.rejectValue("bankTransactionToAccount", "bankTransaction.transactionAmount.noAccount",
						"To-account does not exist");
			}
			account = accountService.findById(bankTransaction.getBankTransactionFromAccount());
			if (account != null) {
				double currentBalance = account.getAccountBalance();
				if (currentBalance - transactionAmount < 0) {
					errors.rejectValue("transactionAmount", "bankTransaction.transactionAmount.value",
							"Remaining account balance must not be below $0");
				}
			}

			break;
		case WITHDRAWAL:
			if (bankTransaction.getBankTransactionFromAccount() == null) {
				errors.rejectValue("bankTransactionFromAccount", "bankTransaction.bankTransactionFromAccount.value",
						"Account number for withdrawal must be provided");
			}
			if (transactionAmount < 1) {
				errors.rejectValue("transactionAmount", "bankTransaction.transactionAmount.value",
						"Withdrawal amount must be at least $1");
			}

			account = accountService.findById(bankTransaction.getBankTransactionFromAccount());
			if (account != null) {
				double currentBalance = account.getAccountBalance();
				if (currentBalance - transactionAmount < 0) {
					errors.rejectValue("transactionAmount", "bankTransaction.transactionAmount.value",
							"Remaining account balance must not be below $0");
				}
			}

			break;
		case LOANPAY:
			if (bankTransaction.getBankTransactionToAccount() == null) {
				errors.rejectValue("bankTransactionToAccount", "bankTransaction.bankTransactionToAccount.value",
						"Account number for loan pay must be provided");
			}
			if (transactionAmount < 1) {
				errors.rejectValue("transactionAmount", "bankTransaction.transactionAmount.value",
						"Payment amount must be at least $1");
			}
			break;
		}

	}

}
