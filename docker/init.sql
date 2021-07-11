create user if not exists 'liquibase'@'%' identified by 'secret';

-- hexagonal
create database if not exists `hexagonal` default character set = utf8mb4 default collate = utf8mb4_general_ci;
grant all privileges on `hexagonal`.* to 'liquibase'@'%';
flush privileges;

