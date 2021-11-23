# Task 1 : Design pattern and its Use Case Scenario Description (further information provided in FinalProjectDeannaNguyen.pdf)

Note, the readme, documentation, powerpoint slides, were revised to include explanations on the code for refactoring. In addition, the UML Diagram was changed to include the correct arrows.


#Use Case Scenario: A commercial/business bank has decided to fully transition to mobile banking. However, one of the main concerns is appealing to new potential customers. In addition, the bank is concerned with lending to customers who may not have the ability to pay the interest rates. 

# Design Goals for this system: 
The design goals for this system are the following:
1.	The system shall be able to create a Business Checking and Savings account.
2.	The system shall be able to check for balance.
3.	The system shall be able to make withdrawals.
4.	The system shall be able to make deposits.
5.	The system shall be able to charge a service fee if over 6 transactions is made, or the user is about to have a negative balance.
6.	The system shall be able to check for statements that contain the words "checking" and "savings".

# Flexibility of System
How the system is flexible is that there is an abstract BankAccount class. An abstract class "optionally provides implementation code for each declared operation", meaning that it defers implementation to subclasses (Rogers, 2001). This allows the ability to add different types of BankAccount classes.

# Simplicity and Understandability of System
How the system is simplified and understandable is the following:
1) Each class has a singular responsibility. For example, the responsibility of the CustomerBuilder class is to "build" the customer based on the information provided in the customer file.
2) Each class name and method has a clear purpose. For example, the "deposit" method is used to deposit funds.
3) The system implements Factory Pattern  and Builder Patterns, which assists with constructing objects. These patterns help with simplicity because the constructor of the subclasses do not have to be called directly.

# How is duplicate code avoided
Duplicate code is avoided through inheritance and refactoring. 
For inheritance, the "BankAccount" abstract class has withdraw, deposit, transfer and check balance methods. These methods can be inherited by the "BusinessChecking" and "BusinessSavings" classes. This way, these methods do not have to be re-written in subclasses.

Second, the "checkBalBelow" attribute was "pulled up" to the BankAccount Abstract class. This helped reduce the line of code and reduce duplicates.

# Design Patterns used
1. Factory Pattern
Class Name: Bank
Lines of Code: 34-52
Explanation: The reason for selecting this design pattern is because multiple Business Checking and Business Savings accounts may be created. Incorporating a creational pattern will fit this criteria. Second, the right new objects need to be created at runtime (Teymourian, module 2 lecture notes).   Having a method ensures this.
When the "Bank" is initialized, the files will be read using "createAccount" and "buildCustomer". The methods are private because from a business standpoint, the bank wants to ensure the Customer information is private and secure. Making this method public will make it easier for unauthorized users to obtain the customer information.

2. Builder Pattern
Class Name: Customer
Lines of Code: 91-93
Explanation: The reason for selecting this design pattern is because it can establish a "class hierarchy" for the Customer class. A customer may have multiple attributes that may be optional or mandatory. To effectively create customer objects, a "Builder Pattern" can assist with that (credits to Facilitator Michael Huang).


# Task 2 - UML Class Diagram. 

