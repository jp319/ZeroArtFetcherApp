package com.jp319.zeroartfetcher.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ZeroArtFetcherConfig {
    private final Properties properties;
    public ZeroArtFetcherConfig() {
        properties = loadPropertiesFromFile();
    }
    public String getApiBaseUrl() {
        return properties.getProperty("api.base.url");
    }
    public String getUserAgentHeader() {
        return properties.getProperty("user.agent.header");
    }
    private Properties loadPropertiesFromFile() {
        try (InputStream inputStream = ZeroArtFetcherConfig.class.getResourceAsStream("/config/config.properties")) {
            if (inputStream != null) {
                Properties properties = new Properties();
                properties.load(inputStream);
                return properties;
            } else {
                System.err.println("config.properties file not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}