create database be;

use be;

create table _users
(
    id       varchar(100) not null,
    username varchar(100) not null,
    password varchar(100) not null,
    name     varchar(100) not null,
    role     enum ('USER'),
    primary key (id),
    unique (username)
) engine innodb;

select *
from _users;

drop database be;
