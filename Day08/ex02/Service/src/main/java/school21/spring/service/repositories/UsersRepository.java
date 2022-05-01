package school21.spring.service.repositories;

import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import java.util.List;
import java.util.Optional;

@Component
public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByEmail(String email);
}
