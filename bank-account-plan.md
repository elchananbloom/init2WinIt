# Capstone Project Planning

## Deliverables 
- Proposal 
- Wireframes
- Database Schema

## Proposal Outline / Example 

## Problem Statement

Context: Briefly describe the context or environment in which the problem exists. Who are the primary users or groups affected? What is the scope of the issue?

Core Problem: What specific, unmet need or challenge does this project address? Explain in simple terms what is currently missing, difficult, or ineffective for users.

Impact on Users: Describe how this problem affects the users or stakeholders. What pain points do they experience as a result of this issue? What specific frustrations, inefficiencies, or missed opportunities arise from the current situation?

Opportunity for Solution: Why is solving this problem meaningful for your intended audience? How would a solution add value to their experience or address their needs in a unique way?

Example:
Context: Running clubs worldwide organize public group runs to foster community, celebrate events, and encourage active lifestyles. However, these group runs are often informal, without centralized registration or event discovery platforms.

Core Problem: There is no centralized platform for runners to easily discover and sign up for group runs. Clubs typically post run information inconsistently—on homepages, social media, or not at all—making it difficult for potential participants to find events and know whether they are still open for sign-ups.

Impact on Users: Runners miss out on group runs that could be beneficial for their training and social goals due to lack of awareness or difficulty in finding event information. Running clubs, in turn, face uncertainty with attendance, which can be disappointing if the turnout is too low or too high.

Opportunity for Solution: By creating an application to post, organize, and manage group runs, both clubs and runners will benefit. Clubs will have a predictable attendance rate, while runners will find it easier to join group runs that fit their schedules and locations, helping both parties achieve their objectives effectively.

## Technical Solution 

Overview of the Solution: Describe the high-level approach you plan to take to solve the problem outlined in your Problem Statement. What kind of application or system will you create? How will it address the core problem?

Key Features and Functionalities: List and describe the main features of the application. What are the critical functionalities that users will need to perform in order to effectively interact with the system?

User Scenarios: Explain how the technical solution will support different user needs. Provide two to three specific use cases or scenarios that illustrate how users (e.g., runners, club admins) will interact with the system and what value they will gain from each interaction.

Technology Stack: Briefly mention the key technologies, frameworks, and tools you will use to build the application. Why did you choose these tools, and how will they help you achieve your solution?

Example:

Overview of the Solution: We will build a web application that allows running clubs to post group runs on a formal calendar, where runners can browse upcoming runs, sign up, and join events. The application will allow both club admins to create, edit, and manage runs, and runners to discover events, sign up, and apply for membership.

Key Features and Functionalities:

Run Creation: Club admins can create group runs, specifying key details like time, location, route (optional), and maximum participants.
Run Discovery: Runners can browse upcoming group runs via a map, calendar, or list view. They can filter by date, location, and running club.
Sign-Up for Runs: Runners can sign up for available runs that are not at capacity. They will receive notifications for upcoming runs.
Admin Approval: Admins have the ability to approve or reject runs and membership applications.
User Registration: Users (runners) can create an account, log in, and track their run history and preferences.
User Scenarios:

Scenario 1: Emma, a traveler, uses the Group Run app to search for runs in Austin during her vacation. She finds a few runs, signs up, and meets local runners to stay active and enjoy her time in the city.
Scenario 2: Kelsey, a casual runner, enjoys joining occasional runs near his Chicago neighborhood. He uses the app to filter available runs and easily signs up for the ones that suit his schedule, without needing to commit to a club.
Technology Stack:

Frontend: React for building interactive UIs with dynamic features.
Backend: Spring Boot for creating a secure REST API to handle user authentication, run management, and data storage.
Database: MySQL to store user and run data, including relationships between users and runs.
Map Integration: Google Maps API to display run locations on a map.
Authentication: JWT (JSON Web Token) for secure user login and role management.

## Glossary


User
Anyone with accepted login credentials. Can be an Admin of the system, or a customer accessing their account.

Account
A representation of a banking account for a user. Contains a balance and can have a series of transactions.

Loan
A representation of a loan taken out by the user and approved by the Admin. Can have a series of loan transactions.

Transaction
A representation of a transaction between a customer and an account or loan. 

Category
A representation of a way to describe and categorize transactions. Can be updated by the Admin.

Loan Type
A representation for the type of loan. Describes the purpose of the loan.


## High Level Requirements

Manage 4-7 database tables (entities) that are independent concepts. A simple bridge table doesn't count.
MySQL for data management
Spring Boot, MVC, JDBC, Testing, React
An HTML and CSS UI that's built with React
Sensible layering and pattern choices
A full test suite that covers the domain and data layers
Must have at least 2 roles (example User and Admin)

