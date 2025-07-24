drop database if exists bank;
create database bank;
use bank;

create table `user` (
	user_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    address varchar(125) not null,
    phone_number varchar(10) not null,
    email varchar(256) not null,
    dob datetime not null,
    password_hash varchar(250) not null,
    created_at datetime default now(),
    `role` varchar(6) not null,
    UNIQUE(email)
    );
    
create table `account` (
	account_id int primary key auto_increment,
    `type` varchar(50) not null,
    balance decimal(20, 2) not null,
    account_number varchar(10) not null,
    created_at datetime default now(),
    user_id int not null,
    UNIQUE(account_number),
    constraint fk_account_user_id
		foreign key (user_id)
        references `user`(user_id)
);

create table transaction_category (
	transaction_category_id int primary key auto_increment,
    `name` varchar(20) not null
);

create table loan_type (
	loan_type_id int primary key auto_increment,
    `name` varchar(20) not null
); 



create table loan (
	loan_id int primary key auto_increment,
    date_approved datetime null,
    flat_interest decimal(10,2) not null,
    initial_amount decimal(10,2) not null,
    date_due datetime not null,
    `status` varchar(20) not null,
    created_at datetime default now(),
    balance decimal(10,2) not null,
    user_id int not null,
    loan_type_id int not null,
    constraint fk_loan_user_id
		foreign key (user_id)
        references user(user_id),
	constraint fk_loan_loan_type_id
		foreign key (loan_type_id)
        references loan_type(loan_type_id)
);

create table `transaction` (
	transaction_id int primary key auto_increment,
    amount decimal(20, 2) not null,
    `type` varchar(20) not null,
    transaction_date datetime default now(),
    `description` varchar(100) null,
    transaction_category_id int not null,
    account_id int null,
    loan_id int null,
    constraint fk_transaction_category_id
		foreign key (transaction_category_id)
        references transaction_category(transaction_category_id),
	constraint fk_transaction_account_id
		foreign key (account_id)
        references `account`(account_id),
	constraint fk_transaction_loan_id
		foreign key (loan_id)
        references loan(loan_id)
);



insert into loan_type (`name`)
values
('Personal'),
('Home Improvement'),
('Small Business'),
('Debt Consolidation'),
('Auto'),
('Auto Refinance');

create view user_transactions as
select u.user_id, 
u.first_name, 
u.last_name, 
acc.account_id, 
t.loan_id, 
t.transaction_id, 
acc.`type` account_type, 
t.amount transaction_amount, 
t.`type` transaction_type, 
tc.`name` transaction_category, 
t.transaction_date, 
year(t.transaction_date) `transaction_year`, 
month(t.transaction_date) `transaction_month`, 
(quarter(t.transaction_date)) `transaction_quarter`,
Case 
	when t.loan_id is not null then l.balance else null
END as loan_balance
from `user` u
inner join `account` acc
on u.user_id = acc.user_id
inner join `transaction` t
on t.account_id = acc.account_id
inner join transaction_category tc
on tc.transaction_category_id = t.transaction_category_id
left join loan l on l.loan_id = t.loan_id;


create view bank_transactions as
select t.amount, year(t.transaction_date) `year`, month(t.transaction_date) `month` , quarter(t.transaction_date) `quarter`, t.`type`
from `transaction` t;




insert into user(user_id, first_name, last_name, address, phone_number, email, dob, password_hash, created_at, role)
values 
(1, 'Guy', 'One', '123 Street', '1234567890', 'test1@example.com','2000-01-01', 'ABCDEF', '2025-01-01', 'ADMIN'),
(2, 'Person', 'Two', '456 Ave', '0987654321', 'test2@example.com','2000-02-02', 'ASFGR', '2025-02-02', 'USER'),
(3, 'Girl', 'Three', '123 Street', '1234567890', 'test3@example.com','2000-03-03', 'BFSBF', '2025-03-03', 'USER');

insert into `account`(`type`, balance, account_number, created_at, user_id)
values
('Savings', 2000.00, '123456', '2025-01-01', 1),
('Checking', 4000.00, '134123', '2025-02-23', 2),
('Checking',  300.00, '246810', '2024-03-04', 3);

insert into transaction_category values
(1, 'Groceries'),
(2, 'Check'),
(3, 'Car'),
(4, 'Loan');

insert into loan_type (`name`)
values
('Personal'),
('Home Improvement'),
('Small Business'),
('Debt Consolidation'),
('Auto'),
('Auto Refinance');

insert into loan values
(1, '2025-01-02', 20.00, 1020.00, '2025-02-02', 'APPROVED', '2025-01-01', 900.00, 1, 2),
(2, '2025-02-02', 20.00, 2020.00, '2025-02-02', 'REJECTED', '2025-01-01', 2020.00, 2, 4),
(3, '2025-03-03', 20.00, 520.00, '2025-02-02', 'IN-PROGRESS', '2025-04-04', 520.00, 3, 5);


insert into `transaction` values
(1, 200.00, 'WITHDRAWAL', '2025-01-02', 'emergency car fix', 3, 1, null),
(2, 120.00, 'LOAN', '2025-01-23', null, 1, 2, 1),
(3, 50.00, 'DEPOSIT', '2025-03-23', 'got paid', 2, 3, null);
	

