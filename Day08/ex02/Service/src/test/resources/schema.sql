DROP TABLE IF EXISTS users CASCADE;

create table users
(
    id INTEGER IDENTITY PRIMARY KEY,
    email varchar(100) not null,
    password varchar(100) not null
);