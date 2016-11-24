create schema finance_organiser;

create table category(
id int primary key auto_increment,
title varchar(45)
);

create table sheetrow(
id int primary key auto_increment,
title varchar(45),
order_num int,
estimated double,
actual double,
modified_at datetime,
current datetime,
category_id int,
FOREIGN KEY(category_id) REFERENCES category(id)
);

--create table item (
--id int primary key auto_increment,
--title varchar(45),
--order_num int,
--isMain boolean
--);
