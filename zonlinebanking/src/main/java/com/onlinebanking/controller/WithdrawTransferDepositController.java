package com.onlinebanking.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlinebanking.domain.Account;
import com.onlinebanking.domain.BankTransaction;
import com.onlinebanking.domain.User;
import com.onlinebanking.domain.enums.AccountType;
import com.onlinebanking.domain.enums.TransactionType;
import com.onlinebanking.service.AccountService;
import com.onlinebanking.service.BankTransactionService;
import com.onlinebanking.service.CustomerService;
import com.onlinebanking.service.MailService;
import com.onlinebanking.service.UserService;
import com.onlinebanking.validation.BankTransactionValidator;

@Controller
public class WithdrawTransferDepositController {

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
	
	@Autowired
	MailService mailService;

	@RequestMapping("/withdrawTransferDeposit")
	public ModelAndView getWTDForm(Model model) {
		ModelAndView modelAndView = new ModelAndView("withdrawTransferDeposit");

		model.addAttribute("accountTypes", AccountType.values());
		
		
		var auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = userService.findUserByUsername(auth.getName());
		var existingCustomer = customerService.findByUsername(loggedInUser.getUsername());

		model.addAttribute("customerAccounts", accountService.getCustomerAccounts(existingCustomer));

		return modelAndView;
	}

	@RequestMapping("/renderAccountDeposit")
	public String renderTheAccountDeposit(@RequestParam long accountId, Model model) {

		Account accountForDeposit = accountService.findById(accountId);

		model.addAttribute("accountForDeposit", accountForDeposit);
		model.addAttribute("bankTransaction", new BankTransaction());
		model.addAttribute("accountTypes", AccountType.values());

		var auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = userService.findUserByUsername(auth.getName());
		var existingCustomer = customerService.findByUsername(loggedInUser.getUsername());

		model.addAttribute("customerAccounts", accountService.getCustomerAccounts(existingCustomer));
		return "withdrawTransferDeposit";

	}

	@RequestMapping("/saveDeposit")
	public String saveTheDeposit(BankTransaction bankTransaction, BindingResult br, Model model) {

		setBankTransactionFields(bankTransaction, TransactionType.DEPOSIT);

		bankTransactionValidator.validate(bankTransaction, br);

		if (br.hasErrors()) {
			model.addAttribute("accountForDeposit", accountService.findById(bankTransaction.getBankTransactionToAccount()));
			model.addAttribute("customerAccounts", accountService.getCustomerAccounts(customerService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())));
			model.addAttribute("accountTypes", AccountType.values());
			return "withdrawTransferDeposit";
		}

		var accountToUpdate = accountService.findById(bankTransaction.getBankTransactionToAccount());
		accountToUpdate.setAccountBalance(accountToUpdate.getAccountBalance() + bankTransaction.getTransactionAmount());
		accountService.save(accountToUpdate);
		bankTransactionService.save(bankTransaction);
		
		var sentTo = userService.findUserByUsername(bankTransaction.getInitiatedByUsername());
		mailService.sendEmail(sentTo.getEmail(), "Notice of Deposit", "Hello, you made a deposit of $" + bankTransaction.getTransactionAmount() + " to account # " + bankTransaction.getBankTransactionToAccount());

