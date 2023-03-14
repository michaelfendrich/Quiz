create table users (
    id int primary key auto_increment,
    email varchar not null unique,
    password varchar not null,
    admin boolean not null
);