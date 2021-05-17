package com.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.domain.Branch;


public interface BranchRepository extends JpaRepository<Branch, Long>{

	
	
}
