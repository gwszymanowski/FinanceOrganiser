create schema finance_organiser;

create table category(
id int primary key auto_increment,
title varchar(45),
order_num int
);

--create table item (
--id int primary key auto_increment,
--title varchar(45),
--order_num int,
--isMain boolean
--);

create table sheetRow(
id int primary key auto_increment,
estimated double,
actual double,
created_at date,
modified_at date,
category_id int,
FOREIGN KEY(category_id) REFERENCES category(id)

);