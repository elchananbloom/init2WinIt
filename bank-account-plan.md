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

In this section, you'll define key terms used within your application. Providing clear and consistent definitions for each term ensures that everyone on your team has a common understanding of the concepts. This is essential not only for your project's success but also for communicating effectively with stakeholders.

Example

Running Club
An organization based on a shared love of running. Clubs have members. They host runs. Some are informal with infrequent runs. Others are large, have budgets, and charge membership fees.

Runner
Anyone who signs up for a run. Runners can be members of a club, but don't have to be. All members are runners but not all runners are members.

Member
A runner who is formally affiliated with a running club. A runner can be a member of more than one club.

Club Admin
A running club member with an administrator role. They have more privileges in the Group Run application. All admins are members, but not all members are admins.

Run
A running event with a specific time, date, and location. A run may also include a route (stretch goal).

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
I will design and implement 4-7 independent database entities that represent different core concepts within the application. These entities will not simply be connected via a bridge table but will each have unique attributes and relationships to other entities. For example, I will create separate tables for users, running clubs, runs, and memberships.

MySQL for Data Management
Plan to meet requirement:
I will use MySQL as the relational database management system for storing and retrieving data. I will design the schema to ensure data integrity and optimize query performance. I’ll use Spring Data JPA with MySQL to manage the entities and perform CRUD operations.

Spring Boot, MVC, JDBC, Testing, React
Plan to meet requirement:
I will implement the backend of the application using Spring Boot, utilizing the MVC (Model-View-Controller) architecture for organizing the application structure. The JDBC will be used for database connections and data transactions. For the frontend, I will build the UI with React, ensuring it is responsive and functional. I will also write unit and integration tests to ensure that both the backend and frontend meet the application requirements.

An HTML and CSS UI Built with React
Plan to meet requirement:
I will create the user interface using React, ensuring a clean, user-friendly design with HTML and CSS. I will follow modern web development practices, including responsive design to ensure the app works on both mobile and desktop devices. The layout will be organized and intuitive for users to browse and sign up for runs.

Sensible Layering and Pattern Choices
Plan to meet requirement:
I will follow best practices for application architecture, utilizing layered design patterns. This includes separating the logic into distinct layers such as controller, service, and repository. I will ensure that the backend follows the Single Responsibility Principle and that the code is easy to maintain and scale.

A Full Test Suite that Covers the Domain and Data Layers
Plan to meet requirement:
I will implement a comprehensive test suite for the project. This will include unit tests for the domain layer (services and models) and the data layer (repositories and database interactions). I will also write integration tests to ensure that the application components work together as expected.

Must Have at Least 2 Roles (Example: User and Admin)
Plan to meet requirement:
I will implement role-based access control using Spring Security. The system will have at least two roles: User and Admin. Users will be able to sign up for runs, while admins will have privileges to create and manage runs, as well as approve or deny runner sign-ups. I will ensure the roles are securely handled through authentication and authorization.

## User Stories

In this section, you will outline how each user story will be implemented in your application. For each user story, explain the user’s goals, the actions they will take, the preconditions required for each action, and the postconditions that follow. This will help clarify how you plan to meet the functional needs of the project.

Example 

Create a Run
Goal: As a user, I want to create a run event that others can join.
Plan to meet requirement: I will design a form where users can submit a run, including details like a description, date and time, location, and the club hosting the event. Users must be logged in and have a MEMBER or ADMIN role to create a run. The run will require approval by an ADMIN if created by a MEMBER.
Precondition: User must be logged in with the MEMBER or ADMIN role.
Post-condition: If the user is a MEMBER, the run will be set to pending and will require ADMIN approval to be posted. If the user is an ADMIN, they can choose to post it immediately or keep it in a pending status.

Edit a Run
Goal: As a user, I want to edit an existing run event.
Plan to meet requirement: I will provide an edit feature that allows users to modify run details, such as the time, location, or maximum participants. Only future runs can be edited.
Precondition: User must be logged in with the MEMBER or ADMIN role. The run's date and time must be in the future.
Post-condition: If the user is a MEMBER, the run will be set to pending after edits. If the user is an ADMIN, they can choose to post the changes immediately or leave it pending.

Cancel a Run
Goal: As an ADMIN, I want to cancel a future run event.
Plan to meet requirement: I will provide a cancel feature for ADMIN users to disable a run, ensuring it no longer appears on the public-facing UI. The run’s data will remain intact, and it will only be marked as canceled.
Precondition: User must be logged in with the ADMIN role. The run’s date and time must be in the future.
Post-condition: The run is set to a canceled status and is no longer visible to the public but remains visible to admins.

Approve a Run
Goal: As an ADMIN, I want to approve or reject pending runs created by MEMBERS.
Plan to meet requirement: I will create an administrative interface where the ADMIN can view pending runs and decide whether to approve, edit, or cancel them.
Precondition: User must be logged in with the ADMIN role.
Post-condition: The run is either approved, edited, or canceled based on the admin's decision.

Browse Runs
Goal: As a user, I want to browse available runs in a format that suits me.
Plan to meet requirement: I will offer multiple browsing options: a text-based search by date and location, a calendar view to select runs, or a map view to see runs as pins. Users can filter runs based on location or date.
Precondition: None
Post-condition: None

Sign Up for a Run
Goal: As a runner, I want to sign up for a run event.
Plan to meet requirement: I will create a sign-up feature that allows users to register for a run if space is available. The system will check if the run has available slots and if the user is already registered.
Precondition: User must be logged in. The run must not be over-capacity, and the user cannot already be signed up for the run.
Post-condition: The user is successfully registered for the run.

Apply for Membership (Optional)
Goal: As a runner, I want to apply to become a member of a running club.
Plan to meet requirement: I will provide an option for runners to apply for membership with a club after attending runs. The application will be sent for approval to the club’s ADMIN.
Precondition: User must be logged in and not already a member of the club.
Post-condition: The membership status will be set to pending and await approval by an ADMIN.

Approve a Membership (Optional)
Goal: As an ADMIN, I want to approve or reject membership applications for the club.
Plan to meet requirement: I will create an administrative interface where admins can see pending membership applications and accept or reject them.
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

In this section, you will provide a visual representation of the relationships between the main classes in your application. The class diagram should illustrate how different entities in your system are connected and how they interact. It will include the classes, their attributes (fields), methods (functions), and relationships (e.g., inheritance, associations). You should focus on clearly depicting the objects and the role they play in the overall architecture of your system. The goal is to show the structure of your application in a way that makes it easy to understand how the system components are related to each other.

## Class List 

In this section, you will list all the classes that are part of your application. For each class, provide a brief description of its role in the system, and then include a list of methods and fields. The methods should describe what the class is capable of (e.g., creating a run, deleting a membership, etc.), while the fields represent the data it stores (e.g., a user’s name, a run’s date). This will give a comprehensive overview of your application’s components and their functionality. Be sure to include any constructors and important logic for each class to fully describe how it operates.

## Task List with estimated compeletion times 

In this section, you will provide a detailed breakdown of the tasks required to complete your project (any task over 4 hours must be broken down further). List each task involved in building and implementing the system, from setting up the database to creating the UI and testing the functionality. For each task, estimate how much time you expect it to take to complete, based on its complexity. This will help you manage your time and ensure that the project stays on track. Tasks should be organized logically, and you should be as detailed as possible, covering all the components required for your application.

