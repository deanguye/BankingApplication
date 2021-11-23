package edu.bu.met.cs665.BusinessBank;

/**
 * The responsibility of this class is to store customer objects. Source:
 * https://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/
 * https://refactoring.guru/design-patterns/builder/java/example
 */
public class Customer {
  // These attributes are for the customer.
  private String first; // First name
  private String last; // Last name
  private final int accountNum; // Account Number
  private String phoneNum; // phoneNum
  private String address; // Address

  // This is the constructor for the customer. it will implement
  // builder
  private Customer(CustomerBuilder builder) {
    this.first = builder.first; // builder for first name
    this.last = builder.last; // builder for last name
    this.accountNum = builder.accountNum; // builder for account number
    this.phoneNum = builder.phoneNum; // builder for phoneNum
    this.address = builder.address; // builder for address
  }

  /**
   * These are all getter methods for customer. All attributes except account
   * number are immutable, which is why the have getter and setters. Making
   * account number immutable makes sense because first names, last names, phone
   * numbers, and addresses can be changed.
   * https://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/
   * https://www.java-design-patterns.com/patterns/builder/
   * 
   * @return
   */
  public String getFirst() { // get first name
    return first;
  }

  public String setFirst(String name) { // get first name
    return first = name;
  }

  public String getLast() { // get last name
    return last;
  }

  public String setLast(String name) { // set last name
    return last = name;
  }

  public String getphoneNum() { // get phoneNum
    return phoneNum;
  }

  public String setphoneNum(String phone) { // set phoneNum
    return phoneNum = phone;
  }

  public String getAddress() { // get address
    return address;
  }

  public String setAddress(String newAddress) { // set address
    return address = newAddress;
  }

  public int getAccountNum() { // get account number
    return accountNum;
  }

  // This class is use to build the customer based on attributes
  // https://www.java-design-patterns.com/patterns/builder/
  public static class CustomerBuilder {
    private String first;
    private String last;
    private int accountNum;
    private String phoneNum;
    private String address;

    // Build the customer based on required first name and last name.
    // This is required
    public CustomerBuilder(String first, String last) {
      this.first = first;
      this.last = last;
    }

    // Add phoneNum number to customer object. This is optional.
    public CustomerBuilder phoneNum(String phoneNum) {
      this.phoneNum = phoneNum;
      return this;
    }

    // Add account number to customer object, this is required.
    public CustomerBuilder accountNum(int num) {
      this.accountNum = num;
      return this;
    }

    // Add address to customer object. This is optional.
    public CustomerBuilder address(String address) {
      this.address = address;
      return this;
    }

    /**
     *  Build customer object based on the customer builder method inputs.
     */
    public Customer build() {
      Customer customer = new Customer(this); // create new customer object
      // based on builder.
      return customer; // return customer object
    }
  }
}
