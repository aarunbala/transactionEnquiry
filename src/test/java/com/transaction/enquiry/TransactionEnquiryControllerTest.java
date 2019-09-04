package com.transaction.enquiry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.transaction.enquiry.model.Transaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TransactionEnquiryController.class })
@AutoConfigureMockMvc
@WebAppConfiguration
public class TransactionEnquiryControllerTest {

	@MockBean
	private ITransactionEnquiryService service;

	@Autowired
	private TransactionEnquiryController controller;

	@Test
	public void testSuccess200() {
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(12345678L);
		transaction.setAmount(new BigDecimal(9898.01));
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);

		when(service.getTransactionsForAccount(12345678L)).thenReturn(transactions);

		ResponseEntity<List<Transaction>> response = controller.getAllTransactionsForAccount(12345678L);

		assertNotNull(response);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertNotNull(response.getBody());
		List<Transaction> actualResponse = response.getBody();
		assertNotNull(actualResponse.get(0));
		assertThat(actualResponse.get(0).getAmount()).isEqualTo(new BigDecimal(9898.01));
	}

	@Test
	public void testSuccess201() {
		List<Transaction> transactions = new ArrayList<>();

		when(service.getTransactionsForAccount(12345678L)).thenReturn(transactions);

		ResponseEntity<List<Transaction>> response = controller.getAllTransactionsForAccount(12345678L);

		assertNotNull(response);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		assertNull(response.getBody());
	}
}
