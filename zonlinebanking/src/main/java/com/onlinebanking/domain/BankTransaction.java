package com.onlinebanking.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.onlinebanking.domain.enums.TransactionType;

@Entity
public class BankTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bankTransactionId;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime bankTransactionDateTime;

	private Long bankTransactionFromAccount; // can be used for transfer and withdrawals

	private Long bankTransactionToAccount; // used for transfer between accounts and deposits

	@Enumerated(EnumType.STRING)
	private TransactionType bankTransactionType;

	private String comments;

	private String initiatedByUsername;
	
	private long transactionAmount;

	public Long getBankTransactionId() {
		return bankTransactionId;
	}

	public void setBankTransactionId(Long bankTransactionId) {
		this.bankTransactionId = bankTransactionId;
	}

	public LocalDateTime getBankTransactionDateTime() {
		return bankTransactionDateTime;
	}

	public void setBankTransactionDateTime(LocalDateTime bankTransactionDateTime) {
		this.bankTransactionDateTime = bankTransactionDateTime;
	}

	public Long getBankTransactionFromAccount() {
		return bankTransactionFromAccount;
	}

	public void setBankTransactionFromAccount(long bankTransactionFromAccount) {
		this.bankTransactionFromAccount = bankTransactionFromAccount;
	}

	public Long getBankTransactionToAccount() {
		return bankTransactionToAccount;
	}

	public void setBankTransactionToAccount(long bankTransactionToAccount) {
		this.bankTransactionToAccount = bankTransactionToAccount;
	}

	public TransactionType getBankTransactionType() {
		return bankTransactionType;
	}

	public void setBankTransactionType(TransactionType bankTransactionType) {
		this.bankTransactionType = bankTransactionType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getInitiatedByUsername() {
		return initiatedByUsername;
	}

	public void setInitiatedByUsername(String initiatedByUsername) {
		this.initiatedByUsername = initiatedByUsername;
	}

	public long getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	
}
