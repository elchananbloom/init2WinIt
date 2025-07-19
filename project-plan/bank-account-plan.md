# Capstone Project Planning

## Deliverables 
- Proposal 
- Wireframes
- Database Schema

## Proposal Outline / Example 

## Problem Statement

Context: There are many, many bank companies in the world. With these banks, you can deposit or withdrawal at their locations with ease, but what if someone wanted to deposit or withdrawal money without having to travel to the bank. What if someone needed a loan, but did not want to go to their location and go through their process of applying on paper?

Core Problem: Banking companies need to have a working system in order for users to add or withdrawal money and also need a loan system for these users in order to make it easier on them.

Impact on Users: It makes it less efficient on their clients to have to either go to the bank to do their business which can lead to a loss of clientele.

Opportunity for Solution: Creating an application in which a user can deposit and withdraw money and request/receive loans from the bank will make it easier on the customers of the bank and the bank owners themselves and can lead to more customers using their banking service.

## Technical Solution 

Overview of the Solution: We will build a web application that allows people to sign up for a bank and be able to make accounts to add or withdraw money into that bank. They can also request loans where the admin can deny or approve the loans. The admin can also see the statistics of the users’ transactions and see what accounts are in their system in order to manage it. They can also edit the categories that are in the system for the users to categorize their transactions.


Key Features and Functionalities:

Account Creation: Users can create banking accounts in order to withdraw and deposit into it. They can see their past payments in said accounts in their UI and pay their loans from their account. Admins will be able to delete these accounts if needed. 

Loan System: Users can request up to two loans and the admin can deny or approve these loans and can see all loans in the system.

Charts: Admin will be able to see charts of the statistics of everything in the system in order to see how their system is performing and how much is being put in and taken from them.

Categories: Admins will be able to add, edit, and delete the categories of the payments so that users can add more specific categories to their transactions if it is needed.



User Scenarios:

Scenario 1: Jay just got a new job and has a card with all of their money, but wants to be able to deposit it into their account. They go to the banking app, makes a new account and makes a deposit by putting in their card information into the system and depositing how much they made that week.
Scenario 2: Cee wants to buy a car, but does not have the funds for it, they request a loan of  $5000. The admin sees this, and sees that Cee does make a lot of deposits and also sees that the bank has a good amount of money due to the high bar charts of deposits. They then accept their request for a loan. Cee can then see that they have a loan and they need to pay back 5100 due to a fixed interest increase.

Frontend: React for building interactive UIs with dynamic features.
Backend: Spring Boot for creating a secure REST API to handle user authentication, account and loan management, category management, transactions, and data storage.
Database: MySQL to store user, transaction and account/loan data, including relationships between accounts and transactions, users and loans/accounts, etc.
Chart Integration: Charts.js or mui to be able to visualize the data in the system.
Payment Integration: Stripe api for the user to put in their payment info and pay
Authentication: JWT (JSON Web Token) for secure user login.

## Glossary


User: 
Anyone above 18 with accepted login credentials. Can be an Admin of the system, or a customer accessing their account.

Account: 
A representation of a banking account for a user. Contains a balance and can have a series of transactions.

Loan: 
A representation of a loan taken out by the user and approved by the Admin. Can have a series of loan transactions.

Transaction: 
A representation of a transaction between a customer and an account or loan. 

Category: 
A representation of a way to describe and categorize transactions. Can be updated by the Admin.

Loan Type: 
A representation for the type of loan. Describes the purpose of the loan.


## High Level Requirements

Manage 4-7 Database Tables (Entities) that are Independent Concepts
Plan to meet requirement:
The team will design and implement 4 Database Entities that are independent concepts. These Entities are User, Account, Transaction and Loan. Additionally, the team will implement a category table and a loan type table that are meant to help with the descrition and categorization of transactions and loans respectively. 


MySQL for Data Management
Plan to meet requirement:
The team will use MySQL as the relational database management system for storing and retrieving data. The team will design the schema to ensure data integrity and optimize query performance. The team will use Spring Data JPA with MySQL to manage the entities and perform CRUD operations.

