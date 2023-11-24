package org.example;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public record AppProperties(String urlForDb, String telegramBotToken) {
//    private String urlForDb;
//    private String telegramBotToken;
//
//    public String getUrlForDb() {
//        return urlForDb;
//    }
//
//    public void setUrlForDb(String urlForDb) {
//        this.urlForDb = urlForDb;
//    }
//
//    public String getTelegramBotToken() {
//        return telegramBotToken;
//    }
//
//    public void setTelegramBotToken(String telegramBotToken) {
//        this.telegramBotToken = telegramBotToken;
//    }
}
