package com.jp319.ZeroArtFetcher.utils.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ZeroArtFetcherConfig {
	private static final Properties properties;
	static {
		properties = loadPropertiesFromFile();
	}
	// Image Properties
	public String getSavedImageDirectory() {
		if (properties == null) {
			throw new IllegalStateException("Properties not loaded. Ensure the config.properties file is available.");
		}
		return properties.getProperty("saved.image.directory");
	}
	// Request Headers
	public static String getApiBaseUrl() {
		return properties.getProperty("api.base.url");
	}
	public static String getUserAgentHeader() {
		return properties.getProperty("user.agent.header");
	}
	public static String getAcceptHeader() {
		return properties.getProperty("accept.header");
	}
	public static String getAcceptLanguageHeader() {
		return properties.getProperty("accept.language.header");
	}
	public static String getAcceptEncodingHeader() {
		return properties.getProperty("accept.encoding.header");
	}
	public static String getDntHeader() {
		return properties.getProperty("dnt.header");
	}
	public static String getConnectionHeader() {
		return properties.getProperty("connection.header");
	}
	public static String getUpgradeInsecureRequestsHeader() {
		return properties.getProperty("upgrade.insecure.requests.header");
	}
	public static String getSecFetchDestHeader() {
		return properties.getProperty("sec.fetch.dest.header");
	}
	public static String getSecFetchModeHeader() {
		return properties.getProperty("sec.fetch.mode.header");
	}
	public static String getSecFetchSiteHeader() {
		return properties.getProperty("sec.fetch.site.header");
	}
	public static String getSecFetchUserHeader() {
		return properties.getProperty("sec.fetch.user.header");
	}
	public static String getPragmaHeader() {
		return properties.getProperty("pragma.header");
	}
	public static String getCacheControlHeader() {
		return properties.getProperty("cache.control.header");
	}
	public static String getCookieHeader() {
		return properties.getProperty("cookie.header");
	}
	// Load Properties
	private static Properties loadPropertiesFromFile() {
		try (InputStream inputStream = ZeroArtFetcherConfig.class.getResourceAsStream("/data/config.properties")) {
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