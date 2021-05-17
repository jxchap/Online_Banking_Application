package com.onlinebanking.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.onlinebanking.domain.enums.AccountType;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@ManyToOne
	private Branch accountBranch;

	private Double accountBalance;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate accountDateOpened;

	@ManyToOne
	private Customer accountCustomer;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Branch getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(Branch accountBranch) {
		this.accountBranch = accountBranch;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public LocalDate getAccountDateOpened() {
		return accountDateOpened;
	}

	public void setAccountDateOpened(LocalDate accountDateOpened) {
		this.accountDateOpened = accountDateOpened;
	}

	public Customer getAccountCustomer() {
		return accountCustomer;
	}

	public void setAccountCustomer(Customer accountCustomer) {
		this.accountCustomer = accountCustomer;
	}

}
