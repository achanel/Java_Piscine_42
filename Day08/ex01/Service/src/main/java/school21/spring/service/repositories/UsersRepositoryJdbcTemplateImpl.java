package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import school21.spring.service.models.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    public JdbcTemplate template;

    private final String SQL_FIND_BY_ID = "SELECT * FROM users WHERE id=?;";
    private final String SQL_SAVE = "INSERT INTO users (email) VALUES(?);";
    private final String SQL_UPDATE = "UPDATE users SET email = ? WHERE id = ?;";
    private final String SQL_DELETE = "DELETE FROM users WHERE id=?;";
    private final String SQL_FIND_ALL = "SELECT * FROM users;";
    private final String SQL_FIND_BY_EMAIL = "SELECT * FROM users WHERE id=?;";

    public UsersRepositoryJdbcTemplateImpl(JDBCGenerator dataSource) {
        this.template = new JdbcTemplate(dataSource.manager);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(template.query(SQL_FIND_BY_ID, new Object[]{id}, (rs, rowNum) -> new User(rs.getLong(1), rs.getString(2)))
                .stream().findAny().orElse(null));
    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_FIND_ALL, (rs, rowNum) -> new User(rs.getLong(1), rs.getString(2)));
    }

    @Override
    public void save(User entity) {
        template.update(SQL_SAVE, entity.getEmail());
    }

    @Override
    public void update(User entity) {
        template.update(SQL_UPDATE, entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        template.update(SQL_DELETE, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(template.query(SQL_FIND_BY_EMAIL, new Object[]{email}, (rs, rowNum) -> new User(rs.getLong(1), rs.getString(2)))
                .stream().findAny().orElse(null));
    }

    public static class JDBCGenerator {
        DriverManagerDataSource manager;

        public JDBCGenerator() {
            manager = new DriverManagerDataSource();
            Properties properties = new Properties();
            try {
                properties.load(UsersRepositoryJdbcTemplateImpl.class.getClassLoader().getResourceAsStream("db.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            manager.setDriverClassName(properties.getProperty("db.driver.name"));
            manager.setUrl(properties.getProperty("db.url"));
            manager.setUsername(properties.getProperty("db.user"));
            manager.setPassword(properties.getProperty("db.password"));
        }
    }
}
