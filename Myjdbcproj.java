package com.endtermpeoject.bankingsystem;
import java.sql.*;
import java.util.Scanner;
class CustomerDAO {
	 Connection con;
	 public void connection(String url, String username, String password) throws SQLException
	 {
		 con = DriverManager.getConnection(url,username,password);
	 }
	 public void showAllRecords() throws SQLException
	 {
		 Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery("Select * From Customer");
	     while(rs.next())
	     {
	         System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "
	        		 +rs.getString(4));
	     }
	 }
	 public void insertRecord(Customer newCustomer) throws SQLException
	 {
		    PreparedStatement st = con.prepareStatement("insert into Customer values (?, ?, ?, ?)");
		    st.setString(1, newCustomer.cust_no);
		    st.setString(2, newCustomer.name);
		    st.setString(3, newCustomer.phoneno);
		    st.setString(4, newCustomer.city);
		    st.executeUpdate();
	 }
	 public void deleteRecord(String customerId) throws SQLException 
	 {
	        PreparedStatement st = con.prepareStatement("DELETE FROM Customer WHERE cust_no = ?");
	        st.setString(1, customerId);
	        int rowsAffected = st.executeUpdate();
	        if (rowsAffected > 0)
	            System.out.println("Customer with ID " + customerId + " has been deleted successfully.");
	        else       
	            System.out.println("No customer found with ID " + customerId);
	 }
	 public void updateName(String id, String name) throws SQLException 
	 {
		    PreparedStatement st = con.prepareStatement("UPDATE Customer SET name = ? WHERE cust_no = ?");
		    st.setString(1, name);
		    st.setString(2, id);
		    int rowsAffected = st.executeUpdate();
		    if (rowsAffected > 0)
		        System.out.println("Customer name updated successfully.");
		    else
		        System.out.println("No customer found with ID " + id);
		}
	public void updateCity(String id, String newCity) throws SQLException 
	{
		PreparedStatement st = con.prepareStatement("UPDATE Customer SET City=? WHERE cust_no=?");
		st.setString(1, newCity);
		st.setString(2,id);
		int rowsAffected = st.executeUpdate();
		if (rowsAffected > 0)
	        System.out.println("Customer name updated successfully");
	    else
	        System.out.println("No customer found with ID " + id);
		
	}
	public void updatePhoneNumber(String id, String phoneNumber) 
	{
		try {
			PreparedStatement st = con.prepareStatement("UPDATE Customer SET phoneno = ? WHERE cust_no = ?");
			st.setString(1, phoneNumber);
		    st.setString(2, id);
		    int rowsAffected = st.executeUpdate();
		    if (rowsAffected > 0)
		        System.out.println("Customer phone number updated successfully.");
		    else
		        System.out.println("No customer found with ID " + id);
		} catch (SQLException e) {
			System.out.println("Wrong Input");
			e.printStackTrace();
		}
	}
	public void showAccountDeatails(String id) throws SQLException
	{
		String query = "SELECT c.*, a.*, b.* " +
		            "FROM Customer c " +
		            "JOIN Depositor d ON c.cust_no = d.cust_no " +
		            "JOIN Account a ON a.account_no = d.account_no " +
		            "JOIN Branch b ON b.branch_code = a.branch_code " +
		            "WHERE c.cust_no = ?";
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		if (rs.next()) 
		{
	        String custNo = rs.getString("cust_no");
	        String name = rs.getString("name");
	        String customerPhone = rs.getString("phoneno");
	        String customerCity = rs.getString("city");
	        String accountNo = rs.getString("account_no");
	        String accountType = rs.getString("type");
	        double balance = rs.getDouble("balance");
	        String branchCode = rs.getString("branch_code");
	        String branchName = rs.getString("branch_name");
	        String branchCity = rs.getString("branch_city");
	        
	        showCustomerDetails(custNo,name,customerPhone,customerCity);
	        System.out.println("Account Number: " + accountNo);
	        System.out.println("Account Type: " + accountType);
	        System.out.println("Balance: " + balance);
	        System.out.println("Branch Code: " + branchCode);
	        System.out.println("Branch Name: " + branchName);
	        System.out.println("Branch City: " + branchCity);
	    } 
		else 
		{
	        System.out.println("No account details found for the customer with number: " + id);
	    }
	}
	public void showLoanDetails(String custNo) throws SQLException {
	    String query = "SELECT c.*, l.*, b.* " +
	            "FROM Customer c " +
	            "JOIN Loan l ON c.cust_no = l.cust_no " +
	            "JOIN Branch b ON b.branch_code = l.branch_code " +
	            "WHERE c.cust_no = ?";
	    
	    PreparedStatement st = con.prepareStatement(query);
	    st.setString(1, custNo);
	    ResultSet rs = st.executeQuery();
	    
	    if (rs.next()) {
	        String loanNo = rs.getString("loan_no");
	        double loanAmount = rs.getDouble("amount");
	        String branchCode = rs.getString("branch_code");
	        String branchName = rs.getString("branch_name");
	        String branchCity = rs.getString("branch_city");
	        
	        String customerNo = rs.getString("cust_no");
	        String customerName = rs.getString("name");
	        String customerPhone = rs.getString("phoneno");
	        String customerCity = rs.getString("city");
	        
	        showCustomerDetails(customerNo,customerName,customerPhone,customerCity);
	        System.out.println("Customer Number: " + custNo);
	        System.out.println("Loan Number: " + loanNo);
	        System.out.println("Loan Amount: " + loanAmount);
	        System.out.println("Branch Code: " + branchCode);
	        System.out.println("Branch Name: " + branchName);
	        System.out.println("Branch City: " + branchCity);
	    } else {
	        System.out.println("No loan details found for the customer with number: " + custNo);
	    }
	}
	public void depositAmount(String accountNo, double depositAmount) throws SQLException {
	    String updateQuery = "UPDATE Account SET balance = balance + ? WHERE account_no = ?";
	    PreparedStatement st = con.prepareStatement(updateQuery);
	    st.setDouble(1, depositAmount);
	    st.setString(2, accountNo);
	    int rowsAffected = st.executeUpdate();
	    
	    if (rowsAffected > 0) {
	        System.out.println("Deposit successful.");
	    } else {
	        System.out.println("Failed to deposit money. No account found with account number: " + accountNo);
	    }
	}

	private void showCustomerDetails(String customerNo, String customerName, String customerPhone, String customerCity)
	{
        System.out.println("Customer Number: " + customerNo);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Customer Phone: " + customerPhone);
        System.out.println("Customer City: " + customerCity);
	}
	public void withdrawAmount(String accountNo, double withdrawAmount) throws SQLException {
	    String balanceQuery = "SELECT balance FROM Account WHERE account_no = ?";
	    PreparedStatement balanceStatement = con.prepareStatement(balanceQuery);
	    balanceStatement.setString(1, accountNo);
	    ResultSet balanceResult = balanceStatement.executeQuery();
	    if (balanceResult.next()) 
	    {
	        double currentBalance = balanceResult.getDouble("balance");
	        if (currentBalance >= withdrawAmount) 
	        {
	            String updateQuery = "UPDATE Account SET balance = balance - ? WHERE account_no = ?";
	            PreparedStatement updateStatement = con.prepareStatement(updateQuery);
	            updateStatement.setDouble(1, withdrawAmount);
	            updateStatement.setString(2, accountNo);
	            int rowsAffected = updateStatement.executeUpdate();

	            if (rowsAffected > 0) 
	            {
	                System.out.println("Withdrawal successful.");
	            } 
	            else 
	            {
	                System.out.println("Failed to withdraw money. Please try again later.");
	            }
	        } else {
	            System.out.println("Insufficient balance. Cannot withdraw the specified amount.");
	        }
	    } 
	    else 
	    {
	        System.out.println("No account found with account number: " + accountNo);
	    }
	}
	 
}
class Customer {
	String cust_no;
	String name;
	String phoneno;
	String city;
	public Customer(String cust_no,String name, String phoneno, String city) {
		this.cust_no=cust_no;
		this.name = name;
		this.phoneno = phoneno;
		this.city = city;
	}
}
public class Myjdbcproj {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:oracle:thin:@172.17.144.110:1521:ora11g";
        // For MySQL
        // String url = "jdbc:mysql://localhost:3306/BankingSystemDB";
        String uname = "root";
        String password = "YOUR_PASSWORD";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        // For MySQL
        // Class.forName("com.mysql.cj.jdbc.Driver");

