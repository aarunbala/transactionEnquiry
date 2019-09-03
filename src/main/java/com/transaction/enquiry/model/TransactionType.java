package com.transaction.enquiry.model;

public enum TransactionType {
	DEBIT("DEBIT"), CREDIT("CREDIT");

	private String type;

	private TransactionType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}
}
