package edu.bu.met.cs665.BusinessBank;

import static java.lang.System.in;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is responsible for holding and creating the customer/ bank account
 * information. Source;
 * https://jmvidal.cse.sc.edu/csce145/fall06/Ch12/Ch12%20Slides.pdf
 */
public class Bank {
  // This is the account database to hold all account information..
  private ArrayList<BankAccount> customerAccounts = new ArrayList<BankAccount>();
  // This is the customer database to hold all customer information.
  protected ArrayList<Customer> customers = new ArrayList<Customer>();
  private int numAccounts = 0; // this will be used as a counter to count all account numbers.

  /**
   * This bank constructor will read all the files containing customer information
   * and bank account information. For the customers.txt file, the customer first
   * name, last name, account number information, phone, and address will be
   * obtained and used to build a customer object. The builder method will be
   * implemented.
   * 
   * <p>The input2.txt file will read the customer account type, credit score,
   * account number, and balance to create customer accounts.
   */
  public Bank() {
    // Display message of reading the files.
    System.out.println("Reading the files and initiating customer bank accounts.");
    try {
      // This will read the account number files.
      Scanner accountFile = new Scanner(new File("resource\\input2.txt"),"UTF-8");
      // call the create account method to create customer accounts.
      createAccount(accountFile);

      // This will read the customer files.
      Scanner custFile = new Scanner(new File("resource\\customers.txt"),"UTF-8");
      // Call the customer builder
      buildCustomer(custFile);
    } catch (IOException ex) {
      // Print message if files have not been read.
      System.out.println("This error is displayed because the file does not exist.");
    }
    // Print message files are read
    System.out.println("The file has been processed.");
  }

  /**
   * This method is private to build the customers. The reason for this is to
   * protect customer information for businesses.
   **/
  private void buildCustomer(Scanner in) {
    in.nextLine(); // Skip the first column
    while (in.hasNextLine()) { // obtain customer information for each line.
      // obtain String of information to be split.
      String firstName;
      String lastName;
      String custPhone;
      String custAdd;
      int acctNumber;

      // Initialize string to be null and account to be 0 to split.
      firstName = lastName = custPhone = custAdd = null;
      acctNumber = 0;

      // Read in string text and split its values based on ","delimeter
      String inputText = in.nextLine();
      String[] arrOfStr = inputText.split(",", 0);

      // Get required fields such as first name, last name and account number.
      firstName = arrOfStr[0];
      lastName = arrOfStr[1];
      acctNumber = Integer.parseInt(arrOfStr[2]);

      // check Phone number is present in string.
      if (arrOfStr.length > 3) {
        custPhone = arrOfStr[3];
      }
      if (arrOfStr.length > 4) { // Check customer address
        custAdd = arrOfStr[4];
      }

      // Use builder pattern here to build customer accounts.
      Customer customer = new Customer.CustomerBuilder(firstName, lastName)
          .accountNum(acctNumber).phoneNum(custPhone)
          .address(custAdd).build();
      // add customer to database
      customers.add(customer);
    }
  }

  /**
   * This code is responsible for create the bank accounts. It will take a file
   * input and read the account type, credit score, account number, and balance.
   * It will run a credit check and type check to determine if the account is
   * eligible for creation. If so, the account is eligible The factory pattern is
   * applied here.
   * 
   * <p>source: https://www.fundera.com/business-loans/guides/
   * credit-score-for-business-loan
   */
  private void createAccount(Scanner in) {
    in.nextLine(); // Skip the first column
    while (in.hasNextLine()) { // read each file line by line
      // Scan for the account information in each line.
      String accountType = in.next(); // checking or savings type
      int creditScore = in.nextInt(); // credit score
      int accountNum = in.nextInt(); // account num
      double balance = in.nextDouble(); // initial balance in file
      BankAccount account = null; // container to store in database

      /*
       * Create a bank account based on the type provided and if it meets the credit
       * score criteria. The minimum credit score must be 680
       * (https://www.fundera.com/business-loans
       * /guides/credit-score-for-business-loan) Per conversation with my bank on
       * 7/16/2021, the minimum is 680.
       */
      if (accountType.contains("BusinessChecking") // If business checking type
          && (creditScore >= 680)) { // If credit score is 680 or above
        // create the business checking account and add to accounts database
        addAccount(new BusinessChecking(accountNum, balance));
      } else if (accountType.contains("BusinessSavings") && (creditScore >= 680)) {
        // If business savings type and credit score is 680 or higher,
        // create the business savings account and add to database
        addAccount(new BusinessSavings(accountNum, balance));
      } else {
        // Print out a statement stating which account number
        // is not eligible and cannot be created.
        System.out.println("Account number: " + accountNum + " is ineligible to be created.");
      }
      try {
        // Check if there is information at the end of the file.
        in.nextLine();
      } catch (NoSuchElementException exception) {
        break; // if no lines, finish the code.
      }
    }
  }

