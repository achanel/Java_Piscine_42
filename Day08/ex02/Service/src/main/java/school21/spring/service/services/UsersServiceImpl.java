package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import java.util.UUID;

@Component
public class UsersServiceImpl implements UsersService{
    private UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcImpl") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String email) {
       String password = UUID.randomUUID().toString();
       if (email == null || email.equals("") || email.length() < 6) {
           System.out.println("Invalid email");
           return null;
       }
       usersRepository.save(new User(null, email, password));
       return password;
    }
}
