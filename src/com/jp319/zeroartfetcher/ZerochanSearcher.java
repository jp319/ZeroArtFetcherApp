package com.jp319.zeroartfetcher;

import com.google.gson.Gson;
import com.jp319.zeroartfetcher.config.ZeroArtFetcherConfig;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ZerochanSearcher {
    private final ZeroArtFetcherConfig zeroArtFetcherConfig = new ZeroArtFetcherConfig();
    private final String apiBaseUrl;
    private final String userAgentHeader;
    private final HttpClient httpClient;
    private final Gson gson;
    private final String id;
    public ZerochanSearcher(String id) {
        this.id = id;
        apiBaseUrl = loadApiBaseUrlFromConfig();
        userAgentHeader = loadUserAgentHeaderFromConfig();
        httpClient = HttpClient.newHttpClient();
        gson = new Gson();
    }
    // Getters
    public String getId() {
        return id;
    }
    // Custom Functions
    public String getImgLink() throws IOException, InterruptedException {
        HttpRequest request = buildApiRequest();
        HttpResponse<String> response = sendApiRequest(request);
        ZerochanItem zerochanItem = parseApiResponse(response.body());
        return zerochanItem.getFull();
    }
    // HTTP Methods
    private HttpRequest buildApiRequest() {
        return HttpRequest.newBuilder()
                .GET()
                .header("User-Agent", userAgentHeader)
                .header("accept", "application/json")
                .uri(URI.create(apiBaseUrl +"/" + getId() + "?json"))
                .build();
    }
    private HttpResponse<String> sendApiRequest(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
    private ZerochanItem parseApiResponse(String responseBody) {
        return gson.fromJson(responseBody, ZerochanItem.class);
    }
    // Config Properties Methods
    private String loadApiBaseUrlFromConfig() {
        return zeroArtFetcherConfig.getApiBaseUrl();
    }
    private String loadUserAgentHeaderFromConfig() {
        return zeroArtFetcherConfig.getUserAgentHeader();
    }
}