In this section, you will outline how you plan to meet the high-level requirements of your project. For each requirement, explain the specific steps you will take, the tools or technologies you will use, and how you'll implement them to meet the project’s objectives.

Manage 4-7 Database Tables (Entities) that are Independent Concepts
Plan to meet requirement:
The team will design and implement 4 Database Entities that are independent concepts. These Entities are User, Account, Transaction and Loan. Additionally, the team will implement a category table and a loan type table that are meant to help with the descrition and categorization of transactions and loans respectively. 


MySQL for Data Management
Plan to meet requirement:
The team will use MySQL as the relational database management system for storing and retrieving data. The team will design the schema to ensure data integrity and optimize query performance. The team will use Spring Data JPA with MySQL to manage the entities and perform CRUD operations.

Spring Boot, MVC, JDBC, Testing, React
Plan to meet requirement:
The team will implement the backend of the application using Spring Boot, utilizing the MVC (Model-View-Controller) architecture for organizing the application structure. The JDBC will be used for database connections and data transactions. For the frontend, The team will build the UI with React, ensuring it is responsive and functional. the team will also write unit and integration tests to ensure that both the backend and frontend meet the application requirements.

An HTML and CSS UI Built with React
Plan to meet requirement:
The team will create the user interface using React, ensuring a clean, user-friendly design with HTML and CSS. The team will follow modern web development practices, including responsive design to ensure the app works on both mobile and desktop devices. The layout will be organized and intuitive for users to browse and sign up for runs.

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

In this section, you will outline how each user story will be implemented in your application. For each user story, explain the user’s goals, the actions they will take, the preconditions required for each action, and the postconditions that follow. This will help clarify how you plan to meet the functional needs of the project.

Example 

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
Goal: As a USER, I want to pay mhy loans.
Plan to meet requirement: The team will provide a form that allows users to select the loan to pay and the ammount being payed.
Precondition: User must be logged in and have active loans.
Post-condition: If the user is logged in as a USER and 



View all loan applications
Pay loans
Goal: As a runner, I want to apply to become a member of a running club.
Plan to meet requirement: The team will provide an option for runners to apply for membership with a club after attending runs. The application will be sent for approval to the club’s ADMIN.
Precondition: User must be logged in and not already a member of the club.
Post-condition: The membership status will be set to pending and await approval by an ADMIN.


Accept loan applications, 
Pay loans
Goal: As a runner, I want to apply to become a member of a running club.
Plan to meet requirement: The team will provide an option for runners to apply for membership with a club after attending runs. The application will be sent for approval to the club’s ADMIN.
Precondition: User must be logged in and not already a member of the club.
Post-condition: The membership status will be set to pending and await approval by an ADMIN.

Reject loan application,
Pay loans
Goal: As a runner, I want to apply to become a member of a running club.
Plan to meet requirement: The team will provide an option for runners to apply for membership with a club after attending runs. The application will be sent for approval to the club’s ADMIN.
Precondition: User must be logged in and not already a member of the club.
Post-condition: The membership status will be set to pending and await approval by an ADMIN.

Create transaction categories,
Pay loans
Goal: As a runner, I want to apply to become a member of a running club.
Plan to meet requirement: The team will provide an option for runners to apply for membership with a club after attending runs. The application will be sent for approval to the club’s ADMIN.
Precondition: User must be logged in and not already a member of the club.
Post-condition: The membership status will be set to pending and await approval by an ADMIN.

View transaction categories,
Pay loans
Goal: As a runner, I want to apply to become a member of a running club.
Plan to meet requirement: The team will provide an option for runners to apply for membership with a club after attending runs. The application will be sent for approval to the club’s ADMIN.
Precondition: User must be logged in and not already a member of the club.
Post-condition: The membership status will be set to pending and await approval by an ADMIN.

Delete transaction categories (Not in use), 
Pay loans
Goal: As a runner, I want to apply to become a member of a running club.
Plan to meet requirement: The team will provide an option for runners to apply for membership with a club after attending runs. The application will be sent for approval to the club’s ADMIN.
Precondition: User must be logged in and not already a member of the club.
Post-condition: The membership status will be set to pending and await approval by an ADMIN.

Delete loan applications (Must be pending), 
Pay loans
Goal: As a runner, I want to apply to become a member of a running club.
Plan to meet requirement: The team will provide an option for runners to apply for membership with a club after attending runs. The application will be sent for approval to the club’s ADMIN.
Precondition: User must be logged in and not already a member of the club.
Post-condition: The membership status will be set to pending and await approval by an ADMIN.

