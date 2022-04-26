package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.app.NotSavedSubEntityException;
import edu.school21.chat.models.Message;

import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
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

    @Override
    public boolean save(Message message) throws NotSavedSubEntityException, SQLException {
        String query = "INSERT into chat.messages (author, room, text, timestamp)" +
                " VALUES (?, ?, ?, ?) RETURNING *";

        Connection connection = dataSource.getConnection();
        try {
            if (userRepository.findById(message.getAuthor().getId()).isPresent()
                    && chatroomRepository.findById(message.getRoom().getId()).isPresent()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, message.getAuthor().getId());
                statement.setLong(2, message.getRoom().getId());
                statement.setString(3, message.getText());
                statement.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                message.setId(resultSet.getLong("id"));
            } else {
                throw new NotSavedSubEntityException("Cant save message!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
