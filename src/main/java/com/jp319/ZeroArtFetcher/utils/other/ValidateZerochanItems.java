package com.jp319.ZeroArtFetcher.utils.other;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.jp319.ZeroArtFetcher.controllers.ZerochanSearcherOnline;
import com.jp319.ZeroArtFetcher.controllers.ZerochanSearcherOnlineV2;
import com.jp319.ZeroArtFetcher.models.ZerochanItem;
import com.jp319.ZeroArtFetcher.utils.config.ZeroArtFetcherConfig;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateZerochanItems {
	public static boolean isValid(List<ZerochanItem> zerochanItems) {
		return zerochanItems != null;
	}
	public static boolean isSingleItem(String responseBody) {
		if (responseBody == null || responseBody.isEmpty()) {
			return false;
		} else return !responseBody.contains("\"items\"");
	}
	public static String getValidJson(String tags) throws IOException {
		String newTag = scrapeJson(tags);
		System.out.println("New Tag: " + newTag);
		if (!newTag.equals("none")) {
			return newTag;
		} else {
			return "none";
		}
	}
	
	public static boolean isValidJson(String response) {
		try {
			JsonParser.parseString(response);
			return true;
		} catch (JsonSyntaxException e) {
			return false;
		}
	}
	public static String scrapeJson(String tags) throws IOException {
		ZerochanSearcherOnline zerochanSearcherOnline = new ZerochanSearcherOnline(tags,FilterManager.getConcatenatedFilters());
		Document document = Jsoup.connect(zerochanSearcherOnline.getCurrentLink()).get();
		Element linkElement = document.select("head link[rel=alternate][type=application/json]").first();
		if (linkElement != null) {
			String hrefValue = linkElement.attr("href");
			return extractStringBetweenSlashesAndQuestionMark(hrefValue);
		} else {
			return "none";
		}
	}
	public static String scrapeJsonV2(String url, HttpGet httpGet, CloseableHttpClient httpClient, HttpClientContext context) throws IOException {
//		Document document = Jsoup.connect(
//				ZeroArtFetcherConfig.getApiBaseUrl()+"/"+tags+"?json&"+FilterManager.getConcatenatedFilters()
//		).get();
		Document document = Jsoup.parse(getFreshResponse(url, httpGet, httpClient, context));
		Element linkElement = document.select("head link[rel=alternate][type=application/json]").first();
		if (linkElement != null) {
			String hrefValue = linkElement.attr("href");
			System.out.println("Href Val: " + hrefValue);
			return extractStringBetweenSlashesAndQuestionMark(hrefValue);
		} else {
			return "none";
		}
	}
	private static String extractStringBetweenSlashesAndQuestionMark(String input) {
		// Define the regular expression pattern to match the desired substring
		String regex = "/([^/?]+)";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		
		if (input.equals("?json")) {
			return "";
		}

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			// Return the entire input string if the pattern is not found
			return input;
		}
	}
	private static String getFreshResponse(String url, HttpGet httpGet, CloseableHttpClient httpClient, HttpClientContext context) {
		CloseableHttpResponse response = null;
		try {
			httpGet.setURI(URI.create(url));
			response = httpClient.execute(httpGet, context);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
