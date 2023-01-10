package edu.school.tanks.repositories;

public interface CrudRepository<T> {
    void save(Integer gamerId);
    void updateShot(Integer gamerId);
    void updateHit(Integer gamerId);

}