Docker and Docker Compose
Plan to meet requirement:
The team will use Docker and Docker compose to containerize the application. The team will ensure application portability using a consistent environment definition.

Spring Boot, MVC, JDBC, Testing, React
Plan to meet requirement:
The team will implement the backend of the application using Spring Boot, utilizing the MVC (Model-View-Controller) architecture for organizing the application structure. The JDBC will be used for database connections and data transactions. For the frontend, The team will build the UI with React, ensuring it is responsive and functional. The team will also write unit and integration tests to ensure that both the backend and frontend meet the application requirements.

An HTML and CSS UI Built with React
Plan to meet requirement:
The team will create the user interface using React, ensuring a clean, user-friendly design with HTML and CSS. The team will follow modern web development practices, including responsive design to ensure the app works on both mobile and desktop devices. The layout will be organized and intuitive for users to browse and interact with functionality.

Sensible Layering and Pattern Choices
Plan to meet requirement:
The team will follow best practices for application architecture, utilizing layered design patterns. This includes separating the logic into distinct layers such as controller, service, and repository. The team will ensure that the backend follows the Single Responsibility Principle and that the code is easy to maintain and scale.

A Full Test Suite that Covers the Domain and Data Layers
Plan to meet requirement:
The team will implement a comprehensive test suite for the project. This will include unit tests for the domain layer (services and models) and the data layer (repositories and database interactions). The Team will also write integration tests to ensure that the application components work together as expected.

Must Have at Least 2 Roles (Example: User and Admin)
Plan to meet requirement:
The team will implement role-based access control using Spring Security. The system will have at least two roles: User and Admin. 

Users will be able to : Create a userAccount, login to a user account, Create a Bank account, create a loan application, withdraw/deposit (update) from Bank Account, View accounts, view approved loans and pending loans, withdraw (delete) a loan Application while its pending, and pay loans.

Admins will be able to: view all loan applications, accept loan applications, reject loan application ,create transaction categories, view transaction categories, delete transaction categories (Not in use), delete loan applications (Must be pending), update transaction categories, view statistics.

## User Stories

Create a userAccount
Goal: As a user, I want to create a user account to log into.
Plan to meet requirement: The team will provide a form where users can submit user information and credentials, including first name, last name, address, phone number, email and password.
Precondition: None
Post-condition: If the user account fields are not null and the phone number and the email address are unique, then a userAccount should be created.

Login to a user account
Goal: As a user, I want to login to my account.
Plan to meet requirement: The team will provide a form where users will submit their credentials to gain access to their accounts.
Precondition: User must have an existing account in the system.
Post-condition: If the user is a USER and provides credentials that match a user in the system, then allow the user to access their accounts.

Create a Bank account
Goal: As a USER, I want to create an account.
Plan to meet requirement: The team will provide a form where USERs will submit account information to create account.
Precondition: User must be logged in with the USER role.
Post-condition: If the user is a USER and the user provides the fields for an account, then a new account will be created.

Create a loan application
Goal: As a USER, I want to apply for a loan
Plan to meet requirement: The team will provide a form where USERs will submit loan application information to apply for a loan.
Precondition: User must be logged in with the USER role.
Post-condition: If user is a USER and the account fields are not empty, then create a new account.

withdraw a loan application
Goal: As a USER, I want to withdraw a loan application.
Plan to meet requirement: The team will provide a button that allows users to withdraw from loan applications that are pending.
Precondition: User must be logged in with the USER role.
Post-condition: If user is a USER and the loan application is pending then allow the user to withdraw the application.

Withdraw from Bank Account
Goal: As a user, I want to withdraw from my accounts.
Plan to meet requirement: The team will provide a form that shows the account being withdrawn from and the amount being withdrawn.
Precondition: If the user is logged in as a USER
Post-condition: If the user is a USER and the amount being withdrwan isnt greater than the amount in the account, then allow the USER to withdraw the money.

