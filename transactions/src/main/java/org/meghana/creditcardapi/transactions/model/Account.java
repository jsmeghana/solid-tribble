package org.meghana.creditcardapi.transactions.model;

public class Account {

	private String name;
	private String id;
	private String dob;
	private int ssn;
	private double amount;

	public Account(String name, String dob, int ssn) {
		this.name = name;
		this.dob = dob;
		this.ssn = ssn;
	}

	public Account(String name, String dob, int ssn, String id) {
		this.name = name;
		this.dob = dob;
		this.ssn = ssn;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public double getAmount() {
		return 2000d;
	}

	public int getSsn() {
		return ssn;
	}

	public String getId() {
		return id;
	}

	public String getDob() {
		return dob;
	}

}
