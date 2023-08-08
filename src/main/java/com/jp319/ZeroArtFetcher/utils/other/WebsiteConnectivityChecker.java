package com.jp319.ZeroArtFetcher.utils.other;

import java.io.IOException;
import java.net.*;

public class WebsiteConnectivityChecker {
	
	public static boolean isWebsiteReachable() {
		try {
			URI uri = URI.create("https://www.zerochan.net");
			HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
			connection.setRequestMethod("GET");
			
			// Set a timeout for the connection (optional)
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			
			// Get the response code
			int responseCode = connection.getResponseCode();
			
			// Check if the response code indicates a successful connection (200)
			return responseCode == HttpURLConnection.HTTP_OK;
		} catch (IOException e) {
			// Handle any IO exceptions that occurred during the connection attempt
			e.printStackTrace();
			return false;
		}
	}
}
