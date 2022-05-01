package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component()
public class UsersRepositoryJdbcTemplateImpl extends JdbcTemplate implements UsersRepository {
    private DataSource dataSource;
    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("hikariDataSource")DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        return query("SELECT * FROM users WHERE id =?", new Object[]{id}, new UserMapper())
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return query("SELECT * FROM users", new UserMapper());
    }

    @Override
    public void save(User entity) {
        update("INSERT INTO users(email, password) VALUES(?, ?)", entity.getEmail(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        update("UPDATE users SET email=?, password=? WHERE id=?", entity.getEmail(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        update("DELETE FROM users WHERE id=?", id);

    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = query("SELECT * FROM users WHERE email=?", new Object[]{email}, new UserMapper())
                .stream()
                .findAny()
                .orElse(null);
        return Optional.of(user);
    }

    private class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
    }

}
