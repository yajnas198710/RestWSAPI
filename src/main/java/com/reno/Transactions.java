package com.reno;

class Transactions {

	String transactionStatus;
	String transactionDate;
	String transactionType;

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	@Override
	public String toString() {
		return "TransactionStatus "+transactionStatus+" transactionDate "+transactionDate +"";
	}
}