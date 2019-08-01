package com.Utility;

import java.util.HashMap;
import java.util.Map;

import com.reno.Account;
import com.reno.AccountManager;

public class Example1 {
	public static void main(String[] args) {
		
		Map<Double, Account> temp = new HashMap<Double, Account>();
		AccountManager accountManager = new AccountManager();
		temp.put(1365.0, accountManager.createAccount("Sanjay", 1365.0));
	
		System.out.println(temp.get("1365.0").getAccountType());
	}
}