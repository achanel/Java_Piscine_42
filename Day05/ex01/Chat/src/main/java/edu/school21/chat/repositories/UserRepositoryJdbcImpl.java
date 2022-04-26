package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final HikariDataSource dataSource;

    public UserRepositoryJdbcImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        Optional<User> optionalUser;
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM chat.users  WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();

        optionalUser = Optional.of(new User(id, resultSet.getString("login"), resultSet.getString("password"), null, null));

        return optionalUser;
    }
}
