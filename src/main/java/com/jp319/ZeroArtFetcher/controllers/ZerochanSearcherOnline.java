package com.jp319.ZeroArtFetcher.controllers;

import com.google.gson.Gson;
import com.jp319.ZeroArtFetcher.models.ZerochanItem;
import com.jp319.ZeroArtFetcher.models.ZerochanItems;
import com.jp319.ZeroArtFetcher.utils.config.ZeroArtFetcherConfig;
import com.jp319.ZeroArtFetcher.utils.other.ValidateZerochanItems;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ZerochanSearcherOnline {
	private static final String API_BASE_URL = ZeroArtFetcherConfig.getApiBaseUrl();
	private static final String USER_AGENT_HEADER = ZeroArtFetcherConfig.getUserAgentHeader();

	private final Gson gson = new Gson();
	private final HttpClient httpClient = HttpClient.newHttpClient();

	private final String id;
	private final String commaSeperatedTags;
	private final String andSeperatedFilters;

	public ZerochanSearcherOnline(String id) {
		this(id, "none", "none");
	}
	public ZerochanSearcherOnline(String commaSeperatedTags, String andSeperatedFilters) {
		this("none", commaSeperatedTags, andSeperatedFilters);
	}
	private ZerochanSearcherOnline(String id, String commaSeperatedTags, String andSeperatedFilters) {
		this.id = id;
		this.commaSeperatedTags = commaSeperatedTags;
		this.andSeperatedFilters = andSeperatedFilters;
	}
	// Getters
	public String getId() {
		return id;
	}
	public String getCurrentLink() {
		return API_BASE_URL +"/" + commaSeperatedTags + "?json&" + andSeperatedFilters;
	}
	// Custom Functions
	public String getImg() throws IOException, InterruptedException {
		HttpRequest request = buildApiRequestForSingleItem();
		HttpResponse<String> response = sendApiRequest(request);
		ZerochanItem zerochanItem = parseApiResponseForSingleItem(response.body());
		return zerochanItem.getFull();
	}
	//TODO: Make methods for getting multiple image thumbnail
	public String[] getThumbnails() throws IOException, InterruptedException {
		System.out.println("Current Link: "+getCurrentLink());

		String[] thumbnails;

		List<ZerochanItem> items = getItemListFromTagSearch();

		System.out.println("Is a valid item: "+ValidateZerochanItems.isValid(items));

		if (ValidateZerochanItems.isValid(items)) {
			thumbnails = new String[items.size()];
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).getThumbnail() != null) {
					thumbnails[i] = items.get(i).getThumbnail();
				} else {
					thumbnails[i] = items.get(i).getSmall();
				}
			}
		} else {
			thumbnails = new String[]{""}; // Return an array with a single empty string
		}

		return thumbnails;
	}
	public List<ZerochanItem> getItemListFromTagSearch() throws IOException, InterruptedException {
		HttpRequest request = buildApiRequestForMultipleItems(commaSeperatedTags, andSeperatedFilters);
		HttpResponse<String> response = sendApiRequest(request);

		//System.out.println("Response: " + response.body());

		ZerochanItems zerochanItems;

		System.out.println("Is single Item: "+ValidateZerochanItems.isSingleItem(response.body()));

		if (ValidateZerochanItems.isSingleItem(response.body())) {
			zerochanItems = new ZerochanItems();
			ZerochanItem item = parseApiResponseForSingleItem(response.body());
			zerochanItems.setItems(new ArrayList<>());
			zerochanItems.getItems().add(item);
		} else {
			zerochanItems = parseApiResponseForMultipleItems(
					new ZerochanResponseParser()
							.getParsedJson(response.body(), commaSeperatedTags)
			);
		}

		return zerochanItems.getItems();
	}
	// HTTP Methods
	private HttpRequest buildApiRequestForSingleItem() {
		return HttpRequest.newBuilder()
				.GET()
				.header("User-Agent", USER_AGENT_HEADER)
				.header("accept", "application/json")
				.uri(URI.create(API_BASE_URL +"/" + getId() + "?json"))
				.build();
	}
	private HttpRequest buildApiRequestForMultipleItems(String commaSeperatedTags, String andSeperatedFilters) {
		return HttpRequest.newBuilder()
				.GET()
				.header("User-Agent", USER_AGENT_HEADER)
				.header("accept", "application/json")
				.uri(URI.create(API_BASE_URL +"/" + commaSeperatedTags + "?json&" + andSeperatedFilters))
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
	// Public Request method
	public String getResponse () throws IOException, InterruptedException {
		HttpRequest request = buildApiRequestForMultipleItems(commaSeperatedTags, andSeperatedFilters);
		HttpResponse<String> response = sendApiRequest(request);
		return response.body();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.header("User-Agent", USER_AGENT_HEADER)
				.header("accept", "application/json")
				.uri(URI.create(API_BASE_URL +"/" + "Ecchi" + "?json&" + "p=1"))
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient()
				.send(
						request,
						HttpResponse
								.BodyHandlers
								.ofString()
				);
		System.out.println(response.body());
	}
}