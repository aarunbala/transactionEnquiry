package com.transaction.enquiry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.transaction.enquiry.model.Transaction;
import com.transaction.enquiry.repository.TransactionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TransactionEnquiryService.class })
@AutoConfigureMockMvc
@WebAppConfiguration
public class TransactionEnquiryServiceTest {
	
	@MockBean
	private TransactionRepository repo;
	
	@Autowired
	private TransactionEnquiryService service;

	@Test
	public void testGetAllTransactionsForAccount() {
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(12345678L);
		transaction.setAmount(new BigDecimal(9898.01));
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);
		
		when(repo.findAllByAccountNumber(12345678L)).thenReturn(transactions);
		
		List<Transaction> response = service.getTransactionsForAccount(12345678L);
		
		assertNotNull(response);
		assertNotNull(response.get(0));
		assertThat(response.get(0)).isEqualTo(transaction);
	}

}
