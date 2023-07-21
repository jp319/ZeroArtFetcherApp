package com.jp319.zeroartfetcher.core;

import com.google.gson.Gson;
import com.jp319.zeroartfetcher.api.ZerochanResponseParser;
import com.jp319.zeroartfetcher.config.ZeroArtFetcherConfig;
import com.jp319.zeroartfetcher.core.model.ZerochanItem;
import com.jp319.zeroartfetcher.core.model.ZerochanItems;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ZerochanSearcher {
    private final ZeroArtFetcherConfig zeroArtFetcherConfig = new ZeroArtFetcherConfig();
    private final String apiBaseUrl;
    private final String userAgentHeader;
    private final HttpClient httpClient;
    private final Gson gson;
    private final String id;
    private final String commaSeperatedTags;
    private final String andSeperatedFilters;
    public ZerochanSearcher() {
        this.id = "none";
        this.commaSeperatedTags = "none";
        this.andSeperatedFilters = "none";
        apiBaseUrl = loadApiBaseUrlFromConfig();
        userAgentHeader = loadUserAgentHeaderFromConfig();
        httpClient = HttpClient.newHttpClient();
        gson = new Gson();
    }
    public ZerochanSearcher(String id) {
        this.id = id;
        this.commaSeperatedTags = "none";
        this.andSeperatedFilters = "none";
        apiBaseUrl = loadApiBaseUrlFromConfig();
        userAgentHeader = loadUserAgentHeaderFromConfig();
        httpClient = HttpClient.newHttpClient();
        gson = new Gson();
    }
    public ZerochanSearcher(String commaSeperatedTags, String andSeperatedFilters) {
        this.id = "none";
        this.commaSeperatedTags = commaSeperatedTags;
        this.andSeperatedFilters = andSeperatedFilters;
        apiBaseUrl = loadApiBaseUrlFromConfig();
        userAgentHeader = loadUserAgentHeaderFromConfig();
        httpClient = HttpClient.newHttpClient();
        gson = new Gson();
    }
    // Getters
    public String getId() {
        return id;
    }
    public String getCurrentLink() {
        return apiBaseUrl +"/" + commaSeperatedTags + "?json&" + andSeperatedFilters;
    }
    // Custom Functions
    public String getImg() throws IOException, InterruptedException {
        HttpRequest request = buildApiRequestForSingleItem();
        HttpResponse<String> response = sendApiRequest(request);
        ZerochanItem zerochanItem = parseApiResponseForSingleItem(response.body());
        return zerochanItem.getFull();
    }
    //TODO: Make methods for getting multiple image thumbnail
    public List<ZerochanItem> getThumbnails() throws IOException, InterruptedException {
        HttpRequest request = buildApiRequestForMultipleItems(commaSeperatedTags, andSeperatedFilters);
        HttpResponse<String> response = sendApiRequest(request);
        ZerochanItems zerochanItems = parseApiResponseForMultipleItems(new ZerochanResponseParser().getParsedJson(response.body()));
        return zerochanItems.getItems();
    }
    // HTTP Methods
    private HttpRequest buildApiRequestForSingleItem() {
        return HttpRequest.newBuilder()
                .GET()
                .header("User-Agent", userAgentHeader)
                .header("accept", "application/json")
                .uri(URI.create(apiBaseUrl +"/" + getId() + "?json"))
                .build();
    }
    private HttpRequest buildApiRequestForMultipleItems(String commaSeperatedTags, String andSeperatedFilters) {
        return HttpRequest.newBuilder()
                .GET()
                .header("User-Agent", userAgentHeader)
                .header("accept", "application/json")
                .uri(URI.create(apiBaseUrl +"/" + commaSeperatedTags + "?json&" + andSeperatedFilters))
                .build();
    }
    private HttpResponse<String> sendApiRequest(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
    private ZerochanItem parseApiResponseForSingleItem(String responseBody) {
        return gson.fromJson(responseBody, ZerochanItem.class);
    }
    private ZerochanItems parseApiResponseForMultipleItems(String responseBody) {
        return gson.fromJson(responseBody, ZerochanItems.class);
    }
    // Config Properties Methods
    private String loadApiBaseUrlFromConfig() {
        return zeroArtFetcherConfig.getApiBaseUrl();
    }
    private String loadUserAgentHeaderFromConfig() {
        return zeroArtFetcherConfig.getUserAgentHeader();
    }
}