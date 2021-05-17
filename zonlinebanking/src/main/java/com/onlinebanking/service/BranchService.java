package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.domain.Branch;

public interface BranchService {

	public Branch findBranch(long branchId);
	public void deleteBranch(long branchId);
	public List<Branch> getAllBranches();
	public Branch save(Branch branch);
	
}
