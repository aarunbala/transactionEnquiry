package com.transaction.enquiry;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.enquiry.model.Transaction;
import com.transaction.enquiry.repository.TransactionRepository;

/**
 * @author arun.balasubramanian
 * 
 * Service Class that supports the TransactionEnquiryController in providing the TransactionEnquiry as a service,
 * has methods to fetch the transaction data from database. Any necessary business logic/manipulation that need to
 * happen on the data can be defined here. 
 */
@Service
public class TransactionEnquiryService implements ITransactionEnquiryService {
	
	private static final Logger log = LoggerFactory.getLogger(TransactionEnquiryService.class);

	@Autowired
	private TransactionRepository repository;

	/**
	 * Method fetches all the transactions for an accountNumber using JPA repositories.
	 * Paging has not yet been implemented. 
	 */
	@Override
	public List<Transaction> getTransactionsForAccount(Long accountNumber) {
		log.debug("Get Transactions for accounts : {}", accountNumber);
		return repository.findAllByAccountNumber(accountNumber);
	}

}
