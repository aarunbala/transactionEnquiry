package com.transaction.enquiry;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.enquiry.model.Transaction;
import com.transaction.enquiry.repository.TransactionRepository;

@Service
public class TransactionEnquiryService implements ITransactionEnquiryService {
	
	private static final Logger log = LoggerFactory.getLogger(TransactionEnquiryService.class);

	@Autowired
	private TransactionRepository repository;

	@Override
	public List<Transaction> getTransactionsForAccount(Long accountNumber) {
		log.debug("Get Transactions for accounts : {}", accountNumber);
		return repository.findAllByAccountNumber(accountNumber);
	}

}
