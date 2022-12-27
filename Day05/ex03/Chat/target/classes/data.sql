INSERT INTO chat.users(login, password) VALUES
                                            ('Zoomer', '12345'), ('Boomer', 'qwerty'), ('Doomer', 'password'),
                                            ('Goomer', 'asdfg'), ('Foomer', 'zxcvbn')
ON CONFLICT DO NOTHING;

INSERT INTO chat.chatrooms(name, owner_id) VALUES
                                            ('General', (SELECT id FROM chat.users WHERE login = 'Zoomer')),
                                            ('ft_memes', (SELECT id FROM chat.users WHERE login = 'Boomer')),
                                            ('Java', (SELECT id FROM chat.users WHERE login = 'Doomer')),
                                            ('Piscine', (SELECT id FROM chat.users WHERE login = 'Goomer')),
                                            ('Help', (SELECT id FROM chat.users WHERE login = 'Foomer'))
ON CONFLICT DO NOTHING;

INSERT INTO chat.messages (author, room, text) VALUES
                                                  ((SELECT id FROM chat.users WHERE login = 'Zoomer'),      (SELECT id FROM chat.chatrooms WHERE name = 'General'), 'How cook cookies?'),
                                                  ((SELECT id FROM chat.users WHERE login = 'Boomer'),      (SELECT id FROM chat.chatrooms WHERE name = 'ft_memes'), 'lol kek cheburek'),
                                                  ((SELECT id FROM chat.users WHERE login = 'Doomer'), (SELECT id FROM chat.chatrooms WHERE name = 'Java'), 'Java is greater than C++'),
                                                  ((SELECT id FROM chat.users WHERE login = 'Goomer'),    (SELECT id FROM chat.chatrooms WHERE name = 'Piscine'), 'Day05 is hard'),
                                                  ((SELECT id FROM chat.users WHERE login = 'Foomer'),  (SELECT id FROM chat.chatrooms WHERE name = 'Help'), 'Hello World!')
ON CONFLICT DO NOTHING;

INSERT INTO chat.users_chatrooms (user_id, chatroom_id) VALUES
                                                      ((SELECT id FROM chat.users WHERE login = 'Zoomer'), (SELECT id FROM chat.chatrooms WHERE name = 'General')),
                                                      ((SELECT id FROM chat.users WHERE login = 'Boomer'), (SELECT id FROM chat.chatrooms WHERE name = 'ft_memes')),
                                                      ((SELECT id FROM chat.users WHERE login = 'Doomer'), (SELECT id FROM chat.chatrooms WHERE name = 'Java')),
                                                      ((SELECT id FROM chat.users WHERE login = 'Goomer'), (SELECT id FROM chat.chatrooms WHERE name = 'Piscine')),
                                                      ((SELECT id FROM chat.users WHERE login = 'Foomer'), (SELECT id FROM chat.chatrooms WHERE name = 'Help'))
ON CONFLICT DO NOTHING;