		return "redirect:withdrawTransferDeposit";

	}

	@RequestMapping("/renderAccountWithDrawal")
	public String renderTheAccountWithDrawal(@RequestParam long accountId, Model model) {

		Account accountForWithDrawal = accountService.findById(accountId);

		model.addAttribute("accountForWithDrawal", accountForWithDrawal);
		model.addAttribute("bankTransaction", new BankTransaction());
		model.addAttribute("accountTypes", AccountType.values());

		var auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = userService.findUserByUsername(auth.getName());
		var existingCustomer = customerService.findByUsername(loggedInUser.getUsername());

		model.addAttribute("customerAccounts", accountService.getCustomerAccounts(existingCustomer));

		return "withdrawTransferDeposit";

	}

	@RequestMapping("/saveWithdrawal")
	public String saveTheWithdrawal(BankTransaction bankTransaction, BindingResult br, Model model) {
		
		setBankTransactionFields(bankTransaction, TransactionType.WITHDRAWAL);

		
		bankTransactionValidator.validate(bankTransaction, br);
		if (br.hasErrors()) {
			model.addAttribute("accountForWithDrawal", accountService.findById(bankTransaction.getBankTransactionFromAccount()));
			model.addAttribute("customerAccounts", accountService.getCustomerAccounts(customerService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())));
			model.addAttribute("accountTypes", AccountType.values());
			return "withdrawTransferDeposit";
		}
		
		var accountToUpdate = accountService.findById(bankTransaction.getBankTransactionFromAccount());
		accountToUpdate.setAccountBalance(accountToUpdate.getAccountBalance() - bankTransaction.getTransactionAmount());
		accountService.save(accountToUpdate);
		bankTransactionService.save(bankTransaction);
		
		var sentTo = userService.findUserByUsername(bankTransaction.getInitiatedByUsername());
		mailService.sendEmailWithAttachment(sentTo.getEmail(), "Notice of Withdrawal", "Hello, you made a withdrawal of $" + bankTransaction.getTransactionAmount() + " from account # " + bankTransaction.getBankTransactionFromAccount());

		return "redirect:withdrawTransferDeposit";

	}

	@RequestMapping("/renderLoanPayment")
	public String renderTheLoanPayment(@RequestParam long accountId, Model model) {

		Account accountForLoanPayment = accountService.findById(accountId);

		model.addAttribute("accountForLoanPayment", accountForLoanPayment);
		model.addAttribute("bankTransaction", new BankTransaction());
		model.addAttribute("accountTypes", AccountType.values());

		var auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = userService.findUserByUsername(auth.getName());
		var existingCustomer = customerService.findByUsername(loggedInUser.getUsername());

		model.addAttribute("customerAccounts", accountService.getCustomerAccounts(existingCustomer));

		return "withdrawTransferDeposit";

	}

	@RequestMapping("/saveLoanPayment")
	public String saveTheLoanPayment(BankTransaction bankTransaction, BindingResult br, Model model) {
		
		setBankTransactionFields(bankTransaction, TransactionType.LOANPAY);
		
		bankTransactionValidator.validate(bankTransaction, br);
		if (br.hasErrors()) {
			model.addAttribute("accountForLoanPayment", accountService.findById(bankTransaction.getBankTransactionToAccount()));
			model.addAttribute("customerAccounts", accountService.getCustomerAccounts(customerService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())));
			model.addAttribute("accountTypes", AccountType.values());
			return "withdrawTransferDeposit";
		}

		var accountToUpdate = accountService.findById(bankTransaction.getBankTransactionToAccount());
		accountToUpdate.setAccountBalance(accountToUpdate.getAccountBalance() - bankTransaction.getTransactionAmount());
		accountService.save(accountToUpdate);
		bankTransactionService.save(bankTransaction);

		return "redirect:withdrawTransferDeposit";

	}

	@RequestMapping("/renderAccountTransfer")
	public String renderTheAccountTransfer(@RequestParam long accountId, Model model) {

		Account accountForTransfer = accountService.findById(accountId);
		;

		model.addAttribute("accountForTransfer", accountForTransfer);
		model.addAttribute("bankTransaction", new BankTransaction());
		model.addAttribute("accountTypes", AccountType.values());

		var auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = userService.findUserByUsername(auth.getName());
		var existingCustomer = customerService.findByUsername(loggedInUser.getUsername());

		model.addAttribute("customerAccounts", accountService.getCustomerAccounts(existingCustomer));
		return "withdrawTransferDeposit";

	}

	@RequestMapping("/saveTransfer")
	public String saveTheTransfer(BankTransaction bankTransaction, BindingResult br, Model model) {
		
		setBankTransactionFields(bankTransaction, TransactionType.TRANSFER);
		
		bankTransactionValidator.validate(bankTransaction, br);
		if (br.hasErrors()) {
			model.addAttribute("accountForTransfer", accountService.findById(bankTransaction.getBankTransactionFromAccount()));
			model.addAttribute("customerAccounts", accountService.getCustomerAccounts(customerService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())));
			model.addAttribute("accountTypes", AccountType.values());
			return "withdrawTransferDeposit";
		}

		Account fromAccountIdObject = accountService.findById(bankTransaction.getBankTransactionFromAccount());
		Account toAccountIdObject = accountService.findById(bankTransaction.getBankTransactionToAccount());

		fromAccountIdObject.setAccountBalance(fromAccountIdObject.getAccountBalance() - bankTransaction.getTransactionAmount());
		if(toAccountIdObject.getAccountType().equals(AccountType.LOAN)) {
			toAccountIdObject.setAccountBalance(toAccountIdObject.getAccountBalance() - bankTransaction.getTransactionAmount());
		}else {
			toAccountIdObject.setAccountBalance(toAccountIdObject.getAccountBalance() + bankTransaction.getTransactionAmount());
		}
		

		accountService.save(fromAccountIdObject);
		accountService.save(toAccountIdObject);
		bankTransactionService.save(bankTransaction);

		return "redirect:withdrawTransferDeposit";

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
