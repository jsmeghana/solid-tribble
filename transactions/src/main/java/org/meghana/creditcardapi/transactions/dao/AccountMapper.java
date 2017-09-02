package org.meghana.creditcardapi.transactions.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.meghana.creditcardapi.transactions.model.Account;
import org.springframework.jdbc.core.RowMapper;

public class AccountMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account = new Account(rs.getString("name"), rs.getString("dob"), rs.getInt("ssn"), rs.getString("id"));
		return account;
	}

}