The diagram is designed based on the design pattern descriptions stated above.
![ii (18)](https://user-images.githubusercontent.com/86865687/129954194-4e3d63bf-1a65-4828-8e3b-7e957c169966.png)



# Preconditions
1.  There are no combined accounts (both account holder has checking and savings combined).
2.  There are no “joint-accounts” such as family accounts, etc. This is only applied for commercial banking and savings account.
3.  For transfers, the 5 dollar transaction fee will not apply since there is no “combined accounts”.
4.  The account files are pre-written in the “input2text” file to reflect the Potential customer accounts.
5.  The customer accounts are pre-written in customer.txt file to reflect customer information.
6.  Potential customers are scanned through the “scanner”.
7.  The potential clients have consulted the company to provide the information in the textfiles.
8.  Credit Scores must be at least 680 or above to create an account.
9.  Customer accounts are read first before creating accounts.
10. Creating accounts and building customers are done as private methods because from a security standpoint, this information should not be public.
11. The files will be read when the bank object is initialized. This is under the assumption the bank has a database with existing customers.
12. In the main, the user is able to interact and input values. However, a receipt will be printed out with every transaction saying "The balance for account holder:  is  dollars." This is expected.
13. In the main, the user can only complete one transaction at a time. The user will NOT be prompted to complete another transaction.
14. After initializing the "bank" object, there will already be accounts created. The assumption is that the customers have interacted with the bank and expressed interest in opening an account prior. The eligible account numbers are 1000,1001 and 1005.
15. Once finish, a receipt will be printed out for one transaction containing the user account number and balance. This will be for every transaction whether it's a withdrawal, deposit, or even an error message. This is expected to happen to mirror a printed receipt.
16. The application is fully functioning, on, and there are no errors or issues while running the application.
17. Only current and eligible customers can complete a transaction.
18. The errors in the checkstyle are related to package naming. They cannot be changed as this is the original naming convention provided in the template.
19. JUnit tests are only for existing account numbers.

# Conceptual Solutions

The conceptual solutions are the following:
1.  Meet Requirements provided.
    - Withdraw money.
    - Deposit money.
    - Transfer money between two accounts.
    - Create a business checking and savings account.
    - Detect for overdraft or exceeding withdrawls.
    - Detect for checking and savings account types.
    
2.  Implement the Factory and Builder Design Patterns.
3.  Prioritize Flexibility, Simplicity, Understandability, and Reduction of Duplicate Code


# How to compile the project

We use Apache Maven to compile and run this project. 

You need to install Apache Maven (https://maven.apache.org/)  on your system. 

Type on the command line: 

```bash
mvn clean compile
```

# How to create a binary runnable package 


```bash
mvn clean compile assembly:single
```


# How to run (see FinalProjectDeannaNguyen.pdf documentation for visual pictures)

```bash
mvn -q clean compile exec:java -Dexec.executable="edu.bu.met.cs665.Main" -Dlog4j.configuration="file:log4j.properties"
```

We recommand the above command for running the Main Java executable. 

1. When running the main, you will be prompted with "Enter your integer account number here: ". The current account numbers that are qualified are 1000,1001, and 1005 based on the test. Using any other account number will give you a message saying "the account does not exist."

2. Next question is "What Transaction would you like to do today?Type in 'w' for withdraw, 'd' for deposit,
't' for transfer or 'b' for check balance. Case will be ignored?". If none of those 4 letters entered, a message will say "cannot process transaction".

3. If withdraw, deposit, or transfer, enter the amount in double (ex. 20.00). This represents transactions in US Dollars. Enter a non - double value will give you an error message saying "the message appeared because user input is invalid".

4. If b for balance, you will get a message saying "Your current balance is:".

5. At the end of every transaction (including the exception statement), you will get a message saying "the balance for account holder:  is  dollars.". This is to mimick a receipt being printed out after every transaction. This will occur for every transaction regardless and is to be expected.

6. The transaction occurs only once. The user will not be reprompted.



# Run all the unit test classes.


```bash
mvn clean compile test checkstyle:check  spotbugs:check
```
The following information about the tests are noted.
1. A bank object will be initialized.
2. In the "runbeforetest" methods, the account holders will be retrieved using the "getAccount" method.
3. The customer accounts will be retrieved using "getCustomer.
4. There are 5 tests, which test for the deposit ,check balance, withdraw, transfer, and retrieve customer capabilities.
5. There will be 3 tests for the deposit, withdraw, and transfer method.
6. The customer tests will test for accurate retrieval of first and last name. The customers are stored in the customer database.


# Using Spotbugs to find bugs in your project 

To see bug detail using the Findbugs GUI, use the following command "mvn findbugs:gui"

Or you can create a XML report by using  


```bash
mvn spotbugs:gui 
```

or 


```bash
mvn spotbugs:spotbugs
```


```bash
mvn spotbugs:check 
```

check goal runs analysis like spotbugs goal, and make the build failed if it found any bugs. 


For more info see 
https://spotbugs.readthedocs.io/en/latest/maven.html


SpotBugs https://spotbugs.github.io/ is the spiritual successor of FindBugs.


# Run Checkstyle 

CheckStyle code styling configuration files are in config/ directory. Maven checkstyle plugin is set to use google code style. 
You can change it to other styles like sun checkstyle. 

To analyze this example using CheckStyle run 

```bash
mvn checkstyle:check
```

This will generate a report in XML format


```bash
target/checkstyle-checker.xml
target/checkstyle-result.xml
```

and the following command will generate a report in HTML format that you can open it using a Web browser. 

```bash
mvn checkstyle:checkstyle
```

```bash
target/site/checkstyle.html
```
