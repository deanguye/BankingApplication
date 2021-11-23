package edu.bu.met.cs665.BusinessBank;

/**
 * This class is responsible for initiating the checking account.
 * Sources:https://rivercitybank.com/resources/fee-schedule-bus/
 * https://jmvidal.cse.sc.edu/csce145/fall06/Ch12/Ch12%20Slides.pdf
 */
public class BusinessChecking extends BankAccount {
  // minimum balance a checking account holder must have.
  private double busCheckMinimumBalance = 500.00;
  // transaction fee is set to 0 by default.
  private double busCheckTransactionFee = 0.00;

  /**
   *  This constructor will initialize the business checking account object.
   */
  public BusinessChecking(int accountNum, double balance) {
    super(accountNum, balance); // call the parent constructor, BankAccount
    /* 
     * This will verify if the balance for account holder is below the minimum
     * balance of 500 dollars.
    */
    checkBalBelow = checkBalance() < busCheckMinimumBalance;
  }

  // this withdraw method will inherit from parent class.
  public void withdraw(double funds) {
    super.withdraw(funds); // call parent method withdraw
  }

  // this method will deduct transaction fees
  @Override
  public void transactionFees() {
    // If the boolean balance below is true subtract transaction fees
    // Sources:https://rivercitybank.com/resources/fee-schedule-bus/
    // https://jmvidal.cse.sc.edu/csce145/fall06/Ch12/Ch12%20Slides.pdf
    if ((checkBalBelow) || (getnumofWithdrawals() > 6)) {
      busCheckTransactionFee = 30.00; // change transaction fees to 30 dollars.
      // subtract transaction fees.
      super.subtract(busCheckTransactionFee);
      // verify balance is equal to checkbalance
      checkBalBelow = checkBalance() < busCheckMinimumBalance;
    }
  }
}
