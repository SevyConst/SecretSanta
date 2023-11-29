package org.example;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public record AppProperties(String telegramBotToken) {
}
