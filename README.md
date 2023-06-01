# BankingManagementSystem

The Banking Management System is a Java-based application that allows users to interact with a backend Oracle database using JDBC connectivity. The program provides a menu-driven interface for performing various banking operations such as managing customer records, account details, and transactions.

## Requirements

- Completion of IDB Laboratory Assignment-4
- Basic Java Programming knowledge
- Oracle database
- JDBC driver

## Setup

1. Ensure that the Oracle JDBC driver is available in the project's classpath.
2. Update the database connection URL, user ID, and password in the program (`app.java`) to match your Oracle database configuration.

## Features

1. Show Customer Records: Display details of all customers in a specific format.
2. Add Customer Record: Add a new customer to the database by providing their information (customer number, name, phone number, and city). The details of all customers will be displayed after the addition.
3. Delete Customer Record: Delete a customer from the database by providing their customer number. The details of all customers will be displayed after the deletion.
4. Update Customer Information: Update information for a specific customer by providing their customer number and selecting the attribute to update (name, phone number, or city). The details of all customers will be displayed after the update.
5. Show Account Details of a Customer: Display account details (account number, type, balance, branch code, branch name, and branch city) for a specific customer by providing their customer number.
6. Show Loan Details of a Customer: Display loan details (loan number, loan amount, branch code, branch name, and branch city) for a specific customer by providing their customer number.
7. Deposit Money to an Account: Deposit a specified amount to a customer's account by providing the account number and the deposit amount. The updated balance will be verified, and the account details will be displayed.
8. Withdraw Money from an Account: Withdraw a specified amount from a customer's account by providing the account number and the withdrawal amount. The updated balance will be verified, and the account details will be displayed.
9. Exit the Program: Terminate the program.

## Usage

1. Compile the `app.java` file.
2. Run the compiled file.
3. Choose an option from the displayed menu by entering the corresponding number.
4. Follow the on-screen prompts to provide necessary inputs for each operation.
5. The program will perform the selected operation on the Oracle database and display the results or appropriate error messages.
