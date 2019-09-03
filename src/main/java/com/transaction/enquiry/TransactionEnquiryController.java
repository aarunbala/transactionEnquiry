package com.transaction.enquiry;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.enquiry.model.Transaction;

@RestController
public class TransactionEnquiryController {

	private static final Logger log = LoggerFactory.getLogger(TransactionEnquiryController.class);

	@Autowired
	private ITransactionEnquiryService service;

	@GetMapping(path = "/transactionEnquiry/{accountNumberStr}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaction>> getAllTransactionsForAccount(@PathVariable String accountNumberStr) {
		
		log.info("Get transactions for Account : {}", accountNumberStr);
		Long accountNumber = Long.parseLong(accountNumberStr);
		List<Transaction> transactions = service.getTransactionsForAccount(accountNumber);
		ResponseEntity<List<Transaction>> response = new ResponseEntity<>(transactions, HttpStatus.OK);
		if(transactions == null || transactions.isEmpty()) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/*")
	public void otherPaths() throws PathNotFoundException {
		throw new PathNotFoundException();
	}
}
