package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.print("Enter a message ID\n-> ");
        Scanner scanner = new Scanner(System.in);
        try {
            Long l;
            while (!scanner.hasNextLong()){
                System.out.println("Error, Enter a message ID:");
                scanner.next();
            }
            l = scanner.nextLong();
            HikariDataSource hikariDataSource = new HikariDataSource();
            hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
            hikariDataSource.setUsername("postgres");
            hikariDataSource.setPassword("postgres");
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(hikariDataSource);
            Optional<Message> result = messagesRepository.findById(l);
            if (result.isPresent()) {
                System.out.println(result.get());
            } else {
                System.out.println("Message with ID: " + l + " not found");
            }
            hikariDataSource.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        scanner.close();
    }
}
