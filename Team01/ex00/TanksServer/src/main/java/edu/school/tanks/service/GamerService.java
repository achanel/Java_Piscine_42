package edu.school.tanks.service;

public interface GamerService {
    void createGamer(int gamerId);
    void addShot(int gamerId);
    void addHit(int gamerId);
    String getStatistics(int num);
}
