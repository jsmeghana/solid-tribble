CREATE TABLE account (
    name varchar(100) NOT NULL,
    dob varchar(100) NOT NULL,
    ssn int NOT NULL,
    id varchar(100) NOT NULL,
    amount double NOT NULL,
    PRIMARY KEY(id)
    
);


CREATE TABLE transaction (
    transactionid varchar(100) NOT NULL,
    transactiontype varchar(20) NOT NULL,
    amount double NOT NULL,
    accountid varchar(100) NOT NULL,
    transactiontime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (accountid) REFERENCES account(id)
    
);

CREATE TABLE userledger (
 transactionid varchar(100) NOT NULL,
 transactiontype varchar(20) NOT NULL,
 amount double NOT NULL,
 accountid varchar(100) NOT NULL,
 FOREIGN KEY (accountid) REFERENCES account(id)
);

CREATE TABLE bankledger(
transactionid varchar(100) NOT NULL,
 transactiontype varchar(20) NOT NULL,
 amount double NOT NULL,
 accountid varchar(100) NOT NULL,
 FOREIGN KEY (accountid) REFERENCES account(id)
);
)