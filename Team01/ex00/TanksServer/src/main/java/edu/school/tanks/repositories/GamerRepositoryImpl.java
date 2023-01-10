package edu.school.tanks.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import edu.school.tanks.models.Gamer;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Component
public class GamerRepositoryImpl implements GamerRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GamerRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        initDb();
    }

    private void initDb() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS gamers;");
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS gamers (\n" +
                "gamerId int,\n" +
                "shot int,\n" +
                "hit int );");
    }

    @Override
    public void save(Integer clientId) {
        String SQL_INSERT_GAMER = "INSERT INTO gamers (gamerId, shot, hit) VALUES (?, ?, ?)";
        int i = jdbcTemplate.update(SQL_INSERT_GAMER, clientId, 0, 0);

        if (i == 0) {
            System.err.println("Client " + clientId + " wasn't saved.");
        }
    }

    @Override
    public void updateHit(Integer clientId) {
        String SQL_UPDATE_GAMER = "UPDATE gamers SET hit = hit + 1 WHERE gamerId = ?";
        int i = jdbcTemplate.update(SQL_UPDATE_GAMER, clientId);

        if (i == 0) {
            System.err.println("Hit wasn't update for Client " + clientId + ".");
        }
    }

    @Override
    public void updateShot(Integer gamerId) {
        String SQL_UPDATE_SHOTS = "UPDATE gamers SET shot = shot + 1 WHERE gamerId = ?";
        int i = jdbcTemplate.update(SQL_UPDATE_SHOTS, gamerId);

        if (i == 0) {
            System.err.println("Shot wasn't update for Client " + gamerId + ".");
        }
    }

    @Override
    public Gamer getInfo(int num) {
        String SQL_GET_GAMER_BY_ID = "SELECT * FROM gamers WHERE gamerId = ?";
        return jdbcTemplate.query(SQL_GET_GAMER_BY_ID,
                new Object[]{num},
                new BeanPropertyRowMapper<>(Gamer.class)).stream().findAny().orElse(null);
    }
}
