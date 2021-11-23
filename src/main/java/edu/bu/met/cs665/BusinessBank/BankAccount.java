package edu.bu.met.cs665.BusinessBank;

//import to convert decimal values for equality
import java.math.BigDecimal; 
import java.math.RoundingMode;

/**
 * This abstract class will allow for Business checking and savings class to
 * instantiate from https://rivercitybank.com/resources/fee-schedule-bus/
 * https://jmvidal.cse.sc.edu/csce145/fall06/Ch12/Ch12%20Slides.pdf
 * 
 * @author dnguy
 */
public abstract class BankAccount {
  private int accountNumber; // account number
  private int numWithdrawals = 0; // number of withdrawals
  private double balance; // balance funds
  // Used to check if balance is below 0.
  protected boolean checkBalBelow = false;

  // This constructor will initialize the bank account object
  public BankAccount(int accountNum, double balance) {
    this.accountNumber = accountNum;
    this.balance = balance;
  }

  // obtain the number of withdrawals
  public int getnumofWithdrawals() {
    return numWithdrawals;
  }

  /**
   *  This will deposit the funds.
   */
  public void deposit(double funds) {
    // If the funds are less than 20.00 but not equal to 0, subtract balance
    // source: https://rivercitybank.com/resources/fee-schedule-bus/
    if (funds <= 20.00 && (funds >= 1)) {
      balance += funds;
    } else if (funds <= 0) { // if user inputs 0, print out error statement
      System.out.println("The user deposit 0 balance or negative balance." 
          + " Balance will remain the same.");
    } else {
      // subtract 10 cents if the deposit is greater than 20.00.
      // source: https://rivercitybank.com/resources/fee-schedule-bus/
      balance += funds; // subtract the funds
      balance -= 0.10; // withdraw 10 cents
    }
  }

  /**
   * This will withdraw the funds.
   */
  public void withdraw(double funds) {
    // If the funds is less than balance and the user did not input 0,
    // or a negative number, withdraw funds.
    if ((funds < balance) && (funds >= 1)) {
      balance -= funds;
      if (balance < 0) { // Verify the balance is not 0.
        checkBalBelow = true; // if 0, change the bool to true
      }
      numWithdrawals++; // increase number of withdrawals
    } else if ((new BigDecimal(funds).setScale(2,RoundingMode.UP))
        == (new BigDecimal(balance).setScale(2,RoundingMode.UP))) {
      // If user withdraws exact balance amount, notify the user
      // they will have 0 balance. Big decimal rounds up to 2.
      // https://java2blog.com/format-double-to-2-decimal-places-java/
      System.out.println("The user will have 0 balance.");
      balance = 0; // set balance to 0
      numWithdrawals++; // increase number of withdrawals
    } else if ((funds < balance) 
        && (funds >= 1) 
        && (getnumofWithdrawals() > 6)) {
      // If the user has more than 6 withdrawals, charge transaction fees
      // source: https://rivercitybank.com/resources/fee-schedule-bus/
      balance -= funds; // conduct withdrawals.
      if (balance < 0) { // Verify the balance is not 0.
        checkBalBelow = true; // if 0, change the bool to true
      }
      System.out.println("Transaction fees subtracted because" 
          + " went over 6 transaction fundss..");
      // charge transaction fees for excess withdrawals
      transactionFees();
    } else if (funds <= 0) {
      // print out error statement if the user inputs 0 to withdraw.
      System.out.println("The user input 0 or negative value. Balance will"
          + " remain the same.");
    } else {
      // If the customer withdraws amount greater than balance,
      // charge transaction fees. Subtract funds to show negative balance.
      System.out.println("Overdraft fee of 30.00 will be charged for" 
          + " insufficient balance.");
      balance -= funds; // subtract from funds for negative balance
      if (balance < 0) { // Verify the balance is not 0.
        checkBalBelow = true; // if 0, change the bool to true
      }
      transactionFees();
    }
  }

  /**
   * This is the method to transfer funds.
   */
  public void transfer(double funds, BankAccount toAccount) {
    // If the person transfers money less than the balance, withdraw
    // from the account holder and deposit the funds to the account
    // transferred.
    if ((funds <= balance && (funds >= 1))) { 
      // If account holder has enough funds.
      withdraw(funds); // withdraw funds from account holder
      // deposit to "toAccount" will not contain the 0.10 cents transaction fee.
      toAccount.deposit(funds + 0.10);
    } else if (funds <= 0) {
      // If user inputs 0, print error statement and keep balance same.
      System.out.println("The transfer cannot succeed because" 
          + " 0 or negative amount is input.");
    } else if (funds > balance) {
      // If insufficient funds from account holder, conduct the transfer
      // and charge transaction fees. Assuming the checking and savings
      // accounts are unlinked, the transaction fees will remain 5.00.
      // https://rivercitybank.com/resources/fee-schedule-bus/
      withdraw((funds)); // withdraw from account holder.
      // deposit to "toAccount" will not contain the 0.10 cents 
      //transaction fee.
      toAccount.deposit(funds + 0.10);
      // Print message out.
      System.out.println("Transaction fee of 5 dollars will be subtracted "
          + "for account holder only \nbecause their balance will become"
          + " negative");
    }
  }

  // This is the method to get the account number and return account number
  public int getAccountNumber() {
    return accountNumber;
  }

  // This is the method to check balance for the account holder.
  public double checkBalance() {
    return balance;
  }

  // This method to set the account balance.
  public void setAccountBalance(double newbalance) {
    this.balance = newbalance;
  }

  // This is the method to subtract transaction fees.
  public abstract void transactionFees();

  protected void subtract(double funds) { // subtract fees from balance
    balance -= funds;
  }
}
