package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

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
    public boolean singUp(String username, String password) throws SQLException {
        if (username.isEmpty())
            return false;
        if (!usersRepository.findByUsername(username).isPresent())
        {
            usersRepository.save(new User(username, passwordEncoder.encode(password)));
            return true;
        }
        else return false;
    }
}
