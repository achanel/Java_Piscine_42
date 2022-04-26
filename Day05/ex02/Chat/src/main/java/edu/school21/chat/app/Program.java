package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.ChatroomRepositoryJdbcImpl;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UserRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("postgres");

        User creator = new User(2L, "user", "user", new ArrayList(), new ArrayList());
        User author = creator;
        Chatroom room = new Chatroom(3L, "room", creator, new ArrayList());
        Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
        UserRepositoryJdbcImpl usersRepository = new UserRepositoryJdbcImpl(ds);
        ChatroomRepositoryJdbcImpl chatroomRepository = new ChatroomRepositoryJdbcImpl(ds, usersRepository);
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds, usersRepository, chatroomRepository);

        messagesRepository.save(message);
        System.out.println(message.getId());
        ds.close();
    }
}
