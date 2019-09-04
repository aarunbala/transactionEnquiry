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

/**
 * @author arun.balasubramanian
 * 
 * Controller class that provides Transaction Enquiry functionality.
 */
@RestController
public class TransactionEnquiryController {

	private static final Logger log = LoggerFactory.getLogger(TransactionEnquiryController.class);

	@Autowired
	private ITransactionEnquiryService service;

	/**
	 * Method gets all the transactions for the provided account; If no transactions are available for an account,
	 * then responds with an 204 status code. Also, responds with 400 for invalid arguments.
	 * 
	 * @param accountNumber
	 * @return List of Transactions
	 */
	@GetMapping(path = "/transactionEnquiry/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaction>> getAllTransactionsForAccount(@PathVariable Long accountNumber) {
		
		log.info("Get transactions for Account : {}", accountNumber);
		
		List<Transaction> transactions = service.getTransactionsForAccount(accountNumber);
		ResponseEntity<List<Transaction>> response = new ResponseEntity<>(transactions, HttpStatus.OK);
		
		//Responding with HttpStatus.NO_CONTENT 204 when there are no transactions
		if(transactions == null || transactions.isEmpty()) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	/**
	 * Method prevents any other resources from being accessed, throws PathNotFoundException which
	 * will get converted to HTTPStatus.BAD_REQUEST 400 status code.
	 * 
	 * @throws PathNotFoundException
	 */
	@GetMapping("/*")
	public void otherPaths() throws PathNotFoundException {
		throw new PathNotFoundException();
	}
}