Deposit into a Bank Account
Goal: As a USER, I want to deposit into my accounts.
Plan to meet requirement: The team will provide a form that shows the account being deposited into and the amount being deposited.
Precondition: If the user is logged in as a USER
Post-condition: If the user is a USER and the amount being deposited isnt negative, then allow the USER to deposit the money.

View approved loans and pending loans
Goal: As a USER, I want view my approved and pending loans.
Plan to meet requirement: The team will provide an button for USERs view their loans and loan applications.
Precondition: user must be logged in as a USER
Post-condition: If the USER is loged in and they click loans button, then their active and pending loans will be displayed.

Pay loans
Goal: As a USER, I want to pay my loans.
Plan to meet requirement: The team will provide a form that allows users to select the loan to pay and the ammount being payed.
Precondition: User must be logged in and have active loans.
Post-condition: If the user is logged in as a USER and the amount being payed is positive, then allow the user to pay the loan.



View all loans
Goal: As an ADMIN, I want to view all of the loans in the system
Plan to meet requirement: The team will provide a button for ADMINS to view all of the loans 
Precondition: User must be logged in as an ADMIN
Post-condition: If the User logged in is an ADMIN, then display all of the loans.


Accept loan applications
Goal: As an ADMIN, I want to be able to accept loan applications that are pending.
Plan to meet requirement: The team will provide an option for ADMINS to accept pending loan applications.
Precondition: User must be logged in as an ADMIN and loan application must be pending.
Post-condition: If the User logged in is an ADMIN and the loan application is Pending then allow the ADMIN to accept the loan.

Reject loan application
Goal: As an ADMIN, I want to be able to reject loan applications that are pending.
Plan to meet requirement: The team will provide an option for ADMINS to reject pending loan applications.
Precondition: User must be logged in as an ADMIN and loan application must be pending.
Post-condition: If the User logged in is an ADMIN and the loan application is Pending then allow the ADMIN to reject the loan.

Create transaction categories
Goal: As an ADMIN, I want to be able to create transaction categories.
Plan to meet requirement: The team will provide a form where ADMINS can submit category information to create transaction categories.
Precondition: User must be logged in as an ADMIN.
Post-condition: If the User logged in is an ADMIN and The category information is not null and unique, then allow the ADMIN to accept th loan.

View transaction categories
Goal: As an ADMIN, I want to view all of the transaction categories in the system.
Plan to meet requirement: The team will provide a button for ADMINS to view all of the transaction categories. 
Precondition: User must be logged in as an ADMIN
Post-condition: If the User logged in is an ADMIN, then display all of the transaction categories.

Delete transaction categories (Not in use)
Goal: As an ADMIN, I want to delete transaction categories that are not in use.
Plan to meet requirement: The team will provide a button for ADMINS to remove categories. 
Precondition: User must be logged in as an ADMIN
Post-condition: If the User logged in is an ADMIN, and the category is not in use, then allow the ADMIN to delete the transaction category.

Delete loan applications (Must be pending)
Goal: As an ADMIN, I want to delete loan applications that are pending.
Plan to meet requirement: The team will provide a button for ADMINS to remove loan applications. 
Precondition: User must be logged in as an ADMIN
Post-condition: If the User logged in is an ADMIN, and the loan application is pending, then allow the ADMIN to delete the loan application.

Update transaction categories
Goal: As an ADMIN, I want to update transaction category values.
Plan to meet requirement: The team will provide a form for ADMINS to update category values. 
Precondition: User must be logged in as an ADMIN
Post-condition: If the User logged in is an ADMIN, and the category value submited is unique, then allow the ADMIN to update the transaction category.

View statistics
Goal: As an ADMIN, I want to view statistics of the transactions and accounts in the system
Plan to meet requirement: The team will provide a view for ADMINS to see statistics involving the transactions and accounts. 
Precondition: User must be logged in as an ADMIN
Post-condition: If the User logged in is an ADMIN, then allow the ADMIN view the statistics of the transactions and accounts.

