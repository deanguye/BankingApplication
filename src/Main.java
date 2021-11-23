package edu.bu.met.cs665;

import edu.bu.met.cs665.BusinessBank.Bank;
import edu.bu.met.cs665.BusinessBank.BankAccount;
import edu.bu.met.cs665.BusinessBank.BusinessChecking;
import edu.bu.met.cs665.BusinessBank.BusinessSavings;
import edu.bu.met.cs665.BusinessBank.Customer;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This main class is responsible for taking in user input and processing
 * transactions based on that input. Source: https://jmvidal.cse.sc.edu/csce145/
 * fall06/Ch12/Ch12%20Slides.pdf
 * https://stackoverflow.com/questions/29943128/reliance-on-default-encoding
 * @author dnguy
 */
public class Main {
  /**
   * Create bank object to run program.
   */
  public static void main(String[] args) {
    Bank bank = new Bank();
    try {
      // Read account number and check for existence
      Scanner readNum = new Scanner(System.in, "UTF-8");
      System.out.println("Enter your integer account number here: ");
      int accountNum = readNum.nextInt();

      // Obtain the users transaction preferences and read it.
      Scanner readTransaction = new Scanner(System.in,"UTF-8");
      System.out.println("What Transaction would you like to do today?"
          + "Type in 'w' for withdraw, 'd' for deposit,\n't' for transfer"
          + " or 'b' for check balance. Case will be ignored.");
      String transaction = readTransaction.next().toLowerCase();

      if ((transaction.contains("w")) || (transaction.contains("d"))
          || (transaction.contains("t"))) {
        Scanner readAmount = new Scanner(System.in,"UTF-8");
        // Enter the amount of money desired to withdraw.
        System.out.println("Enter the amount of money to withdraw,"
            + "deposit, or transfer. ");
        double amount = readAmount.nextDouble();
        // This will process the withdrawal transactions.
        if (transaction.contains("w")) {
          bank.withdraw(accountNum, amount);
        } else if (transaction.contains("d")) {
          System.out.println("For deposits over 20.00, 1 0" 
              + "cents charge will apply. Otherwise,"
              + "\nthe balance will be subtracted from deposit"
              + " amount applied.");
          // This will process the deposit transactions.
          bank.deposit(accountNum, amount);
        } else if (transaction.contains("t")) {
          // Enter the account number to transfer to.
          System.out.println("Enter the account number to " 
              + "transfer to: ");
          Scanner readToAccountNum = new Scanner(System.in,"UTF-8");
          // read the account transfer to
          int toAccountNum = readToAccountNum.nextInt();
          // complete transfer and print out statement
          bank.transfer(accountNum, toAccountNum, amount);
          System.out.println("The balance for account transferred " 
              + toAccountNum + " is "
              + bank.getAccount(toAccountNum).checkBalance() 
              + " dollars.");
        }
      } else if (transaction.contains("b")) {
        // Print if checking for balance.
        System.out.println("Your current balance is: " 
            + bank.getAccount(accountNum).checkBalance());
      } else { // notify user if system cannot process transactions.
        System.out.println("Cannot process transaction");
      } // print out balance at the end of transaction
      System.out.println("The balance for account holder: " 
          + accountNum + " is "
          + bank.getAccount(accountNum).checkBalance() 
          + " dollars.");
    } catch (InputMismatchException e) {
      // if bad user input such as string in the first time, print out message.
      System.out.println("This message appeared because the user input" 
          + " is invalid.");
    } catch (java.lang.NullPointerException e) {
      // if account does not exist, print this message
      System.out.println("This message appeared because the "
          + "account does not exist.");
    }
  }
}
