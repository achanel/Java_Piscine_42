package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UserRepositoryJdbcImpl;
import edu.school21.chat.repositories.ChatroomRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("postgres");

        UserRepositoryJdbcImpl usersRepository = new UserRepositoryJdbcImpl(ds);
        ChatroomRepositoryJdbcImpl chatroomRepository = new ChatroomRepositoryJdbcImpl(ds, usersRepository);
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds, usersRepository, chatroomRepository);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message ID");
        System.out.print("-> ");
        System.out.println(messagesRepository.findById(scanner.nextLong()).get());
        ds.close();
    }
}
