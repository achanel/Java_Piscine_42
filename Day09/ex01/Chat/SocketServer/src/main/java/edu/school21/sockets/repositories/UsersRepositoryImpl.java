package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl extends JdbcTemplate implements UsersRepository {
    public DataSource dataSource;

    private final String SQL_FIND_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String SQL_SAVE = "INSERT INTO users (username, password) VALUES(?, ?);";
    private final String SQL_UPDATE = "UPDATE users SET username = ?, password = ? WHERE id = ?;";
    private final String SQL_DELETE = "DELETE FROM users WHERE id=";
    private final String SQL_FIND_ALL = "SELECT * FROM users;";
    private final String SQL_FIND_BY_NAME = "SELECT * FROM users WHERE username=?;";
    private final String SQL_SAVE_MSG = "INSERT INTO messages(message) VALUES (?);";

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        return query(SQL_FIND_BY_ID, new UserMapper(), new Object[]{id})
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return query(SQL_FIND_ALL, new UserMapper());
    }

    @Override
    public void save(User entity) {
        try {
            update(SQL_SAVE, entity.getUsername(), entity.getPassword());
        } catch (DuplicateKeyException e)
        {
            System.out.println("User already exist or bad input");
        }
    }

    @Override
    public void update(User entity) {
        update(SQL_UPDATE, entity.getUsername(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        update(SQL_DELETE, id);
    }

    @Override
    public Optional<User> findByUsername(String name) {
        return Optional.ofNullable(query(SQL_FIND_BY_NAME, new Object[]{name}, (rs, rowNum) -> new User(rs.getString(2), rs.getString(3)))
                .stream().findAny().orElse(null));
    }

    @Override
    public void saveMessage(String message) {
        update(SQL_SAVE_MSG, message);
    }


    private class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
}
