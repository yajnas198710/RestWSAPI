package com.reno;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

@Path("accountManager")
public class AccountManager {

	private static final Map<String, Account> accountMap = new ConcurrentHashMap<String, Account>();
	private static final ArrayList<String> accountHoler = new ArrayList<String>();
	final static Logger logger = Logger.getLogger(ClassName.class);

	@GET
	@Path("transferFund")
	@Produces(MediaType.APPLICATION_JSON)
	public Account transferFund(@Context UriInfo uriInfo) {
		Account account = new Account("transferFund");
		boolean transferFlag = false;
		String fromAccountNumber = uriInfo.getQueryParameters().get("fromAccountNumber").get(0).toString();
		String toAccountNumber = uriInfo.getQueryParameters().get("toAccountNumber").get(0).toString();
		double depositAmmount = Double.parseDouble(uriInfo.getQueryParameters().get("deposit").get(0).toString());
		logger.info("This is info : " + " Transfer Fund Request fromAccountNumber " + fromAccountNumber
				+ " toAccountNumber " + toAccountNumber + " depositAmmount" + depositAmmount);

		if (null != accountMap && accountMap.containsKey(fromAccountNumber)
				&& accountMap.containsKey(toAccountNumber)) {

			// Withdraw Account
			Account depositBalance = new Account();
			depositBalance.setAccountHolder(accountMap.get(fromAccountNumber).getAccountHolder());
			depositBalance.setAccountNumber(accountMap.get(fromAccountNumber).getAccountNumber());

			if (depositAmmount <= accountMap.get(fromAccountNumber).getAacountBalance()) {
				depositBalance
						.setAacountBalance(accountMap.get(fromAccountNumber).getAacountBalance() - depositAmmount);
				transferFlag = true;
			}
			if(!transferFlag){
				account.setAccountResponse("Balance is Not Sufficiant");
				return account;
			}
			ArrayList<Transactions> depositHistory = new ArrayList<Transactions>();
			Transactions depositTransaction = new Transactions();
			depositHistory.add(depositTransaction);
			depositTransaction.setTransactionDate(new Date().toString());
			depositTransaction.setTransactionStatus("SUCCESS");
			depositTransaction.setTransactionType("Deposit");
			depositBalance.setTransactionHistory(depositHistory);
			accountMap.replace(fromAccountNumber, depositBalance);

			logger.info("This is info : " + "Updated Balance " + accountMap.get(fromAccountNumber).getAacountBalance());

			// Deposit Account

			Account withdrawBalance = new Account();
			withdrawBalance.setAccountHolder(accountMap.get(toAccountNumber).getAccountHolder());
			withdrawBalance.setAccountNumber(accountMap.get(toAccountNumber).getAccountNumber());

			if (transferFlag) {
				withdrawBalance.setAacountBalance(accountMap.get(toAccountNumber).getAacountBalance() + depositAmmount);
				transferFlag = false;
			}

			ArrayList<Transactions> withdrawHistory = new ArrayList<Transactions>();
			Transactions withdrawTransaction = new Transactions();
			withdrawHistory.add(withdrawTransaction);
			withdrawTransaction.setTransactionDate(new Date().toString());
			withdrawTransaction.setTransactionStatus("SUCCESS");
			withdrawTransaction.setTransactionType("Withdraw");
			withdrawBalance.setTransactionHistory(withdrawHistory);
			accountMap.replace(toAccountNumber, withdrawBalance);

			logger.info("This is info : " + "Updated Balance " + accountMap.get(toAccountNumber).getAacountBalance());

			logger.info("This is info : " + "Printing Deposit Account Details: - START ");

			for (Map.Entry<String, Account> entry : accountMap.entrySet()) {
				logger.info("This is info : " + "Account Number : " + entry.getKey() + " Account  Holder : "
						+ entry.getValue().getAccountHolder() + " Account  Balance : "
						+ entry.getValue().getAacountBalance());
				logger.info("This is info : " + "Transaction History "
						+ entry.getValue().getTransactionHistory().toString());

			}
			logger.info("This is info : " + "Printing Deposit Account Details: - STOP ");
		}

		return account;
	}

