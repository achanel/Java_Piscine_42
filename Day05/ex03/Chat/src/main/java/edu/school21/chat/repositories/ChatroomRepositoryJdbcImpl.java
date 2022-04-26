package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class ChatroomRepositoryJdbcImpl implements ChatroomRepository {

    private final HikariDataSource dataSource;
    private final UserRepositoryJdbcImpl userRepository;

    public ChatroomRepositoryJdbcImpl(HikariDataSource dataSource, UserRepositoryJdbcImpl userRepository) {
        this.dataSource = dataSource;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Chatroom> findById(Long id) throws SQLException {
        Optional<Chatroom> optionalChatroom;
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM chat.rooms  WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();

        optionalChatroom = Optional.of(new Chatroom(id, resultSet.getString("name"),
                userRepository.findById(resultSet.getLong("owner")).orElse(null),
                null));

        return optionalChatroom;
    }
}
