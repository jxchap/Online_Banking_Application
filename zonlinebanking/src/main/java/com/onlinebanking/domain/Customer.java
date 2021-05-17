package com.onlinebanking.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinebanking.domain.enums.Gender;
import com.onlinebanking.domain.enums.IdentityType;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	private String customerName;

	private String username;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dob;

	@OneToMany
	@JoinColumn(name = "accountId")
	@JsonIgnore
	private List<Account> customerAccounts = new ArrayList<>();

	@Embedded
	@Valid
	private Address customerAddress;

	private String customerMobile;

	private String customerEmail;

	@Enumerated(EnumType.STRING)
	private IdentityType customerIDType;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public List<Account> getCustomerAccounts() {
		return customerAccounts;
	}

	public void setCustomerAccounts(List<Account> customerAccounts) {
		this.customerAccounts = customerAccounts;
	}

	public Address getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public IdentityType getCustomerIDType() {
		return customerIDType;
	}

	public void setCustomerIDType(IdentityType customerIDType) {
		this.customerIDType = customerIDType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
