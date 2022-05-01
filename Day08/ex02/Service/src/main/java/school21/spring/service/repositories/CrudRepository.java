package school21.spring.service.repositories;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CrudRepository<T> {
    T findById(Long id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(Long id);
}
