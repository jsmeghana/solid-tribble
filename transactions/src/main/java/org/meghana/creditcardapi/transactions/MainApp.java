package org.meghana.creditcardapi.transactions;

import org.meghana.creditcardapi.transactions.dao.AccountDaoImpl;
import org.meghana.creditcardapi.transactions.model.Account;
import org.meghana.creditcardapi.transactions.model.Transaction;
import org.meghana.creditcardapi.transactions.model.transcationType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		AccountDaoImpl accountdaoimpl= (AccountDaoImpl)context.getBean("accountDaoImpl");
		
		//-----Account Creation---------
		
		//Account ac = new Account("Meghana", "03/20/1912267456", 1234);
		//accountdaoimpl.create(ac);
		
		
		Transaction t= new Transaction("123403201912267456", transcationType.debit, 3000);
		String TID=accountdaoimpl.purchase(t);
		System.out.println(TID);
		
		
		//accountdaoimpl.create("Jack", "04-20-1990");
	
		
	/*Account account= accountdaoimpl.getAccountdetails("123403201999");
	System.out.println("ID" + account.getId());
	System.out.println("Name" + account.getName());
	System.out.println(("DOB"+ account.getDob()));
	System.out.println(account.getSsn());
	
	*/	

	}

}
