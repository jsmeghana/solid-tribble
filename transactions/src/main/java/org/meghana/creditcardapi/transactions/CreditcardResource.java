package org.meghana.creditcardapi.transactions;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.meghana.creditcardapi.transactions.dao.AccountDaoImpl;
import org.meghana.creditcardapi.transactions.model.Account;
import org.meghana.creditcardapi.transactions.model.AccountInfo;
import org.meghana.creditcardapi.transactions.model.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
		return gson.toJson("200 OK");
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAccountdetails(@PathParam("id") String id){
		System.out.println(id);
	List<Transaction> tx= this.getObject().getTransaction(id);
	double accountBalance=0;
	for (Transaction t: tx){
		String transactionType = t.getTransactiontype();
		System.out.println(transactionType);
		if (transactionType.equals("debit")){
			accountBalance=accountBalance+t.getAmount();
		}
	}
	AccountInfo ac = new AccountInfo(id, accountBalance, tx);
		return gson.toJson(ac);
		}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String createAccount(@QueryParam("name") String name,
			@QueryParam("dob") String dob,
			@QueryParam("ssn") int ssn){
		     Account ac = new Account(name, dob, ssn);		      
		    String Id= this.getObject().create(ac);
		    AccountInfo accountinfo= new AccountInfo(Id);
			
		return gson.toJson(accountinfo);
		
	}
	
	@POST
	@Path("/transactions")
	@Produces(MediaType.APPLICATION_JSON)
	public String createPurchase(@QueryParam("id") String id,
			@QueryParam("transactiontype") String transactiontype,
			@QueryParam("amount") double amount){
		Transaction transaction= new Transaction(id,transactiontype,amount);
		String Id= this.getObject().purchase(transaction);
		return gson.toJson(Id);
	}
	
	
}