Process Payment (Optional)
Goal: As a USER, I want to create a transaction using a debit or credit card.
Plan to meet requirement: The team will provide a form for USERS to enter card information to complete transaction. 
Precondition: User must be logged in as an USER
Post-condition: If the User is logged in as a USER and the user provides valid card data, then allow the user to complete transaction using a credit or debit card.




## Learning Goal 

Application: The team will use chart.js or mui to display reports of financial data that could be of use to the Admin.
Research and Resources: The team will start with the official chart.js documentation and a video tutorial on its useage in react.
Challenges: We anticipate needing to figure out how to properly pass data whose structure has changed in order to genreate reports. To address this, the team will practice with dummy data first and attempt to generate a variety of data visualizations.
Success Criteria: If Admins can different data visualizations after logging in the app, then I'll consider this learning goal achieved.

## Class Diagram 
```
 init2WinIt
    ├── server
    │   └── src
    │       ├── main
    │       │   └── java
    │       │       └── learn
    │       │           └── bank
    │       │               ├── App.java
    │       │               ├── mappers
    │       │               │   ├── UserMapper.java                                  --map user to java
    │       │               │   ├── AccountMapper.java                               --map account to java
    │       │               │   ├── LoanMapper.java                                  --map loan to java
    │       │               │   ├── TransactionCategoryMapper.java                   --map transaction category to java
    │       │               │   ├── TransactionMapper.java                           --map transaction to java
    │       │               │   ├── LoanTypeMapper.java                              --map loan type to java
    │       │               │   └── StatisticsMapper.Java                            --map statistics to java
    │       │               ├── data
    │       │               │   ├── DataException.java
    │       │               │   ├── UserJDBCTemplateRepository.java                  --user repository
    │       │               │   ├── AccountJDBCTemplateRepository.java               --account repository
    │       │               │   ├── LoanJDBCTemplateRepository.java                  --loan repository
    │       │               │   ├── TransactionCategoryJDBCTemplateRepository.java   --transaction category repository
    │       │               │   ├── TransactionJDBCTemplateRepository.java           --transaction repository
    │       │               │   ├── StatisticsJDBCTemplateRepository.java            --statistics repository
    │       │               │   ├── UserRepository.java                              --user repository
    │       │               │   ├── AccountRepository.java                           --account repository interface
    │       │               │   ├── LoanRepository.java                              --loan repository interface
    │       │               │   ├── CategoryRepository.java                          --category repository interface
    │       │               │   ├── TransactionRepository.java                       --transaction repository interface
    │       │               │   └── StatisticsRepository.java                        --statistic repository interface
    │       │               ├── domain
    │       │               │   ├── Result.java                                      --domain result for handling
    │       │               │   ├── ResultType.java                                  --result enums
    │       │               │   ├── UserService.java                                 --user validation rules
    │       │               │   ├── AccountService.java                              --account validation rules
    │       │               │   ├── LoanService.java                                 --loan validation rules
    │       │               │   ├── CategoryService.java                             --category validation rules
    │       │               │   ├── TransactionService.java                          --transaction validation rules
    │       │               │   └── StatisticsService.java                           --statistics pass through sql calls
    │       │               ├── models
    │       │               │   ├── User.java                                        --user model
    │       │               │   ├── Account.java                                     --account model
    │       │               │   ├── Loan.java                                        --loan model
    │       │               │   ├── TransactionCategory.java                         --transaction category model
    │       │               │   ├── Transaction.java                                 --transaction model
    │       │               │   └── LoanType.java                                    --loan type enum
    │       │               └── Controllers
    │       │                   ├── UserController.java                              --user controller
    │       │                   ├── AccountController.java                           --account controller
    │       │                   ├── LoanController.java                              --loan controller
    │       │                   ├── TransactionCategoryController.java               --transaction category controller
    │       │                   ├── TransactionController.java                       --transaction controller
    │       │                   └── StatisticsController.java                        --statistics controller
    │       └── test
    │           └── java
    │               └── learn
    │                   └── solar
    │                       ├── data
    │                       │   ├── UserJDBCTemplateRepositoryTest.java                --user repository tests
    │                       │   ├── AccountJDBCTemplateRepositoryTest.java             --account repository tests
    │                       │   ├── LoanJDBCTemplateRepositoryTest.java                --loan repository tests
    │                       │   ├── TransactionCategoryJDBCTemplateRepositoryTest.java --transaction category repository tests
    │                       │   ├── TransactionJDBCTemplateRepositoryTest.java         --transaction repository tests
    │                       │   └── StatisticsJDBCTemplateRepositoryTest.java          --statistics repository tests
    │                       ├── domain
    │                       │   ├── UserServiceTest.java                               --user service tests    
    │                       │   ├── AccountServiceTest.java                            --account service tests
    │                       │   ├── LoanServiceTest.java                               --loan service tests
    │                       │   ├── CategoryServiceTest.java                           --category service tests
    │                       │   ├── TransactionServiceTest.java                        --transaction service tests
    │                       │   └── StatisticsServiceTest.java                         --statistics service tests
    │                       └── controllers
    │                           ├── UserControllerTest.java                            --user controller tests
    │                           ├── AccountControllerTest.java                         --account controller tests
    │                           ├── LoanControllerTest.java                            --loan controller tests
    │                           ├── TransactionCategoryControllerTest.java             --transaction category controller tests
    │                           ├── TransactionControllerTest.java                     --transaction controller tests
    │                           └── StatisticsControllerTest.java                      --statistics controller test
    └── client
        └── innit2WinIt
            ├── public
            │   └── index.html
            └── src
                ├── App.js
                ├── index.js
                ├── package.json
                ├── components
                │   ├── UserNav.jsx
                │   ├── AdminNav.jsx
                │   ├── Transaction.jsx
                │   ├── Transactions.jsx
                │   ├── UserAccountHeader.jsx
                │   ├── Modal.jsx
                │   ├── NewLoanModal.jsx
                │   ├── NewTransactionModal.jsx
                │   ├── LoanHeader.jsx
                │   ├── PendingLoan.jsx
                │   ├── AdminLoan.jsx
                │   ├── AdminTransactionCategory.jsx
                │   ├── AdminTransactionCategories.jsx
                │   ├── NewTransactionCategoryModal.jsx
                │   ├── AdminAccount.jsx
                │   └── AdminAccounts,jsx
                └── pages
                    ├── LoginPage.jsx
                    ├── SignUpPage.jsx
                    ├── NewAccount.jsx
                    ├── UserAccountPage.jsx
                    ├── UserLoanPage.jsx
                    ├── AdminStatisticsPage.jsx
                    ├── AdminLoansPage.jsx
                    ├── AdminTransactionCategoriesPage.jsx
                    └── AdminAccountsPage.jsx 
```
## Class List 

