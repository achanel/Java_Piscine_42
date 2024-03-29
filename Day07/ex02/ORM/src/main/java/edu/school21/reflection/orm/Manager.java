package edu.school21.reflection.orm;

public interface Manager {
    public void save(Object entity);
    public void update(Object entity);
    public <T> T findById(Long id, Class<T> aClass);
}
