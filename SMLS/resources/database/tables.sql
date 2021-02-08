CREATE TABLE `BOOK` (
	id varchar(200) primary key,
	title varchar(200),
	author varchar(200),
	publisher varchar(100),
	isAvail boolean default true
	)
	
 CREATE TABLE `MEMBER` (
	id varchar(200) primary key,
	name varchar(200),
	mobile varchar(20),
	email varchar(100)
	)
	
 CREATE TABLE `ISSUE` (
	bookID varchar(200) primary key,
	memberID varchar(200),
	issueTime timestamp default CURRENT_TIMESTAMP,
	renew_count integer default 0,
	FOREIGN KEY (bookID) REFERENCES BOOK(id),
	FOREIGN KEY (memberID) REFERENCES MEMBER(id)
	)
	
 CREATE TABLE `MAIL_SERVER_INFO` (
	server_name varchar(255),
	server_port integer,user_email varchar(1024),
	user_password varchar(1024),ssl_enabled boolean
	)