	@GET
	@Path("depositFund")
	@Produces(MediaType.APPLICATION_JSON)
	public Account depositFund(@Context UriInfo uriInfo) {
		String accountNumber = uriInfo.getQueryParameters().get("accountNumber").get(0).toString();
		Account account = new Account("depositFund");

		if (null != accountMap && accountMap.containsKey(accountNumber)) {

			Account updateBalance = new Account();
			updateBalance.setAacountBalance(
					Double.parseDouble(uriInfo.getQueryParameters().get("deposit").get(0).toString()));
			updateBalance.setAccountHolder(accountMap.get(accountNumber).getAccountHolder());
			updateBalance.setAccountNumber(accountMap.get(accountNumber).getAccountNumber());

			ArrayList<Transactions> transactionHistory = new ArrayList<Transactions>();
			Transactions transaction = new Transactions();
			transactionHistory.add(transaction);
			transaction.setTransactionDate(new Date().toString());
			transaction.setTransactionStatus("SUCCESS");
			transaction.setTransactionType("Deposit");
			updateBalance.setTransactionHistory(transactionHistory);

			accountMap.replace(accountNumber, updateBalance);

			account.setAccountHolder(accountMap.get(accountNumber).getAccountHolder());
			account.setAccountNumber(accountMap.get(accountNumber).getAccountNumber());
			account.setAacountBalance(accountMap.get(accountNumber).getAacountBalance());
			logger.info("This is info : " + "Printing Deposit Account Details: - START ");
			for (Map.Entry<String, Account> entry : accountMap.entrySet()) {
				logger.info("Account Number : " + entry.getKey() + "Account  Holder : "
						+ entry.getValue().getAccountHolder() + " Account  Balance : "
						+ entry.getValue().getAacountBalance());
			}
		}
		return account;
	}

	@GET
	@Path("accountDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Account accountDetails(@QueryParam("input") String accountNumber) {
		Account account = new Account("accountDetails");
		logger.info("This is info : " + "Printing Account Details: - START ");
		if (accountMap.containsKey(accountNumber)) {
			account = accountMap.get(accountNumber);

		} else {
			account.setAccountResponse("Account Number Not Exist Please create Account");
		}

		logger.info("This is info : " + "Printing  Account Details: - STOP ");

		return account;
	}

	@GET
	@Path("addAccount")
	@Produces(MediaType.APPLICATION_JSON)
	public Account addAccount(@QueryParam("input") String accountHolder) {
		Random rand = new Random();
		Account account = new Account("addAccount");
		Integer accountNumber = rand.nextInt(6300); // (63003639615);

		if (!accountHoler.contains(accountHolder)) {
			accountHoler.add(accountHolder);
			accountMap.put(accountNumber.toString(), createAccount(accountHolder, accountNumber));
			account.setInput(accountHolder);
			account.setAccountNumber(accountNumber);
			account.setAacountBalance(0.0);
			account.setAccountHolder(accountHolder);
			logger.info("This is info : " + "Printing Deposit Account Details: - START ");
			for (Entry<String, Account> entry : accountMap.entrySet()) {
				logger.info("This is info : " + "Account Number : " + entry.getKey() + "Account  Holder : "
						+ entry.getValue().getAccountHolder() + " Account  Balance : "
						+ entry.getValue().getAacountBalance());
			}

			logger.info("This is info : " + "Printing Deposit Account Details: - STOP ");
		}

		return account;
	}

	public Account createAccount(String accountHolder, double accountNumber) {

		Account account = new Account();
		account.setAccountHolder(accountHolder);
		DecimalFormat format = new DecimalFormat("0.#");

		account.setAccountNumber(Double.parseDouble(format.format(accountNumber)));
		account.setAccountType("Saving");
		account.setAacountBalance(0);

		ArrayList<Transactions> transactionHistory = new ArrayList<Transactions>();
		Transactions transaction = new Transactions();
		transactionHistory.add(transaction);
		transaction.setTransactionDate(new Date().toString());
		transaction.setTransactionStatus("SUCCESS");
		transaction.setTransactionType("Account Created");
		;
		account.setTransactionHistory(transactionHistory);

		return account;
	}

}