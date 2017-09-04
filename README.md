# CreditCard API - Spring Rest Jdbc/MySQL
This is a fictional credit card API(REST web Service) built using Java/Maven/Spring Jdbc/MySQL that simulates money transaction events. 
It provides a complete built-in health check, metrics and much more.

## How to Run
This application is packaged as a war and it runs on Tomcat. Tomcat v7.0 installation is necessary.
1. Clone this repository
2. Make sure you are using JDK 1.7 and Maven 4.x
3. You can build the project and run the tests by running `mvn clean package`
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
7. The project has a `Table.sql` file at path `solid-tribble\transactions\Table.sql` that contains all the queries required to create tables in this project.
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
   9. Once this is done, the application should successfully connect to the database.
   
   ## About the Service
   The service is a simple creditcard api to track purchases for customers and is capable of tracking account balances via double-entry
bookkeeping and uses the accounting logic here and provide all the rules for ledger distributions. It sends several transactions simulating a credit card
to the server and then be able to query the server for lists of transactions or account balances, while
double-entry bookkeeping assures that all our calculations are balanced.

When queried to the server, it uses the MySQL database to persist the Account users details and transactions and returns the outstanding balance for the account along 
with the list of transactions performed that is ordered by the timestamp.



