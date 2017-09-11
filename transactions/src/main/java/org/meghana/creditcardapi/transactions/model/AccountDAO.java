package org.meghana.creditcardapi.transactions.model;

import java.util.List;

import javax.sql.DataSource;

public interface AccountDAO {
	public void setDataSource(DataSource ds);

	public List<Transaction> getTransaction(String id);

	public String create(Account ac);

	public Account getAccountdetails(String id);

}
