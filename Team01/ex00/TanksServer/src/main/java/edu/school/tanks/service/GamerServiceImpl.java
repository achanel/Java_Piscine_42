package edu.school.tanks.service;

import edu.school.tanks.models.Gamer;
import edu.school.tanks.repositories.GamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GamerServiceImpl implements GamerService {
    private final GamerRepository gamerRepository;

    @Autowired
    public GamerServiceImpl(GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }

    @Override
    public void createGamer(int gamerId) {
        gamerRepository.save(gamerId);
    }

    @Override
    public void addShot(int gamerId) {
        gamerRepository.updateShot(gamerId);
    }

    @Override
    public void addHit(int gamerId) {
        gamerRepository.updateHit(gamerId);
    }

    @Override
    public String getStatistics(int num) {
        Gamer info = gamerRepository.getInfo(num);
        Integer shot = info.getShot();
        Integer hit = info.getHit();
        int miss = shot - hit;
        num = num == 1 ? 2 : 1;
        Gamer info2 = gamerRepository.getInfo(num);
        Integer shot2 = info2.getShot();
        Integer hit2 = info2.getHit();
        int miss2 = shot2 - hit2;
        return "stat:" + shot + ":" + hit + ":" + miss + ":" + shot2 + ":" + hit2 + ":" + miss2;
    }
}
