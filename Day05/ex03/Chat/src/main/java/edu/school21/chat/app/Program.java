package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.ChatroomRepositoryJdbcImpl;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UserRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Optional;

public class Program {
    public static void main(String[] args) throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("postgres");


        UserRepositoryJdbcImpl usersRepository = new UserRepositoryJdbcImpl(ds);
        ChatroomRepositoryJdbcImpl chatroomRepository = new ChatroomRepositoryJdbcImpl(ds, usersRepository);
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds, usersRepository, chatroomRepository);
        Optional<Message> messageOptional = messagesRepository.findById(5L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText("Bye");
            message.setDateTime(null);
            messagesRepository.update(message);
        }
        ds.close();
    }
}
