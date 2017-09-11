package org.meghana.creditcardapi.transactions.model;

import java.util.List;

public class AccountInfo {

	public String accountid;
	public double principalAmount;
	public List<Transaction> transactions;

	public String getId() {
		return accountid;
	}

	public double getPrincipalAmount() {
		return principalAmount;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public AccountInfo(String accountid) {
		super();
		this.accountid = accountid;

	}

	public AccountInfo(String accountid, double principalAmount, List<Transaction> transactions) {
		super();
		this.accountid = accountid;
		this.principalAmount = principalAmount;
		this.transactions = transactions;
	}

	public String getAccountid() {
		return accountid;
	}

}