package school21.spring.service.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;


import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component()
public class UsersRepositoryJdbcImpl implements UsersRepository{
    private DataSource dataSource;

    @Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("dataSourceSpring")DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        PreparedStatement preparedStatement =
                null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement("SELECT * FROM users WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
            user.setEmail(resultSet.getString("password"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> findAll() {
        final String SQL = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User user) {
        PreparedStatement preparedStatement =
                null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement("INSERT INTO users (email, password) VALUES (?, ?)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();

            preparedStatement = dataSource.getConnection().prepareStatement("SELECT id FROM users WHERE email=?");
            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getLong("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement preparedStatement =
                    dataSource.getConnection().prepareStatement("UPDATE users SET email=?, password=? WHERE id=?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setLong(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement =
                    dataSource.getConnection().prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;

        PreparedStatement preparedStatement =
                null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement("SELECT * FROM users WHERE email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return Optional.of(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.of(user);
    }
}
