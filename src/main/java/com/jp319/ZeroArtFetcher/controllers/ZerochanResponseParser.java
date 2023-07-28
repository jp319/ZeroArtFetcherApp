package com.jp319.ZeroArtFetcher.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.jp319.ZeroArtFetcher.utils.other.FilterManager;
import com.jp319.ZeroArtFetcher.utils.other.ValidateZerochanItems;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZerochanResponseParser {
	private String extractJson(String input, String tags) {
		if (input != null && !input.isEmpty()) {
			int startIndex = input.indexOf("\"items\":");
			if (startIndex >= 0) {
				return "{" + input.substring(startIndex);
			} else {
				return ""; // Return an empty string if "items" is not found
			}
		} else {
			try {
				String newTag = ValidateZerochanItems.getValidJson(tags);
				System.out.println("New Tag: " + newTag);
				ZerochanSearcherOnline zerochanSearcherOnline =
						new ZerochanSearcherOnline(newTag, FilterManager.getConcatenatedFilters());
				
				int startIndex = zerochanSearcherOnline.getResponse().indexOf("\"items\":");
				if (startIndex >= 0) {
					return "{" + zerochanSearcherOnline.getResponse().substring(startIndex);
				} else {
					return ""; // Return an empty string if "items" is not found
				}
			} catch (IOException | InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
	private JsonObject toJsonObject(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, JsonObject.class);
	}
	public String getParsedJson(String input, String tags) {
		try {
			return toJsonObject(extractJson(input, tags)).toString();
		} catch (JsonSyntaxException e) {
			// If there's a JSON syntax exception, log the error (optional) and return an empty JSON string
			e.printStackTrace(); // Optionally, log the exception for debugging purposes
			return "{}"; // Return an empty JSON object
		}
	}

}