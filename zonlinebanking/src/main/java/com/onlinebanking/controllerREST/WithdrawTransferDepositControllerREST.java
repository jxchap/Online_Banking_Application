package com.onlinebanking.controllerREST;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.domain.Account;
import com.onlinebanking.domain.BankTransaction;
import com.onlinebanking.domain.enums.AccountType;
import com.onlinebanking.domain.enums.TransactionType;
import com.onlinebanking.service.AccountService;
import com.onlinebanking.service.BankTransactionService;
import com.onlinebanking.service.CustomerService;
import com.onlinebanking.service.UserService;
import com.onlinebanking.validation.BankTransactionValidator;

@RestController
public class WithdrawTransferDepositControllerREST {

	@Autowired
	AccountService accountService;

	@Autowired
	UserService userService;

	@Autowired
	CustomerService customerService;

	@Autowired
	BankTransactionService bankTransactionService;

	@Autowired
	BankTransactionValidator bankTransactionValidator;

	@PostMapping("/saveDepositREST")
	public ResponseEntity<?> saveTheDeposit(@RequestBody BankTransaction bankTransaction, BindingResult br) {

		setBankTransactionFields(bankTransaction, TransactionType.DEPOSIT);

		bankTransactionValidator.validate(bankTransaction, br);

		if (br.hasErrors()) {
			return new ResponseEntity<>(br.getAllErrors().toString(), HttpStatus.CONFLICT);
		}

		var accountToUpdate = accountService.findById(bankTransaction.getBankTransactionToAccount());
		accountToUpdate.setAccountBalance(accountToUpdate.getAccountBalance() + bankTransaction.getTransactionAmount());
		accountService.save(accountToUpdate);
		bankTransactionService.save(bankTransaction);

		return new ResponseEntity<>(bankTransaction, HttpStatus.OK);

	}

	@PostMapping("/saveWithdrawalREST")
	public ResponseEntity<?> saveTheWithdrawal(@RequestBody BankTransaction bankTransaction, BindingResult br) {

		setBankTransactionFields(bankTransaction, TransactionType.WITHDRAWAL);

		bankTransactionValidator.validate(bankTransaction, br);
		if (br.hasErrors()) {
			return new ResponseEntity<>(br.getAllErrors().toString(), HttpStatus.CONFLICT);
		}

		var accountToUpdate = accountService.findById(bankTransaction.getBankTransactionFromAccount());
		accountToUpdate.setAccountBalance(accountToUpdate.getAccountBalance() - bankTransaction.getTransactionAmount());
		accountService.save(accountToUpdate);
		bankTransactionService.save(bankTransaction);

		return new ResponseEntity<>(bankTransaction, HttpStatus.OK);

	}

	@PostMapping("/saveLoanPaymentREST")
	public ResponseEntity<?> saveTheLoanPayment(@RequestBody BankTransaction bankTransaction, BindingResult br) {

		setBankTransactionFields(bankTransaction, TransactionType.LOANPAY);

		bankTransactionValidator.validate(bankTransaction, br);
		if (br.hasErrors()) {
			return new ResponseEntity<>(br.getAllErrors().toString(), HttpStatus.CONFLICT);
		}

		var accountToUpdate = accountService.findById(bankTransaction.getBankTransactionToAccount());
		accountToUpdate.setAccountBalance(accountToUpdate.getAccountBalance() - bankTransaction.getTransactionAmount());
		accountService.save(accountToUpdate);
		bankTransactionService.save(bankTransaction);

		return new ResponseEntity<>(bankTransaction, HttpStatus.OK);

	}

	@PostMapping("/saveTransferREST")
	public ResponseEntity<?> saveTheTransfer(@RequestBody BankTransaction bankTransaction, BindingResult br) {

		setBankTransactionFields(bankTransaction, TransactionType.TRANSFER);

		bankTransactionValidator.validate(bankTransaction, br);
		if (br.hasErrors()) {
			return new ResponseEntity<>(br.getAllErrors().toString(), HttpStatus.CONFLICT);
		}

		Account fromAccountIdObject = accountService.findById(bankTransaction.getBankTransactionFromAccount());
		Account toAccountIdObject = accountService.findById(bankTransaction.getBankTransactionToAccount());

		fromAccountIdObject
				.setAccountBalance(fromAccountIdObject.getAccountBalance() - bankTransaction.getTransactionAmount());
		if (toAccountIdObject.getAccountType().equals(AccountType.LOAN)) {
			toAccountIdObject
					.setAccountBalance(toAccountIdObject.getAccountBalance() - bankTransaction.getTransactionAmount());
		} else {
			toAccountIdObject
					.setAccountBalance(toAccountIdObject.getAccountBalance() + bankTransaction.getTransactionAmount());
		}

		accountService.save(fromAccountIdObject);
		accountService.save(toAccountIdObject);
		bankTransactionService.save(bankTransaction);

		return new ResponseEntity<>(bankTransaction, HttpStatus.OK);

	}

	@GetMapping("/getBankTransactions")
	public ResponseEntity<List<BankTransaction>> getTransactions() {
		var result = bankTransactionService.getAllBankTransactions();

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	// utility method
	public static void setBankTransactionFields(BankTransaction bankTransaction, TransactionType transType) {
		bankTransaction.setBankTransactionDateTime(LocalDateTime.now());
		bankTransaction.setBankTransactionType(transType);
		bankTransaction.setInitiatedByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		switch (transType) {
		case DEPOSIT:
			bankTransaction.setBankTransactionFromAccount(0);
			bankTransaction.setComments("Deposit");
			break;
		case WITHDRAWAL:
			bankTransaction.setBankTransactionToAccount(0);
			bankTransaction.setComments("WithDrawal");
			break;
		case LOANPAY:
			bankTransaction.setBankTransactionFromAccount(0);
			bankTransaction.setComments("Loan Payment");
			break;
		case TRANSFER:
			bankTransaction.setComments("Transfer");
			break;

		}
	}
}
