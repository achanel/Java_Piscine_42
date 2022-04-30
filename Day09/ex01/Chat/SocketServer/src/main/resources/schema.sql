DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    username    VARCHAR NOT NULL,
    password    VARCHAR
);
DROP TABLE IF EXISTS messages;

CREATE TABLE IF NOT EXISTS messages
(
    message VARCHAR
);
