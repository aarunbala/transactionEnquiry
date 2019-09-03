package com.transaction.enquiry;

import java.util.List;
import com.transaction.enquiry.model.Transaction;

public interface ITransactionEnquiryService {
	List<Transaction> getTransactionsForAccount(Long accountNumber);
}
