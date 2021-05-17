package com.onlinebanking.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long branchId;

	private String branchName;

	@Embedded
	private Address branchAddress;

	@OneToMany(mappedBy = "accountBranch")
	//@JsonBackReference
	@JsonIgnore
	List<Account> branchAccountList = new ArrayList<>();

	public Branch() {

	}

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Address getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(Address branchAddress) {
		this.branchAddress = branchAddress;
	}

	public List<Account> getBranchAccountList() {
		return branchAccountList;
	}

	public void setBranchAccountList(List<Account> branchAccountList) {
		this.branchAccountList = branchAccountList;
	}
	
	
	
	

}
