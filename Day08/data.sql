DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    email    VARCHAR NOT NULL
);


INSERT INTO users (email)
VALUES ('1@mail.ru');
INSERT INTO users (email)
VALUES ('2@mail.ru');
INSERT INTO users (email)
VALUES ('3@mail.ru');
INSERT INTO users (email)
VALUES ('4@mail.ru');
INSERT INTO users (email)
VALUES ('5@mail.ru');



DROP TABLE IF EXISTS users CASCADE;

create table users
(
    id SERIAL PRIMARY KEY,
    email varchar(100) not null,
    password varchar(100) not null
);

insert into users (email, password) values ('achanel@gmail.com', '123');
insert into users (email, password) values ('achanel2@gmail.com', 'qwe');
insert into users (email, password) values ('achanel3@gmail.com', 'asd');
insert into users (email, password) values ('achanel4@gmail.com', 'zxc');