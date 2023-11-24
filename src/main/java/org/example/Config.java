package org.example;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class Config {

    @Bean
    public TelegramBot telegramBot(AppProperties appProperties) {
        return new TelegramBot(appProperties.telegramBotToken());
    }
}
