# CreditCard API - Spring Rest Jdbc/MySQL
This is a fictional credit card API(REST web Service) built using Java/Maven/Spring Jdbc/MySQL that simulates money transaction events. 
It provides a complete built-in health check, metrics and much more.

## How to Run
This application is packaged as a war and it runs on Tomcat. Tomcat v7.0 installation is necessary.
1. Clone this repository
2. Make sure you are using JDK 1.8 and Maven 4.x
3. You can build the project by running `mvn clean install`
4. Once it is successfully built, you can right click on the project "transactions" ‒> run on server‒>Select Tomact V7.0 and add the transaction project‒>finish
5. Check the console in the IDE to see if the project was successfully deployed. If it was successful you should see something like this
```
Sep 04, 2017 1:37:14 PM org.apache.catalina.startup.Catalina start
INFO: Server startup in 17118 ms
```
### Install Database
1. Install MySQL version 5.6.37 Database from this link--> https://dev.mysql.com/downloads/installer/
2. Once installed, the MySQL installer directs you to create a username and password. 
3. You can verify if the connection was successfull by clicking on the connect button in the installer.
4. You could open the MYSQL 5.6 command client and connect to the Database or use the workbench provided by MYSQL.
5. Use SQL query 'CREATE DATABASE databaseName' to create a database.
6. Type 'USE databasename' to begin using the database
7. The project has a `Table.sql` file at path `solid-tribble\transactions\Table.sql` that contains all the queries required to create tables in this project. You can copy the queries in the database to create the tables.
8. As this project uses Spring, make sure that the user name,password and the database name that you created is updated in the `spring.xml` path `solid-tribble\transactions\src\main\java\spring.xml` file as shown below.
The database name can be changed right after the localhost:3360/databasename
```
<bean id="dataSource" 
      class = "org.springframework.jdbc.datasource.DriverManagerDataSource">
      <property name = "driverClassName" value = "com.mysql.jdbc.Driver"/>
      <property name = "url" value = "jdbc:mysql://localhost:3306/creditcard"/>
      <property name = "username" value = "root"/>
      <property name = "password" value = "root"/>
   </bean>
   ```
   9.  Once this is done, the application should successfully connect to the database.
   10. The project has 4 tables **account, transaction, userledger and bankledger** tables.
   
   ## About the Service
   The service is a simple creditcard api to track purchases for customers and is capable of tracking account balances via double-entry
bookkeeping and uses the accounting logic here and provide all the rules for ledger distributions. It sends several transactions simulating a credit card
to the server and then be able to query the server for lists of transactions or account balances, while
double-entry bookkeeping assures that all our calculations are balanced.

When queried to the server, it uses the MySQL database to persist the Account users details and transactions and returns the outstanding balance for the account along with the list of transactions performed that is ordered by the timestamp.

If your database connection properties work, you can call some REST endpoints on your localhost **8080**; 
More interestingly, you can start calling some of the operational endpoints (see full list below) like /accounts and /health.

Here is what this little application demonstrates:

1. Full integration with the latest Spring Framework: inversion of control, dependency injection, etc.
2. Demonstrates operations such as healthcheck,Account Creation, Create Transactions,Get outstanding balance based on the account id It   also adds the transaction based on "Credit" and "Debit" transaction types.etc. endpoints automatically on a configured port.
3. RESTful service using annotation: supports JSON response.
4. Spring Data Integration with JDBCTemplate with just a few lines of configuration and familiar annotations.
5. Automatic CRUD functionality against the data source using Spring JDBC DAO pattern.
6. The code can also accomodate future extensions for transactions such as Payments, billing etc..

Here are the endpoints you can call:
## To get health of the server, Create Accounts, Create transactions and retrive transactions and outstanding balance for an account
```
http://localhost:8080/transactions/webapi/accounts/health
http://localhost:8080/transactions/webapi/accounts
http://localhost:8080/transactions/webapi/accounts/transactions
http://localhost:8080/transactions/webapi/accounts/{id}

```
## Get Health Status
```
GET /transactions/webapi/accounts/health
Content-Type: application/json
Response
{
"200 OK"
}
RESPONSE CODE: HTTP 200 OK
```
## Account Creation
## Create New Account and intitialize all the rows in the Transactions and User ledger and Bank ledger tables. 
1. When this operation is called, it creates a new account and initializes the Transaction(journal) and ledgers required for an account
 and returns the account id for further usage.
 2. It also assigns a credit limit for the user in the 'amount' column of the account table.