Update transaction categories
Pay loans
Goal: As a runner, I want to apply to become a member of a running club.
Plan to meet requirement: The team will provide an option for runners to apply for membership with a club after attending runs. The application will be sent for approval to the club’s ADMIN.
Precondition: User must be logged in and not already a member of the club.
Post-condition: The membership status will be set to pending and await approval by an ADMIN.

view statistics
Pay loans
Goal: As a runner, I want to apply to become a member of a running club.
Plan to meet requirement: The team will provide an option for runners to apply for membership with a club after attending runs. The application will be sent for approval to the club’s ADMIN.
Precondition: User must be logged in and not already a member of the club.
Post-condition: The membership status will be set to pending and await approval by an ADMIN.

Approve a Membership (Optional)
Goal: As an ADMIN, I want to approve or reject membership applications for the club.
Plan to meet requirement: The team will create an administrative interface where admins can see pending membership applications and accept or reject them.
Precondition: User must be logged in with the ADMIN role.
Post-condition: The membership will either be approved or rejected, preventing the runner from reapplying multiple times if rejected.

## Learning Goal 

- What specific knowledge or skill do you aim to gain from this project that you haven’t yet learned? Why is it meaningful to you or the project?
- Describe how this new knowledge or skill will be used within the project. What specific part of the application or feature will rely on it?
- List initial resources you plan to use to understand this concept (e.g., official documentation, tutorials, or textbooks). Are there any third-party libraries or tools? Will you need an API key or extra setup?
- What challenges do you anticipate in learning and applying this skill? How do you plan to address them (e.g., experimenting, testing with dummy data, seeking mentorship)?
- How will you measure whether you have achieved your learning goal? What will the successful implementation of this skill or technology look like in your project?

Note: pick a learning goal that is both ambitious and realistic, one that will directly improve the quality of their capstone project while also pushing you to expand your skill set.

Example: 

Learning Goal: I want to learn how to integrate Google Maps into a web application.

Application: I will use Google Maps API to display the location of each run on an interactive map within the app.
Research and Resources: I’ll start with the official Google Maps API documentation and a Udemy course on map APIs in JavaScript.
Challenges: I anticipate needing to figure out how to dynamically load map locations and handle API key security. To address this, I’ll practice with dummy data first and research security best practices for frontend applications.
Success Criteria: If users can see a Google Maps widget in the app that dynamically updates with each run location, then I’ll consider this learning goal achieved.

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
    │       │               │   ├── AccountMapper.java                               --account model
    │       │               │   ├── LoanMapper.java                                  --map loan to java
    │       │               │   ├── TransactionCategoryMapper.java                   --map transaction category to java
    │       │               │   ├── TransactionMapper.java                           --map transaction to java
    │       │               │   ├── LoanTypeMapper.java                              --map loan type to java
    │       │               │   └── StatisticsMapper.Java                            --map statistics to java
    │       │               ├── data
    │       │               │   ├── DataException.java
    │       │               │   ├── AccountJDBCTemplateRepository.java               --account repository
    │       │               │   ├── LoanJDBCTemplateRepository.java                  --loan repository
    │       │               │   ├── TransactionCategoryJDBCTemplateRepository.java   --transaction category repository
    │       │               │   ├── TransactionJDBCTemplateRepository.java           --transaction repository
    │       │               │   ├── StatisticsJDBCTemplateRepository.java            --statistics repository
    │       │               │   ├── AccountRepository.java                           --account repository interface
    │       │               │   ├── LoanRepository.java                              --loan repository interface
    │       │               │   ├── CategoryRepository.java                          --category repository interface
    │       │               │   ├── TransactionRepository.java                       --transaction repository interface
    │       │               │   └── StatisticsRepository.java                        --statistic repository interface
    │       │               ├── domain
    │       │               │   ├── Result.java                                      --domain result for handling
    │       │               │   ├── ResultType.java                                  --result enums
    │       │               │   ├── AccountService.java                              --account validation rules
    │       │               │   ├── LoanService.java                                 --loan validation rules
    │       │               │   ├── CategoryService.java                             --category validation rules
    │       │               │   ├── TransactionService.java                          --transaction validation rules
    │       │               │   └── StatisticsService.java                           --statistics pass through sql calls
    │       │               ├── models
    │       │               │   ├── Account.java                                     --account model
    │       │               │   ├── Loan.java                                        --loan model
    │       │               │   ├── TransactionCategory.java                         --transaction category model
    │       │               │   ├── Transaction.java                                 --transaction model
    │       │               │   └── LoanType.java                                    --loan type enum
    │       │               └── ui
    │       │                   ├── AccountController.java                           --UI account controller
    │       │                   ├── LoanController.java                              --UI loan controller
    │       │                   ├── TransactionCategoryController.java               --UI transaction category controller
    │       │                   ├── TransactionController.java                       --UI transaction controller
    │       │                   └── StatisticsController.java                        --UI statistics controller
    │       └── test
    │           └── java
    │               └── learn
    │                   └── solar
    │                       ├── data
    │                       │   ├── AccountJDBCTemplateRepositoryTest.java             --account repository tests
    │                       │   ├── LoanJDBCTemplateRepositoryTest.java                --loan repository tests
    │                       │   ├── TransactionCategoryJDBCTemplateRepositoryTest.java --transaction category repository tests
    │                       │   ├── TransactionJDBCTemplateRepositoryTest.java         --transaction repository tests
    │                       │   └── StatisticsJDBCTemplateRepositoryTest.java          --statistics repository tests
    │                       ├── domain
    │                       │   ├── AccountServiceTest.java                        --account service tests
    │                       │   ├── LoanServiceTest.java                           --loan service tests
    │                       │   ├── CategoryServiceTest.java                       --category service tests
    │                       │   ├── TransactionServiceTest.java                    --transaction service tests
    │                       │   └── StatisticsServiceTest.java                     --statistics service tests
    │                       └── controller
    │                           ├── AccountController.java                             --UI account controller tests
    │                           ├── LoanController.java                                --UI loan controller tests
    │                           ├── TransactionCategoryController.java                 --UI transaction category controller tests
    │                           ├── TransactionController.java                         --UI transaction controller tests
    │                           └── StatisticsController.java                          --UI statistics controller test
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
                │   ├── AdminTrasactionCategory.jsx
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

