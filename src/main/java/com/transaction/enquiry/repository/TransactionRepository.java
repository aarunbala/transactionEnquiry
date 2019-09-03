package com.transaction.enquiry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.transaction.enquiry.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	List<Transaction> findAllByAccountNumber(Long accountNumber);
}
