create schema finance_organiser;

create table category(
id int primary key auto_increment,
title varchar(45)
);

create table sheetRow(
id int primary key auto_increment,
order_num int,
estimated double,
actual double,
created_at datetime,
modified_at datetime,
category_id int,
FOREIGN KEY(category_id) REFERENCES category(id) ON CASCADE DELETE

);

--create table item (
--id int primary key auto_increment,
--title varchar(45),
--order_num int,
--isMain boolean
--);
