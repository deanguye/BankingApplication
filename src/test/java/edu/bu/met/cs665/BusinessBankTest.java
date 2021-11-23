package edu.bu.met.cs665;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.bu.met.cs665.BusinessBank.Bank;
import edu.bu.met.cs665.BusinessBank.BankAccount;
import edu.bu.met.cs665.BusinessBank.BusinessChecking;
import edu.bu.met.cs665.BusinessBank.BusinessSavings;
import edu.bu.met.cs665.BusinessBank.Customer;


public class BusinessBankTest {
  /*
   * All these global variables will be 
   * run before the test cases. A 
   * Bank interface, an initialized
   * bank account (account 1) and a
   * transfer account(acocunt2) will
   * be declared.
  */
  BankAccount account1 = null; // Use for basic banking transactions
  BankAccount account2 = null; // Use for external transfer
  Customer customer1; // Information for the customer conducting transaction
  Customer customer2; // Information for customer receiving transfer
  Bank bank = new Bank(); // Initializing bank object
  
  /**
   * This will initialize the account and customer information.
   */
  @Before
  public void runBeforeTest() {    
    // Use the "getAccount" method with account number as parameter
    account1 = bank.getAccount(1000); // account number 1000
    account2 = bank.getAccount(1001); // account number 1001
    
    // Use "getCustomer" method with account number as parameter
    // customer tied to account number 1000
    customer1 = bank.getCustomer(1000); 
    // customer tied to account number 1001
    customer2 = bank.getCustomer(1001);
  }     
  
  // This test will check for balance for account1 and account2.
  @Test
  public void testBalance() {
    //The expected output is 1500.00.
	// 0.01 rounds the balance to two digits.
    assertEquals(account1.checkBalance(),1500.00,0.01); 
    // The expected output is 300 dollars.
    assertEquals(account2.checkBalance(),300.00,0.01); 
  }
  
  //This will test for the deposit method for account1 only.
  @Test
  public void testDeposit() {
    /*
	 *The expected input deposit is 0 dollars. The expected output 
	 */
    //The expected output is balance will remain the same at 1500.00.
	System.out.println("Test deposits for account holder 1000");
    account1.deposit(0.00);
    assertEquals(account1.checkBalance(),1500.00,0.01);
    
    // The expected input deposit is 21.00 . The expected output is 1520.90. 
    // This will verify the correct deposit and service fee is charged.
    account1.deposit(21.00);
    assertEquals(account1.checkBalance(),1520.90,0.01);
    
    // The expected input deposit is 1500.00. The expected output is 3020.80. 
    // This will verify the correct amount is deposit.
    account1.deposit(1500.00);
    assertEquals(account1.checkBalance(),3020.80,0.01);
    
    /*
	 *This tests the deposits for account holder 1001.
	 */
    // The expected input deposit is 0 dollars. The expected output is 300.000
    System.out.println("Test deposits for account holder 1001");
    account2.deposit(0.00);
    assertEquals(account2.checkBalance(),300.00,0.01);
    
    // The expected input deposit is 21.00 . The expected output is 1520.90. 
    // This will verify the correct deposit and service fee is charged.
    account2.deposit(21.00);
    assertEquals(account2.checkBalance(),320.90,0.01);
    
    // The expected input deposit is 1500.00. The expected output is 3020.80. 
    // This will verify the correct amount is deposit.
    account2.setAccountBalance(300.00); // reset to 1500 for testing
    account2.deposit(1500.00);
    assertEquals(account2.checkBalance(),1799.9 ,0.01);
 }
  
  //Test for withdrawal method for account1 only.
  @Test
  public void testWithdraw() {
    System.out.println("\nThese three tests will verify"
        + " that the correct amount is withdrawn.\nThere will"
        + " be a withdrawn of 0 dollars, 21 dollars, and"
        + " 1500 dollars.\n ");
    //The expected input withdrawn is 0 dollars. The expected output 
    //is the balance will remain the same at 1500.00.
    account1.withdraw(0.00);
    assertEquals(account1.checkBalance(),1500.00,0.01);
    
    // The expected input withdrawn is 1. The expected output is 1499.0.
    // This will verify the correct amount is withdrawn
    account1.withdraw(1.00);
    assertEquals(account1.checkBalance(),1499.00,0.01);
    
    //The initial input is 1501 dollars. The expected output is -31.00
    // due to transaction fees.
    account1.setAccountBalance(1500.00); // reset to 1500 for testing
    account1.withdraw(1501.00);
    assertEquals(account1.checkBalance(),-31.00,0.01);
    
    /*
   	 *This tests the withdrawals for account holder 1001.
   	 */
    // The expected input withdraw is 0 dollars. The expected output is 300.000
    System.out.println("Test deposits for account holder 1001");
    account2.withdraw(0.00);
    assertEquals(account2.checkBalance(),300.00,0.01);
       
    // The expected input withdraw is 21.00 . The expected output is 1520.90. 
    // This will verify the correct withdraw and service fee is charged.
    account2.withdraw(21.00);
    assertEquals(account2.checkBalance(),279.00,0.01);
       
    // The expected input withdraw is 1500.00. The expected output is -1230.00
    // This will verify the correct amount is withdraw.
    account2.setAccountBalance(300.00); // reset to 1500 for testing
    account2.withdraw(1500.00);
    assertEquals(account2.checkBalance(),-1230.00,0.01);
 }
  //Test for transfer between account1 and account2.
  @Test
  public void testTransfer() {
    System.out.println("These three tests will verify"
        + " that the transfer is successful. There will"
        + " be a transfer of 0 dollars, 21 dollars, and"
        + " 1500 dollars to account number 1001.");
    
    //The expected input is transfer 0 dollars to account holder 2.
    //The expected output is an error message and the balance remaining 
    //the same for account holder 1 of 1500 dollars.
    account1.transfer(0.00, account2);
    assertEquals(account1.checkBalance(),1500.00,0.01);
    
    //The expected input is transfer 21 dollars to account holder 2.
    //The expected output is for account holder 1 to have 1479 as 
    //the remaining balance.
    account1.transfer(21.00, account2);
    assertEquals(account1.checkBalance(),1479.00,0.01);
    
    //The expected input is transfer 1500 dollars to account holder 2.
    // The expected output is an error message and 0 balance.
    // All balances were reset "setAccountBalances" for testing purposes.
    account1.setAccountBalance(1500); 
    account2.setAccountBalance(300.00);
    //transfer account1 balance amount to account 2
    account1.transfer(1500.00, account2); 
    // assert that for account1, the new balance is 0.
    assertEquals(account1.checkBalance(),0,0.01);
    // assert that for account2, the new balance is 1800.00
    // this is because the deposits are greater than 20.00.
    assertEquals(account2.checkBalance(),1800.00,0.01);
  }
  
  //Test for get customer method.
  @Test
  public void testCustomer() {
    //Expected input is customer 1 and method "getFirstname" is called.
    // Expected output is "Deanna". The "getLastname"  for customer 1 
	// is Nguyen.
	assertEquals(customer1.getFirst(),"Deanna");
	assertEquals(customer1.getLast(),"Nguyen");
	
	//Expected input is customer 2 and method "getFirstname"
    // is called. Expected output is "Kevin" for first name and "Le"
	// for last name.
	assertEquals(customer2.getFirst(),"Kevin");
	assertEquals(customer2.getLast(),"Le");
  }
 }
  



