package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{

    private final HikariDataSource dataSource;
    UserRepositoryJdbcImpl userRepository;
    ChatroomRepositoryJdbcImpl chatroomRepository;

    public MessagesRepositoryJdbcImpl(HikariDataSource dataSource, UserRepositoryJdbcImpl userRepository, ChatroomRepositoryJdbcImpl chatroomRepository) {
        this.dataSource = dataSource;
        this.userRepository = userRepository;
        this.chatroomRepository = chatroomRepository;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Optional<Message> optionalMessage;
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM chat.messages  WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();

        optionalMessage = Optional.of(new Message(id,
                userRepository.findById(resultSet.getLong("author")).orElse(null),
                chatroomRepository.findById(resultSet.getLong("room")).orElse(null),
                resultSet.getString("text"), resultSet.getTimestamp("timestamp").toLocalDateTime()));

        return optionalMessage;
    }
}
