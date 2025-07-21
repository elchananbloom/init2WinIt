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
    password_hash varchar(250) not null,
    created_at datetime not null,
    role varchar(6) not null,
    UNIQUE(email)
    );
    
create table `account` (
	account_id int primary key auto_increment,
    `type` varchar(50) not null,
    balance decimal(20, 2) not null,
    account_number varchar(10) not null,
    created_at datetime not null,
    UNIQUE(account_number),
    constraint fk_account_user_id
		foreign key (user_id)
        references `user`(user_id)
);

create table `transaction` (
	transaction_id int primary key auto_increment,
    amount decimal(20, 2) not null,
    type varchar(20) not null,
    transaction_date datetime not null,
    description varchar(100) null,
    constraint fk_transaction_category_id
		foreign key (transaction_category_id)
        references category(transaction_category_id),
	constraint fk_transaction_account_ud
		foreign key (account_id)
        references `account`(account_id),
	constraint fk_transaction_loan_id,
		foreign key (loan_id)
        references loan(loan_id)
);

create table loan (
	loan_id int primary key auto_increment,
    date_approved datetime null,
    flat_interest decimal(10,2) not null,
    initial_amount decimal(10,2) not null,
    date_due datetime null,
    status varchar(10) not null,
    created_at datetime not null,
    balance decimal(10,2) not null,
    constraint fk_loan_user_id
		foreign key (user_id)
        references user(user_id),
	constraint fk_loan_loan_type_id
		foreign key (loan_type_id)
        references loan_type(loan_type_id)
);

create table transaction_category (
	transaction_category int primary key auto_increment,
    `name` varchar(20) not null
);

create table loan_type (
	loan_type_key int primary key auto_increment,
    `name` varchar(20) not null
); 

delimiter //
create procedure set_known_good_state()
begin

end//