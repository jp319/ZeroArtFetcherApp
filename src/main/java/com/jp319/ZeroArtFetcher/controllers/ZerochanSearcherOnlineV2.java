package com.jp319.ZeroArtFetcher.controllers;

import com.google.gson.Gson;
import com.jp319.ZeroArtFetcher.models.ZerochanItem;
import com.jp319.ZeroArtFetcher.models.ZerochanItems;
import com.jp319.ZeroArtFetcher.utils.config.ZeroArtFetcherConfig;
import com.jp319.ZeroArtFetcher.utils.other.ValidateZerochanItems;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZerochanSearcherOnlineV2 implements Closeable {
	// Constants
	private static final String API_BASE_URL = ZeroArtFetcherConfig.getApiBaseUrl();
	private static final String USER_AGENT_HEADER = ZeroArtFetcherConfig.getUserAgentHeader();
	private static final String ACCEPT_HEADER = ZeroArtFetcherConfig.getAcceptHeader();
	private static final String ACCEPT_LANGUAGE_HEADER = ZeroArtFetcherConfig.getAcceptLanguageHeader();
	private static final String ACCEPT_ENCODING_HEADER = ZeroArtFetcherConfig.getAcceptEncodingHeader();
	private static final String DNT_HEADER = ZeroArtFetcherConfig.getDntHeader();
	private static final String CONNECTION_HEADER = ZeroArtFetcherConfig.getConnectionHeader();
	private static final String UPGRADE_INSECURE_REQUESTS_HEADER = ZeroArtFetcherConfig.getUpgradeInsecureRequestsHeader();
	private static final String SEC_FETCH_DEST_HEADER = ZeroArtFetcherConfig.getSecFetchDestHeader();
	private static final String SEC_FETCH_MODE_HEADER = ZeroArtFetcherConfig.getSecFetchModeHeader();
	private static final String SEC_FETCH_SITE_HEADER = ZeroArtFetcherConfig.getSecFetchSiteHeader();
	private static final String SEC_FETCH_USER_HEADER = ZeroArtFetcherConfig.getSecFetchUserHeader();
	private static final String PRAGMA_HEADER = ZeroArtFetcherConfig.getPragmaHeader();
	private static final String CACHE_CONTROL_HEADER = ZeroArtFetcherConfig.getCacheControlHeader();
	private static final String COOKIE_HEADER = ZeroArtFetcherConfig.getCookieHeader();
	// Variables
	private final Gson gson = new Gson();
	private final BasicCookieStore cookieStore = new BasicCookieStore();
	private final CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	private final HttpClientContext context = HttpClientContext.create();
	private final String id;
	private String commaSeperatedTags;
	private final String andSeperatedFilters;
	public ZerochanSearcherOnlineV2(String id) {
		this(id, "none", "none");
	}
	public ZerochanSearcherOnlineV2(String commaSeperatedTags, String andSeperatedFilters) {
		this("none", commaSeperatedTags, andSeperatedFilters);
	}
	// Private Constructor
	private ZerochanSearcherOnlineV2(String id, String commaSeperatedTags, String andSeperatedFilters) {
		this.id = id;
		this.commaSeperatedTags = commaSeperatedTags;
		this.andSeperatedFilters = andSeperatedFilters;
		setContext();
	}
	// Public Method used for GUI
	public String getCurrentLink() {
		return API_BASE_URL + "/" + getCommaSeperatedTags() + "?json&" + getAndSeperatedFilters();
	}
	public String[] getThumbnails() throws IOException {
		System.out.println("Current Link: " + getCurrentLink());
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
	// Public Method used for ZerochanResponseParser
	public List<ZerochanItem> getItemListFromTagSearch() throws IOException {
		String response = getJsonContent();
		System.out.println("Response: " + response);
		System.out.println("Is Valid Json: " + ValidateZerochanItems.isValidJson(response));
		if (response.contains("Page number too high, please filter results.")) {
			return null;
		} else {
			if (!ValidateZerochanItems.isValidJson(response)) {
				if (ValidateZerochanItems.isSingleItem(response)) {
					commaSeperatedTags = ValidateZerochanItems.scrapeJsonV2(
							getCurrentLink(), getHttpGet(), httpClient, context
					);
					response = getJsonContent();
				}
			}
			System.out.println("New Link: " + getCurrentLink());
			
			ZerochanItems zerochanItems;
			
			System.out.println("Is single Item: "+ ValidateZerochanItems.isSingleItem(response));
			
			if (ValidateZerochanItems.isSingleItem(response)) {
				zerochanItems = new ZerochanItems();
				ZerochanItem item = parseApiResponseForSingleItem(response);
				zerochanItems.setItems(new ArrayList<>());
				zerochanItems.getItems().add(item);
			} else {
				zerochanItems = parseApiResponseForMultipleItems(
						new ZerochanResponseParser()
								.getParsedJson(response, commaSeperatedTags)
				);
			}
			return zerochanItems.getItems();
		}
	}
	// Private Http Methods
	private ZerochanItem parseApiResponseForSingleItem(String responseBody) {
		return gson.fromJson(responseBody, ZerochanItem.class);
	}
	private ZerochanItems parseApiResponseForMultipleItems(String responseBody) {
		return gson.fromJson(responseBody, ZerochanItems.class);
	}
	private String getJsonContent() throws IOException {
		return EntityUtils.toString(getEntity());
	}
	private HttpEntity getEntity() throws IOException {
		return getCloseableHttpResponse().getEntity();
	}
	private CloseableHttpResponse getCloseableHttpResponse() throws IOException {
		return httpClient.execute(getHttpGet(), context);
	}
	private HttpGet getHttpGet() {
		HttpGet httpGet = new HttpGet(buildLink());
		httpGet.addHeader("User-Agent",USER_AGENT_HEADER);
		httpGet.addHeader("Accept",ACCEPT_HEADER);
		httpGet.addHeader("Accept-Language",ACCEPT_LANGUAGE_HEADER);
		httpGet.addHeader("Accept-Encoding",ACCEPT_ENCODING_HEADER);
		httpGet.addHeader("DNT",DNT_HEADER);
		httpGet.addHeader("Connection",CONNECTION_HEADER);
		httpGet.addHeader("Upgrade-Insecure-Requests",UPGRADE_INSECURE_REQUESTS_HEADER);
		httpGet.addHeader("Sec-Fetch-Dest",SEC_FETCH_DEST_HEADER);
		httpGet.addHeader("Sec-Fetch-Mode",SEC_FETCH_MODE_HEADER);
		httpGet.addHeader("Sec-Fetch-Site",SEC_FETCH_SITE_HEADER);
		httpGet.addHeader("Sec-Fetch-User",SEC_FETCH_USER_HEADER);
		httpGet.addHeader("Pragma",PRAGMA_HEADER);
		httpGet.addHeader("Cache-Control",CACHE_CONTROL_HEADER);
		httpGet.addHeader("Cookie",COOKIE_HEADER);
		return httpGet;
	}
	private String buildLink() {
		if (!getId().equals("none")) {
			return API_BASE_URL + "/" + getId() + "?json";
		} else if (!getCommaSeperatedTags().equals("none") &&
				   !getAndSeperatedFilters().equals("none")) {
			return API_BASE_URL + "/" + getCommaSeperatedTags() + "?json&" + getAndSeperatedFilters();
		} else {
			return API_BASE_URL + "/" + getCommaSeperatedTags() + "?json";
		}
	}
	// Getter
	private String getId() {
		return id;
	}
	private String getCommaSeperatedTags() {
		return commaSeperatedTags;
	}
	private String getAndSeperatedFilters() {
		return andSeperatedFilters;
	}
	// Setter
	private void setContext() {
		this.context.setCookieStore(cookieStore);
	}
	// Close Resources
	@Override
	public void close() throws IOException {
		httpClient.close();
	}
}