### App 10 m
- `public static void main(String[])` -- instantiate all required classes with valid arguments, dependency injection. run controller


### data.DataException 0.5 h
Custom data layer exception.
- `public DataException(String, Throwable)` -- constructor, Throwable arg is the root cause exception

### data.UserJDBCTemplateRepository 0.5 h
- `public findById(int)` -- find user by id
- `public add(User)` -- create a user
- `public getUserByEmail(String)` -- get user by email for security purposes



### data.UserRepository 10 m
Contract for UserRepository.
- `public findById(int)`
- `public add(User)`


### data.AccountJDBCTemplateRepository 0.5 h
- `public Account add(Account)` -- create a Account
- `public List<Account> findAll()` -- finds all Accounts in the data
- `public List<Account> findByUserId(int)` -- finds all Accounts in the data source that belongs to a user
- `public Account findById(int)` -- finds Account in the data source
- `private User getUserById(int)` -- finds Account user


### data.AccountRepository (interface) 10 m
Contract for AccountRepository.
- `List<Account> findAll()`
- `List<Account> findById(int)`
- `Account add(Account)`
- `Account findById(int)`


### data.LoanJDBCTemplateRepository 1 h
- `public Loan add(Loan)` -- create a Loan
- `public Loan delete(int)` -- delete a Loan
- `public Loan update(Loan)` -- update a Loan
- `public List<Loan> findAll()` -- finds all Loans in the data
- `public List<Loan> findByUserId(int)` -- finds all Loans in the data source that belongs to a user
- `public Loan findById(int)` -- finds a Loan in the data source
- `private User getUserById(int)` -- finds Account user
- `private User getLoanTypeById(int)` -- finds Account loan type



