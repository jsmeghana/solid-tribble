package org.meghana.creditcardapi.transactions;



import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.meghana.creditcardapi.transactions.dao.AccountDaoImpl;
import org.meghana.creditcardapi.transactions.model.Account;
import org.meghana.creditcardapi.transactions.model.Transaction;
import org.meghana.creditcardapi.transactions.model.transcationType;
import org.springframework.cglib.core.GeneratorStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.ServletContextAttributeExporter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/accounts")
public class CreditcardResource {
	
	
	
	public AccountDaoImpl getObject(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		AccountDaoImpl accountdaoimpl= (AccountDaoImpl)context.getBean("accountDaoImpl");
		return accountdaoimpl;
	}
	
	
	Gson gson = new GsonBuilder().create();
	@GET
	@Path("/health")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHealthstatus(){
	List<Transaction> tx= this.getObject().getTransaction("123418121962222253342");
	double accountBalance=0;
	for (Transaction t: tx){
		//System.out.println(t.getTransactiontype());
		String transactionType = t.getTransactiontype();
		System.out.println(transactionType);
		if (transactionType.equals("debit")){
			System.out.println("hello");
			accountBalance=accountBalance+t.getAmount();
			
		}
	}
	List<String> a = new ArrayList<>();
	String accountbalance = "Account::::::"+accountBalance;

	a.add(accountbalance);
	List b = new ArrayList<>();
	b.add(a);
	b.add(tx);
	
		return gson.toJson(b);
		}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String createAccount(@QueryParam("name") String name,
			@QueryParam("dob") String dob,
			@QueryParam("ssn") int ssn){
		     Account ac = new Account(name, dob, ssn);		      
		    String Id= this.getObject().create(ac);
		    //System.out.println(Id);
		return gson.toJson(Id);
		
	}
	
	@POST
	@Path("/transactions")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createTransactions(){
		return gson.toJson("Post works");
	}
	
	
}

