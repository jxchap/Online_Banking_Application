package com.onlinebanking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.domain.Branch;
import com.onlinebanking.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	BranchRepository branchRepository;

	@Override
	public Branch findBranch(long branchId) {
		var result = branchRepository.findById(branchId);

		if (result.isPresent()) {
			return result.get();
		}

		return null;
	}

	@Override
	public void deleteBranch(long branchId) {
		branchRepository.deleteById(branchId);

	}

	@Override
	public List<Branch> getAllBranches() {

		return branchRepository.findAll();
	}

	@Override
	public Branch save(Branch branch) {
		return branchRepository.save(branch);
	}

}