### data.LoanRepository (interface) 10 m
Contract for LoanRepository.
- `List<Loan> findAll()`
- `List<Loan> findByUserId(int)`
- `Loan add(Loan)`
- `Loan delete(int)`
- `Loan update(Loan)`
- `Loan findById(int)`


### data.TransactionCategoryJDBCTemplateRepository 0.5 h
- `public TransactionCategory add(TransactionCategory)` -- create a TransactionCategory
- `public List<TransactionCategory> findAll()` -- finds all TransactionCategories in the data
- `public delete(int)` --delete Transaction Category
- `public update(int)` --update Transaction Category


### data.TransactionCategoryRepository 10 m
Contract for TransactionCategoryRepository.
- `public TransactionCategory add(TransactionCategory)`
- `public List<TransactionCategory> findAll()`
- `public delete(int)`
- `public update(int)`

### data.TransactionJDBCTemplateRepository 1 h
- `public Transaction add(Transaction)` -- add a new transaction
- `public List<Transaction> findByAccount(int)` -- find all transactions for an account
- `public List<Transaction> findByLoan(int)` -- find all transactions for a loan
- `private User getAccountById(int)` -- finds Account
- `private User getCategoryById(int)` -- finds transaction category 
- `private User getLoanById(int)` -- finds transaction loan


### data.TransactionRepository 10 m
Contract for TransactionRepository.
- `Transaction add(Transaction)`
- `List<Transaction> findByAccount(int)`
- `List<Transaction> findByLoan(int)`

### data.StatisticsJDBCTemplateRepository 1.5 h
 - `Map<Integer, Double> getDepositsByYear()` -- get all the deposits made and group by a year
 - `Map<Integer, Double> getWithdrawsByYear()` -- get all the withdraws made and group by a year
 - `Map<String, Double> getWithdrawsByCategory()` -- get all the withdraws made and group by a transaction category

### data.StatisticsRepository 10 m
Contract for StatisticsRepository.
 - `Map<Integer, Double> getDepositsByYear()`
 - `Map<Integer, Double> getWithdrawsByYear()`
 - `Map<String, Double> getWithdrawsByCategory()`

### domain.Result 20 m
- `private ArrayList<String> messages` -- error messages
- `private T payload` -- an optional generic type
- `public List<String> getMessages()` -- messages getter, create a new list
- `public T getPayload()` -- T getter
- `public void setPayload(T)` -- T setter
- `public void addMessage(String)` -- adds an error message to messages

### domain.ResultType 10 m
 - enum for the result type (SUCCESS, INVALID, NOT_FOUND)

### domain.UserService 20 m
- `public findById(int)` -- find user by id
- `public add(User)` -- create a user

### domain.AccountService 0.5 h
- `public Account add(Account)` -- create a Account
- `public List<Account> findAll()` -- finds all Accounts in the data
- `public List<Account> findByUserId(int)` -- finds all Accounts in the data source that belongs to a user
- `public Account findById(int)` -- finds Account in the data source that

### domain.LoanService 1 h
- `public Loan add(Loan)` -- create a Loan
- `public Loan delete(int)` -- delete a Loan
- `public Loan update(Loan)` -- update a Loan
- `public List<Loan> findAll()` -- finds all Loans in the data
- `public List<Loan> findByUserId(int)` -- finds all Loans in the data source that belongs to a user
- `public Loan findById(int)` -- finds a Loan in the data source

