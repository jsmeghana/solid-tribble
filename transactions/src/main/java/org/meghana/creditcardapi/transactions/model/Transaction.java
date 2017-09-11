package org.meghana.creditcardapi.transactions.model;

public class Transaction {

	private String id;
	private String transactionid;
	private String transactiontype;

	public Transaction() {

	}

	public Transaction(String id, String transactiontype, double amount) {
		super();
		this.id = id;
		this.transactiontype = transactiontype;
		this.amount = amount;

	}

	private String time;
	private String timestamp;
	private double amount;

	public Transaction(String id, String transactiontype, double amount, String timestamp) {
		super();
		this.id = id;
		this.transactiontype = transactiontype;
		this.amount = amount;
		this.timestamp = timestamp;

	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

}
