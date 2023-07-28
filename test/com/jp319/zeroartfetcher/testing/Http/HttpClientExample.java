package com.jp319.zeroartfetcher.testing.Http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientExample {
	public static void main(String[] args) {
		// Create an instance of CloseableHttpClient
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			// The URL you want to request
			String url = "https://www.zerochan.net/Genshin%20Impact?json&";
			
			// Create an HttpGet request with the URL
			HttpGet httpGet = new HttpGet(url);
			
			// Add headers to the request
			httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
			httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
			httpGet.setHeader("Accept-Language", "en-US,en;q=0.5");
			httpGet.setHeader("Connection", "keep-alive");
			httpGet.setHeader("DNT", "1");
			httpGet.setHeader("Host", "www.zerochan.net");
			httpGet.setHeader("Sec-Fetch-Dest", "document");
			httpGet.setHeader("Sec-Fetch-Mode", "navigate");
			httpGet.setHeader("Sec-Fetch-Site", "none");
			httpGet.setHeader("Sec-Fetch-User", "?1");
			httpGet.setHeader("TE", "trailers");
			httpGet.setHeader("Upgrade-Insecure-Requests", "1");
			httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0");
			
			// Execute the request and get the response
			HttpResponse response = httpClient.execute(httpGet);
			
			// Get the response headers
			System.out.println("Response Headers:");
			for (var header : response.getAllHeaders()) {
				System.out.println(header.getName() + ": " + header.getValue());
			}
			
			// Check the response status code
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("Response Status Code: " + statusCode);
			
			// Get the response body
			HttpEntity entity = response.getEntity();
			String responseBody = EntityUtils.toString(entity);
			
//			// Print the response body
//			System.out.println("Response Body:");
//			System.out.println(responseBody);
			
			// Ensure the response entity is fully consumed and the underlying connection is released
			EntityUtils.consume(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

