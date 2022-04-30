package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;

@Component("usersService")
public class UsersServiceImpl implements UsersService{
    private PasswordEncoder passwordEncoder;
    private UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean signUp(String username, String password) throws SQLException {
        Optional<User> optionalUser = usersRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return false;
        }
        usersRepository.save(new User(username, passwordEncoder.encode(password)));
        return true;
    }

    @Override
    public boolean signIn(String username, String password) throws SQLException {
        Optional<User> optionalUser = usersRepository.findByUsername(username);
        if (!optionalUser.isPresent())
            return false;
        if (!passwordEncoder.matches(password, optionalUser.get().getPassword()))
            return false;
        return true;
    }

    @Override
    public void saveMessage(String message) {
        usersRepository.saveMessage(message);
    }
}
