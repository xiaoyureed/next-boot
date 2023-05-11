package io.github.xiaoyureed.nextbootslim.core.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfiguration {

    /**
     *  jdbc:postgresql://localhost:5432/match_engine?user=postgres&password=postgres
     */
    @Bean
    @FlywayDataSource
    @ConditionalOnProperty(prefix = "spring.datasource", value = "url")
    public DataSource dataSource(@Value("spring.datasource.url") String postgresUrl) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(postgresUrl);
        config.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(config);
    }

    @Bean
    @FlywayDataSource
    @ConditionalOnProperty(prefix = "spring.datasource", value = "url", matchIfMissing = true)
    public DataSource dataSourceH2() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:file:~/nextboot");
        config.setDriverClassName("org.h2.Driver");
        config.setUsername("sa");

        return new HikariDataSource(config);
    }
}
