package edu.school.tanks.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:db.properties")
public class ApplicationConfig {
    @Value("${db.url}")
    private String url;

    @Value("${db.user}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.driver.name}")
    private String driver;

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariDataSource ret = new HikariDataSource();
        ret.setDriverClassName(driver);
        ret.setUsername(username);
        ret.setPassword(password);
        ret.setJdbcUrl(url);
        return ret;
    }
}