  /** Deposit method called by bank class based on account number and funds
   *  source: https://jmvidal.cse.sc.edu/csce145/fall06/Ch12/Ch12%20Slides.pdf
   */
  public void deposit(int accountNum, double funds) {
    // if an account can be found using "getaccount" based on the account number
    if (getAccount(accountNum) != null) {
      // deposit the funds into that account. This is based on polymorphism.
      getAccount(accountNum).deposit(funds);
    } else {
      // Print out message saying the account number cannot be deposited
      System.out.println("Cannot" + "deposit for Account number " + accountNum);
    }
  }

  /**withdraw method called by bank class based on account number and funds
   * source: https://jmvidal.cse.sc.edu/csce145/fall06/Ch12/Ch12%20Slides.pdf
   */
  public void withdraw(int accountNum, double funds) {
    // if an account can be found using "getaccount" based on the account number
    if (getAccount(accountNum) != null) {
      // withdraw funds from that account. This is based on polymorphism.
      getAccount(accountNum).withdraw(funds);
    } else {
      // Print out message saying the account number cannot be deposited
      System.out.println("Cannot" + "withdraw for Account number " + accountNum);
    }
  }

  /**
   * Transfer method called by bank class based on account number and funds
   * source: https://jmvidal.cse.sc.edu/csce145/fall06/Ch12/Ch12%20Slides.pdf.
   */
  public void transfer(int fromAccount, int toAccount, double funds) {
    // if the account conducting transfer cannot be found using "getaccount"
    // based on the account number
    if (getAccount(fromAccount) == null) {
      // do not initialize transfer and print out message.
      System.out.println("Cannot find 'From' account number " + fromAccount);
    } else if (getAccount(toAccount) == null) { 
      /*
       *  if the account receiving transfer cannot be found using "getaccount" 
       *  based on the account number, do not initialize transfer and print out
       *  the statement below.      
       */
      System.out.println("Cannot find 'To' account number " + toAccount);
    } else { // if both accounts are found, transfer the funds from the "fromAccount"
      // to the "to account."
      getAccount(fromAccount).transfer(funds, (getAccount(toAccount)));
    }
  }

  /*
   *  Method to add account to the database.
   *  Source: https://jmvidal.cse.sc.edu/
   *  csce145/fall06/Ch12/Ch12%20Slides.pdf
   */
  private void addAccount(BankAccount account) {
    customerAccounts.add(account); // add account to database
    numAccounts++; // increase the counter by 1 to retrieve in for loop
  }

  /**
   * This method is to retrieve the account based on account number.
   */
  public BankAccount getAccount(int accountNum) {
    BankAccount account = null; // container to retrieve the account
    // for the index in the account database size
    for (int i = 0; i < customerAccounts.size(); i++) {
      // obtain the bank account from database
      BankAccount tempAcc = customerAccounts.get(i);
      // get the account number from the tempAcc bank account.
      int myacct = tempAcc.getAccountNumber();

      /* If the input account number matches with the account number
       * retrieved from myacct, set the account container to that account.
       */
      if (myacct == accountNum) {
        // return account
        account = customerAccounts.get(i);
        break; // end the for loop
      }
    }
    return account; // return the matching account.
  }

  /**
   * This method is to retrieve the customer based on account number.
   */
  public Customer getCustomer(int accountNum) {
    Customer customer = null; // container to retrieve the customer
    // for the index in the customer database size
    for (int i = 0; i < customers.size(); i++) {
      Customer tempCustomer = customers.get(i); // obtain the customer

      /*
       *  If the customer account number matches with the account number input
       *  set the container to customer and return that customer.
       */
      if (tempCustomer.getAccountNum() == accountNum) {
        customer = tempCustomer; // set customer container
      }
    }
    return customer; // return customer.
  }
}
