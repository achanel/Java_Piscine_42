drop schema if exists chat cascade;

create schema if not exists chat;

create table if not exists chat.users (
    id bigserial primary key,
    username text not null,
    password text not null
);

insert into chat.users (username, password)
values ('user1', '123'),
       ('user2', 'qwe'),
       ('user3', 'asdf'),
       ('user4', '987qwe'),
       ('user5', 'password');
