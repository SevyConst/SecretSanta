package org.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class Config {

    @Bean(name="ds")
    @ConfigurationProperties("spring.datasource")
    public DataSource ds() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public Db db(DataSource ds) {
        return new Db(ds);
    }

    @Bean
    public TelegramBot telegramBot(AppProperties appProperties, Db db) {
        return new TelegramBot(appProperties.telegramBotToken(), db);
    }
}
