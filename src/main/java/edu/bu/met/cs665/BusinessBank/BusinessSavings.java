package edu.bu.met.cs665.BusinessBank;

/**
 * This class is responsible for initiating the savings account.
 * Sources:https://rivercitybank.com/resources/fee-schedule-bus/
 * https://jmvidal.cse.sc.edu/csce145/fall06/Ch12/Ch12%20Slides.pdf
 */
public class BusinessSavings extends BankAccount {
  // minimum balance a checking account holder must have.
  private double busSavingsMinimumBalance = 500.00;
  // transaction fee is set to 0 by default.
  private double busSavingsTransactionFee = 0.00;

  /**
   * This constructor will initialize the business savings account object.
   */
  public BusinessSavings(int accountNum, double balance) {
    super(accountNum, balance);// call the parent constructor, BankAccount
  }

  /**
   * This withdraw method will inherit from parent class.
   */
  public void withdraw(double amount) {
    super.withdraw(amount); // call parent method withdraw
    // check if balance is below minimum balance
    if (checkBalance() < busSavingsMinimumBalance) {
      checkBalBelow = true; // change the boolean to true
    }
  }

  // this method will deduct transaction fees
  @Override
  public void transactionFees() {
    // If the boolean balance below is true subtract transaction fees
    // Sources:https://rivercitybank.com/resources/fee-schedule-bus/
    // https://jmvidal.cse.sc.edu/csce145/fall06/Ch12/Ch12%20Slides.pdf
    if ((checkBalBelow) || (getnumofWithdrawals() > 6)) {
      busSavingsTransactionFee = 30.00; // change transaction fees to 30 dollars.
      // subtract transaction fees.
      super.subtract(busSavingsTransactionFee);
      // verify balance is equal to checkbalance
      checkBalBelow = checkBalance() < busSavingsMinimumBalance;
    }
  }
}