        CustomerDAO dao = new CustomerDAO();
        dao.connection(url, uname, password);

        System.out.println("***** Banking Management System*****");

        System.out.println("Enter your choice (1-9):\n" +
                "1. Show all records\n" +
                "2. Add a new customer\n" +
                "3. Delete a customer\n" +
                "4. Update a customer\n" +
                "5. Account Details of a customer\n" +
                "6. Loan Details of a Customer\n" +
                "7. Deposit Money\n" +
                "8. Withdraw moeny\n" +
                "9. Exit");

        int choice = sc.nextInt();
        sc.nextLine();
        do 
        {
	        switch (choice) 
	        {
	            case 1:
	                dao.showAllRecords();
	                break;
	            case 2:
	            	System.out.println("Enter Customer Number");
	            	String cust_no = sc.nextLine();
	                System.out.println("Enter Customer Name");
	                String name = sc.nextLine();
	                System.out.println("Enter Phone Number");
	                String phone = sc.nextLine();
	                System.out.println("Enter City");
	                String city = sc.nextLine();
	                Customer newCustomer = new Customer(cust_no,name, phone, city);
	                dao.insertRecord(newCustomer);
	                break;
	            case 3:
	            	 System.out.println("Enter Customer ID to delete:");
	                 String customerId = sc.nextLine();
	                 dao.deleteRecord(customerId);
	                break;
	            case 4:
	                System.out.println("Enter Customer Id: ");
	                String id = sc.nextLine();
	                System.out.println("Enter A: Update name");
	                System.out.println("Enter B: Update city");
	                System.out.println("Enter C: Update Phone number");
	                System.out.println("Enter Your Choice To Update");

	                char uChoice = sc.nextLine().toUpperCase().charAt(0);

	                switch(uChoice) {
	                    case 'A':
	                        System.out.println("Enter new name: ");
	                        String newName = sc.nextLine();
	                        dao.updateName(id, newName);
	                        break;
	                    case 'B':
	                        System.out.println("Enter new city: ");
	                        String newCity = sc.nextLine();
	                        dao.updateCity(id, newCity);
	                        break;
	                    case 'C':
	                        System.out.println("Enter new phone number: ");
	                        String newPhone = sc.nextLine();
	                        dao.updatePhoneNumber(id, newPhone); 
	                        break;
	                    default:
	                        System.out.println("Invalid choice. Please enter A, B, or C.");
	                }
	                break;

	            case 5:
	            	System.out.println("Enter Customer Id");
	            	String cid = sc.nextLine();
	                dao.showAccountDeatails(cid);
	                break;
	            case 6:
	            	System.out.println("Enter Customer Id");
	            	String cuid = sc.nextLine();
	            	dao.showLoanDetails(cuid);
	                break;
	            case 7:
	                System.out.println("Enter Account Number: ");
	                String accountNo = sc.nextLine();
	                System.out.println("Enter Deposit Amount: ");
	                double depositAmount = sc.nextDouble();
	                dao.depositAmount(accountNo, depositAmount);
	                break;
	            case 8:
	                System.out.println("Enter Account Number: ");
	                String withdrawAccountNo = sc.nextLine();
	                System.out.println("Enter Withdrawal Amount: ");
	                double withdrawalAmount = sc.nextDouble();
	                dao.withdrawAmount(withdrawAccountNo, withdrawalAmount);
	                break;
	            case 9:
	                System.out.println("Exiting the program...");
	                System.exit(0);
	                break;
	            default:
	                System.out.println("Invalid choice. Please enter a number between 1 and 9.");
	        }
	    System.out.println("Enter Choice");
        choice = sc.nextInt();
        sc.nextLine();
        } while(choice != 0);
    }	
}
