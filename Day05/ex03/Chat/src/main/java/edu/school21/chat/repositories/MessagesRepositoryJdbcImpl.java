package edu.school21.chat.repositories;

import edu.school21.chat.app.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{
    private final DataSource dataSource;
    private final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?;";
    private final String SQL_FIND_ROOM_BY_ID = "SELECT * FROM chatrooms WHERE id=?;";
    private final String SQL_SAVE_MESSAGE = "INSERT INTO messages (author, room, text, date_time) VALUES(?,?,?,?);";
    private final String SQL_UPDATE_MESSAGE = "UPDATE messages SET author=?, room=?, text=?, date_time=? WHERE id=?;";


    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id){
        String SQL_FIND_MESSAGE_BY_ID = "SELECT * FROM chat.messages WHERE id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_MESSAGE_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next())
                return Optional.empty();
            Long userId = resultSet.getLong(2);
            Long chatroomId = resultSet.getLong(3);
            User user = findUser(userId);
            Chatroom chatroom = findChatroom(chatroomId);
            return Optional.of(new Message(resultSet.getLong(1), user, chatroom,
                    resultSet.getString(4), resultSet.getTimestamp("date_time").toLocalDateTime()));
        } catch (SQLException throwables) {
            throwables.getMessage();
        }
        return Optional.empty();
    }

    private User findUser(Long id) throws SQLException {
        String uQuery = "SELECT * FROM chat.messages WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(uQuery);
            if (!rs.next()) {
                return null;
            }
            return new User(id, rs.getString(2), rs.getString(3));
        }
    }

    private Chatroom findChatroom(Long id) throws SQLException {
        String cQuery = "SELECT * FROM chat.users_chatrooms WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(cQuery);
            if (!rs.next()) {
                return null;
            }
            return new Chatroom(id, rs.getString(2));
        }
    }
    @Override
    public void save(Message message) throws NotSavedSubEntityException {
        if (message.getRoom() == null) {
            throw new NotSavedSubEntityException("Room ID: is null");
        }
        if (message.getAuthor() == null) {
            throw new NotSavedSubEntityException("Author ID: is null");
        }
        if (!findRoomById(message.getRoom().getId()).isPresent()) {
            throw new NotSavedSubEntityException("Room ID: " + message.getRoom().getId() + " not found");
        }
        if (!findUserById(message.getAuthor().getId()).isPresent()) {
            throw new NotSavedSubEntityException("Author ID: " + message.getAuthor().getId() + " not found");
        }

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SAVE_MESSAGE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getRoom().getId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            Long i = null;
            if (resultSet.next()) {
                i = resultSet.getLong(1);
            }
            if (i != null) {
                message.setId(i);
            } else {
                throw new NotSavedSubEntityException("Generated ID is null");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message) {
        if (message == null){
            throw new NotSavedSubEntityException("Message is null");
        }
        if (message.getId() == null){
            throw new NotSavedSubEntityException("Message ID: is null");
        }
        if (message.getRoom() == null) {
            throw new NotSavedSubEntityException("Room ID: is null");
        }
        if (message.getAuthor() == null) {
            throw new NotSavedSubEntityException("Author ID: is null");
        }
        if (!findById(message.getId()).isPresent()) {
            throw new NotSavedSubEntityException("Message ID: " + message.getId() + " not found");
        }
        if (!findRoomById(message.getRoom().getId()).isPresent()) {
            throw new NotSavedSubEntityException("Room ID: " + message.getRoom().getId() + " not found");
        }
        if (!findUserById(message.getAuthor().getId()).isPresent()) {
            throw new NotSavedSubEntityException("Author ID: " + message.getAuthor().getId() + " not found");
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_MESSAGE)) {
            statement.setLong(5, message.getId());
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getRoom().getId());

            if (message.getText() == null) {
                statement.setNull(3, Types.VARCHAR);
            } else {
                statement.setString(3, message.getText());
            }
            if (message.getDateTime() == null) {
                statement.setNull(4, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));
            }
            statement.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<User> findUserById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            User findUser = null;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long userId = resultSet.getLong("id");
                String userLogin = resultSet.getString("login");
                String userPassword = resultSet.getString("password");
                findUser = new User(userId, userLogin, userPassword, null, null);
            }
            resultSet.close();
            if (findUser != null) {
                return Optional.of(findUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Optional<Chatroom> findRoomById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ROOM_BY_ID)) {
            statement.setLong(1, id);
            Chatroom findRoom = null;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long chatroomId = resultSet.getLong("id");
                String chatroomName = resultSet.getString("name");
                findRoom = new Chatroom(chatroomId, chatroomName, null, null);
            }
            resultSet.close();
            if (findRoom != null) {
                return Optional.of(findRoom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
