package com.onlinebanking.controllerREST;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.domain.Account;
import com.onlinebanking.service.AccountService;
import com.onlinebanking.service.BranchService;
import com.onlinebanking.service.CustomerService;
import com.onlinebanking.validation.AccountValidator;

@RestController
public class AccountControllerREST {

	@Autowired
	BranchService branchService;

	@Autowired
	CustomerService customerService;

	@Autowired
	AccountService accountService;

	@Autowired
	AccountValidator accountValidator;

	@PostMapping(value = "/saveAccountREST", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> saveTheAccount(@Valid @RequestBody Account account, BindingResult br) {
		
		accountValidator.validate(account, br);
		
		if (br.hasErrors()) {
			return new ResponseEntity<>( HttpStatus.CONFLICT);
		}else {
			account.setAccountDateOpened(LocalDate.now());
			accountService.save(account);
			return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/getAccountsREST")
	public ResponseEntity<List<Account>> getTheAccounts(){
		
		var listOfAccounts = accountService.getAllAccounts();
		
		return new ResponseEntity<>(listOfAccounts, HttpStatus.OK);
		
	}
	

}