```
POST /transactions/webapi/accounts
Content-Type: application/json
Accepts: Query Parameters (It requires 3 parameters to create an account)
Example:
name=sbli
dob =04/12/2012
ssn=123474
eg Request URL: http://localhost:8080/transactions/webapi/accounts?name=sblig&dob=04/12/2012&ssn=123474

Response : Returns the Account Id from the database table account back to the user for future use. This account Id is unique for each person and I have created it by combining the DOB and SSN. It also indicates that the outstanding balance is 0 as your account is new.

In addition, the operation also initializes the Transaction table, User ledger table and Bank ledger tables.All these objects are called in the `solid-tribble\transactions\src\main\java\org\meghana\creditcardapi\transactions\dao\AccountDaoImpl.java` and intitalized.
At this stage the Transaction type will be stored as "Account Creation". 

Response Body
{
    "accountid": "123470818200333323",
    "principalAmount": 0
}

RESPONSE CODE: HTTP 200 OK

```
# Create Purchase Transactions for an account
When this operation is called ,it simulates a creditcard purchase transaction to the server.
1. It applies an incoming transaction
2. It records the transaction in the correct Transaction table (Journal)
3. It applies the given rule to allocate the transaction in the correct ledgers (Bankledger and Userledger).
4. userledger table will have only **"debit"** entries.
5. bankledger table will have only **"credit"** entries (also called principal amount)

Added Enhancements to this operation
It also stores the remaining balance on the credit limit available for that account in the Account table.
```
POST /transactions/webapi/accounts/transactions
Content-Type: application/json
Request:
Accepts(Account Id, Transaction Type(eg debit, credit, account creation) and amount)
Query Parameters: 
id=123418121962222253342
transactiontype=debit
amount=300
Request URL : http://localhost:8080/transactions/webapi/accounts/transactions?id=123418121962222253342&transactiontype=debit&amount=300

Response
Returns the transaction id that is unique for each transaction made for that account. 
{
12341812196222225334220170904173649
}
RESPONSE CODE: HTTP 200 OK

```
## Get Account Id, outstanding balance and list of transactions for an account ordered by the timestamp
1. When this operation is called, it returns an existing account
2. returns the outstanding principal for an account (being the sum of all the credit entries in fetches all transactions for an account    and returns them in a list ordered by time
```
GET /transactions/webapi/accounts/{id}
Content-Type: application/json
Path Parameter = {id} this is the account id
Request URL: http://localhost:8080/transactions/webapi/accounts/123418121962222253342

Retrieves the transaction details and outstanding principal amount for this account number 123418121962222253342. The transactions are ordered by the timestamp as well;

Response
{
    "accountid": "123418121962222253342",
    "principalAmount": 7800,
    "transactions": [
        {
            "id": "12341812196222225334220170904173649",
            "transactiontype": "debit",
            "timestamp": "2017-09-04 17:36:49.0",
            "amount": 300
        },
        {
            "id": "12341812196222225334220170904123823",
            "transactiontype": "debit",
            "timestamp": "2017-09-04 12:38:23.0",
            "amount": 300
        },
        {
            "id": "12341812196222225334220170904123801",
            "transactiontype": "debit",
            "timestamp": "2017-09-04 12:38:01.0",
            "amount": 300
        }

}

HTTP RESPONSE CODE: HTTP 200 OK

```
# About Spring JDBC and JDBCTemplate
Spring JdbcTemplate is a powerful mechanism to connect to the database and execute SQL queries. It internally uses JDBC api, but eliminates a lot of problems of JDBC API.

## Problems of JDBC API

The problems of JDBC API are as follows:

1. We need to write a lot of code before and after executing the query, such as creating connection, statement, closing resultset, connection etc.
2. We need to perform exception handling code on the database logic.
3. We need to handle transaction.
4. Repetition of all these codes from one to another database logic is a time consuming task.

## Advantages
Spring JdbcTemplate eliminates all the above mentioned problems of JDBC API. It provides you methods to write the queries directly, so it saves a lot of work and time.

## Questions or comments: jsmeghana@gmail.com
