package com.jp319.ZeroArtFetcher.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ZerochanResponseParser {
    private final Gson gson = new Gson();
    private String extractJson(String input) {
        int startIndex = input.indexOf("\"items\": [");
        if (startIndex >= 0) {
            int endIndex = input.lastIndexOf("]");
            if (endIndex >= startIndex) {
                return input.substring(startIndex, endIndex + 1);
            }
        }
        return null;
    }
    private JsonObject toJsonObject(String json) {
        return gson.fromJson("{" + json + "}", JsonObject.class);
    }
    public String getParsedJson(String input) {
        return toJsonObject(extractJson(input)).toString();
    }
}