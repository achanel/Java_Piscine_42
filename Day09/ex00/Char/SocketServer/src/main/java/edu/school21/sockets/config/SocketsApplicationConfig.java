package edu.school21.sockets.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan ("edu.school21.sockets")
@PropertySource("classpath:db.properties")
public class SocketsApplicationConfig {
    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.user}")
    private String DB_USER;

    @Value("${db.password}")
    private String DB_PASSWORD;

    @Value("${db.driver.name}")
    private String DB_DRIVER;

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(DB_URL);
        hikariConfig.setUsername(DB_USER);
        hikariConfig.setPassword(DB_PASSWORD);
        hikariConfig.setDriverClassName(DB_DRIVER);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
