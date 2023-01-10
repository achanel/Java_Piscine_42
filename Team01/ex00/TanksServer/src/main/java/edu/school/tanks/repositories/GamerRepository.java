package edu.school.tanks.repositories;

import edu.school.tanks.models.Gamer;

public interface GamerRepository extends CrudRepository<Gamer> {
    Gamer getInfo(int num);
}
