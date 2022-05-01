package school21.spring.service.services;

import org.springframework.stereotype.Component;
import school21.spring.service.repositories.UsersRepository;

@Component
public interface UsersService {
    String signUp(String email);
}
