drop database if exists website;
create database website;
use website;
create table users(username varchar(255), password varchar(255), email varchar(255));
create table products(name varchar(255), price float, picture varchar(255));
insert into products values ("Бяла тениска", 19.99, "t-shirt-white.jpg"), ("Черна тениска", 19.99, "t-shirt-black.jpg"), ("Сива тениска", 19.99, "t-shirt-gray.jpg");
insert into users values ("admin", "admin", "admin@site.com"), ("momchil", "1234", "umil2@abv.bg");