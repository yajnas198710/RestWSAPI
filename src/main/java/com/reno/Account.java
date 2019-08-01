package com.reno;

import java.util.ArrayList;

public class Account {
	String accountHolder;
	String accountType;
	double accountNumber;
	double aacountBalance;
	String action;
	String accountResponse;
	String input;
	ArrayList<Transactions> transactionHistory;

	public ArrayList<Transactions> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(ArrayList<Transactions> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}

	public Account() {
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAacountBalance() {
		return aacountBalance;
	}

	public void setAacountBalance(double aacountBalance) {
		this.aacountBalance = aacountBalance;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public double getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(double accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Account(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	public String getAccountResponse() {
		return accountResponse;
	}

	public void setAccountResponse(String accountResponse) {
		this.accountResponse = accountResponse;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

}