### domain.TransactionCategoryService 0.5 h
- `public TransactionCategory add(TransactionCategory)` -- create a TransactionCategory
- `public List<TransactionCategory> findAll()` -- finds all TransactionCategories in the data
- `public delete(int)` --delete Transaction Category
- `public update(int)` --update Transaction Category

### domain.TransactionService 2 h
- `public Transaction add(Transaction)` -- add a new transaction
- `public List<Transaction> findByAccount(int)` -- find all transactions for an account
- `public List<Transaction> findByLoan(int)` -- find all transactions for a loan

### domain.StatisticsService 0.5 h
 - `Map<Integer, Double> getDepositsByYear()` -- get all the deposits made and group by a year
 - `Map<Integer, Double> getWithdrawsByYear()` -- get all the withdraws made and group by a year
 - `Map<String, Double> getWithdrawsByCategory()` -- get all the withdraws made and group by a transaction category

### models.User 20 m
 - `private int userId`
 - `private String firstName`
 - `private String lastName`
 - `private String address`
 - `private String phone`
 - `private String email`
 - `private String passwordHash`
 - `private Date createdAt`
 - `private String role`

### models.Account 20 m
 - `private int accountId`
 - `private String accountName`
 - `private String accountType`
 - `private BigDecimal accountBalance`
 - `private String accountNumber`
 - `private User user`
 - `private Date createdAt`

### models.Loan 20 m
 - `private int loanId`
 - `private LoanType loanType`
 - `private Date loanDateApproved`
 - `private double loanFlatInterest`
 - `private BigDecimal loanInitialAmount`
 - `private Date loanDueDate`
 - `private String loanStatus`
 - `private User user`
 - `private String loanDescription`
 - `private Date createdAt`
 - `private Date loanBalance`

### models.TransactionCategory 10 m
 - `private int TransactionCategoryId`
 - `private int TransactionCategoryName`

### models.Transaction 20 m
 - `private int TransactionId`
 - `private BigDecimal TransactionAmount`
 - `private String TransactionType`
 - `private TransactionCategory TransactionCategory`
 - `private Account account`
 - `private Date TransactionDate`
 - `private String TransactionDescription`
 - `private Loan loan`

### models.LoanType 20 m
 - `private int loanTypeId`
 - `private String name`

### controllers.UserController 0.5 h
- `public findById(int)` -- find user by id
- `public add(User)` -- create a user

### controllers.AccountController 1 h
- `public Account add(Account)` -- create a Account
- `public List<Account> findAll()` -- finds all Accounts in the data
- `public List<Account> findByUserId(int)` -- finds all Accounts in the data source that belongs to a user
- `public Account findById(int)` -- finds Account in the data source by it's id

### controllers.LoanController 1 h
- `public Loan add(Loan)` -- create a Loan
- `public Loan delete(int)` -- delete a Loan
- `public Loan update(Loan)` -- update a Loan
- `public List<Loan> findAll()` -- finds all Loans in the data
- `public List<Loan> findByUserId(int)` -- finds all Loans in the data source that belongs to a user
- `public Loan findById(int)` -- finds a Loan in the data source

### controllers.TransactionCategoryController 1 h
- `public TransactionCategory add(TransactionCategory)` -- create a TransactionCategory
- `public List<TransactionCategory> findAll()` -- finds all TransactionCategories in the data
- `public delete(int)` --delete Transaction Category
- `public update(int)` --update Transaction Category

### controllers.TransactionController 1 h
- `public Transaction add(Transaction)` -- add a new transaction
- `public List<Transaction> findByAccount(int)` -- find all transactions for an account
- `public List<Transaction> findByLoan(int)` -- find all transactions for a loan

### controllers.StatisticsController 1 h
 - `Map<Integer, Double> getDepositsByYear()` -- get all the deposits made and group by a year
 - `Map<Integer, Double> getWithdrawsByYear()` -- get all the withdraws made and group by a year
 - `Map<String, Double> getWithdrawsByCategory()` -- get all the withdraws made and group by a transaction category

### mappers.UserMapper 0.5 h
map sql results to User

### mappers.AccountMapper 0.5 h
map sql results to Account

