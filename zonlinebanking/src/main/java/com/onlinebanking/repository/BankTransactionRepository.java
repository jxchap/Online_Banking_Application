package com.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.domain.BankTransaction;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long>{

}