### App
- `public static void main(String[])` -- instantiate all required classes with valid arguments, dependency injection. run controller


### data.DataException


Custom data layer exception.


- `public DataException(String, Throwable)` -- constructor, Throwable arg is the root cause exception


### data.AccountJDBCTemplateRepository
- `private String filePath`
- `public Account add(Account)` -- create a Account
- `private List<Account> findAll() -- finds all Accounts in the data
- `private List<Account> findByUserId(int)` -- finds all Accounts in the data source that belongs to a user
- `private String serialize(Account)` -- convert a Account into a String (a line) in the file
- `private Account deserialize(String)` -- convert a String into a Account


### data.AccountRepository (interface)


Contract for AccountFileRepository and AccountRepositoryTestDouble.
-  `List<Account> findAll()`
- `List<Account> findById(int)`
- `Account add(Account)`


### data.LoanJDBCTemplateRepository
- `private String filePath`
- `public Loan add(Loan)` -- create a Loan
- `public Loan delete(int)` -- delete a Loan
- `public Loan update(int)` -- update a Loan
- `private List<Loan> findAll() -- finds all Loans in the data
- `private List<Loan> findByUserId(int)` -- finds all Loans in the data source that belongs to a user
- `private String serialize(Loan)` -- convert a Loan into a String (a line) in the file
- `private Loan deserialize(String)` -- convert a String into a Loan


### data.LoanRepository (interface)


Contract for LoanFileRepository and LoanRepositoryTestDouble.
-  `List<Loan> findAll()`
- `List<Loan> findById(int)`
- `Loan add(Loan)`
- `Loan delete(Loan)`
- `Loan update(Loan)`


### data.TransactionCategoryJDBCTemplateRepository
- `private String filePath`
- `public TransactionCategory add(TransactionCategory)` -- create a TransactionCategory
- `public List<TransactionCategory> findAll() -- finds all TransactionCategorys in the data
- `public delete(int)`--delete Transaction Category
- `public update(int)` --update Transaction Category
- `private String serialize(TransactionCategory)` -- convert a Account into a String (a line) in the file
- `private Account deserialize(TransactionCategory)` -- convert a String into a Account


### data.TransactionCategoryRepository


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

In this section, you will provide a detailed breakdown of the tasks required to complete your project (any task over 4 hours must be broken down further). List each task involved in building and implementing the system, from setting up the database to creating the UI and testing the functionality. For each task, estimate how much time you expect it to take to complete, based on its complexity. This will help you manage your time and ensure that the project stays on track. Tasks should be organized logically, and you should be as detailed as possible, covering all the components required for your application.

