package org.meghana.creditcardapi.transactions.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.meghana.creditcardapi.transactions.model.Transaction;
import org.springframework.jdbc.core.RowMapper;

public class TransactionMapper implements RowMapper<Transaction>{

	@Override
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Transaction tx= new Transaction(rs.getString("transactionid"),rs.getString("transactiontype"),rs.getDouble("amount"),rs.getString("transactiontime"));
		return tx;
				                        
		
	}
	

	
}