### mappers.LoanMapper 0.5 h
map sql results to Loan

### mappers.TransactionCategoryMapper 0.5 h
map sql results to TransactionCategory

### mappers.TransactionMapper 0.5 h
map sql results to Transaction

### mappers.LoanTypeMapper 0.5 h
map sql results to LoanType

### mappers.StatisticsMapper 0.5 h
map sql results to Statistics


## client
### components
#### UserNav 1.5 h
 - `getUserAccounts(userId)` -- get all the user accounts to show on the navbar
 - `getUserLoans(userId)` -- get all the user loans to show on the navbar
 - `handleAddAccount()` -- open new page for adding a new user account
#### AdminNav 1 h
 - showing what the admin can do.
#### Transaction 1 h
 - seeing transaction details
#### Transactions 0.5 h
 - `getAccountTransactions(accountId)` -- get all of the account transactions and call Transaction to show the details
#### UserAccountHeader 1 h
 - `getAccount(accountId)` -- get the user account to show details like balance.
#### Modal 1 h
 - generic modal sheet
#### NewLoanModal 2 h
 - `getLoanTypes()` -- get an enum of the loan types
 - `postNewLoanApplication()` -- add new loan
#### NewTransactionModal 4 h
 - stripe API
#### LoanHeader 1.5 h
 - `getLoan(loanId)` -- get a loan to show details like balance.
#### PendingLoan 2 h
 - widget for showing a pending loan
#### AdminLoan 1 h
 - widget for showing an approved or disapproved loan
#### AdminTransactionCategory 0.5 h
 - widget for a transaction category
#### AdminTransactionCategories 0.5 h
 - `getTransactionCategories()` -- get all the transaction categories and call AdminTransactionCategory on each
#### NewTransactionCategoryModal 2 h
 - `postNewTransactionCategory()` -- add a new transaction category
#### AdminAccount 1 h
 - widget for showing account details
#### AdminAccounts 0.5 h
 - `getAllAccounts()` -- get all the bank accounts and call AdminAccount on each
### pages
#### LoginPage 2 h
 - `login()` -- log in to the bank
#### SignUpPage 2 h
 - `signUp()` -- sign a new user up
#### NewAccount 2 h
 - `postNewAccount()` -- create a new account
#### UserAccountPage 3 h
 - `handleDeposit()` -- deposit to the account
 - `handleWithdraw()` -- withdraw from the account
 - `handleNewLoan()` -- open a new loan modal
#### UserLoanPage 1 h
 - `handleDeposit()` -- deposit to the loan
#### AdminStatisticsPage 4 h
 - showing a few statistics about the bank
#### AdminLoansPage 0.5 h
 - `getAllLoans()` -- get all the loans and call AdminLoan or PendingLoan based on status
#### AdminTransactionCategoriesPage 0.5 h
 - `handleAddCategory()` -- open a modal to add transaction category
#### AdminAccountsPage 0.5 h
 - call the AdminAccounts component


## Task List with estimated compeletion times 


See above in the class list

## Repository Tests
 - User 1.5 h
 - Account 1.5 h
 - Loan 1.5 h
 - TransactionCategory 1.5 h
 - Transaction 1.5 h
 - Statistics 1.5 h

## Service Tests
 - User 1.5 h
 - Account 1.5 h
 - Loan 1.5 h
 - TransactionCategory 1.5 h
 - Transaction 1.5 h
 - Statistics 1.5 h

## Controller Tests
 - User 1.5 h
 - Account 1.5 h
 - Loan 1.5 h
 - TransactionCategory 1.5 h
 - Transaction 1.5 h
 - Statistics 1.5 h

## Module Tests
 - User 0.5 h
 - Account 0.5 h
 - Loan 0.5 h
 - TransactionCategory 0.5 h
 - Transaction 0.5 h
 - Statistics 0.5 h

 ## Docker Containerization
- Docker & Docker Compose 2-3 h
- Container orchestration for development 1h
- Environment-specific configurations 1 h
- Database containerization 1h