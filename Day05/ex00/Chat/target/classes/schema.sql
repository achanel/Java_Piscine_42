DROP SCHEMA if exists chat cascade;

CREATE SCHEMA if not exists chat;

CREATE TABLE if not exists chat.users
(
    id          BIGSERIAL PRIMARY KEY,
    login       VARCHAR NOT NULL,
    password    VARCHAR NOT NULL
);

CREATE TABLE if not exists chat.chatrooms
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR NOT NULL,
    owner_id    BIGINT  NOT NULL,
    foreign key (owner_id) references chat.users(id)
);

CREATE TABLE if not exists chat.users_chatrooms
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    chatroom_id BIGINT NOT NULL,
    foreign key (user_id) references chat.users(id),
    foreign key (chatroom_id) references chat.chatrooms(id)
);

CREATE TABLE if not exists chat.messages
(
    id        BIGSERIAL PRIMARY KEY,
    author    BIGINT    NOT NULL,
    room      BIGINT    NOT NULL,
    text      TEXT,
    date_time TIMESTAMP,
    foreign key (author) references chat.users(id),
    foreign key (room) references chat.chatrooms(id)
);