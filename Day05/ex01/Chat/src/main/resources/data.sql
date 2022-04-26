INSERT INTO chat.users (login, password)
VALUES ('Zoomer', '12345');
INSERT INTO chat.users (login, password)
VALUES ('Boomer', 'qwerty');
INSERT INTO chat.users (login, password)
VALUES ('Doomer', 'password');
INSERT INTO chat.users (login, password)
VALUES ('Groomer', 'asdfg');
INSERT INTO chat.users (login, password)
VALUES ('Foomer', 'zxcvbn');

INSERT INTO chat.rooms (name, owner)
VALUES ('General', 1);
INSERT INTO chat.rooms (name, owner)
VALUES ('ft_memes', 2);
INSERT INTO chat.rooms (name, owner)
VALUES ('Java', 3);
INSERT INTO chat.rooms (name, owner)
VALUES ('Piscine', 4);
INSERT INTO chat.rooms (name, owner)
VALUES ('Help', 5);

INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (1, 5, 'How cook cookies?', '2022-04-25 20:00:01');
INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (2, 4, 'Day05 is hard', '2022-04-01 08:00:01');
INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (3, 3, 'Java is greater than C++', '2022-01-01 10:30:02');
INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (4, 2, 'lol kek cheburek', '1970-01-01 15:45:04');
INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (5, 1, 'Hellow World!', '1970-01-01 00:00:05');